package www.example.com.greendao;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.CursorQuery;
import www.example.com.greendao.modle.DaoMaster;
import www.example.com.greendao.modle.DaoSession;
import www.example.com.greendao.modle.Food;
import www.example.com.greendao.modle.FoodDao;
import www.example.com.greendao.modle.FoodType;
import www.example.com.greendao.modle.FoodTypeDao;

/**
 * Created by wangfei on 16-3-31.
 */
public class OptionsFragment extends Fragment {

    public static final String OPTIONS_FLAY_STRING = "OPTIONS";

    public static final byte ADD_OPTIONS = 1;
    public static final byte QUERY_OPTIONS = 2;

    private DaoSession mDaoSession;
    private CursorAdapter mCursorAdapter;
    private OptionsQueryAdpater mQueryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Thread to running
        SQLiteOpenHelper devOpenHelper = DaoMaster.getSQLiteDatabaseInstance(getActivity());
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        mCursorAdapter = new OptionsCursorAdapter(getActivity(), null);
        mQueryAdapter = new OptionsQueryAdpater(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        byte option = arguments.getByte(OPTIONS_FLAY_STRING);
        int layoutResource = getLayoutResource(option);
        if (layoutResource != 0) {
            View view = inflater.inflate(layoutResource, null);
            addOptions(view, option);
            return view;
        }
        return null;
    }

    private int getLayoutResource(byte option) {
        switch (option) {
            case ADD_OPTIONS:
                return R.layout.options_fragment_add;
            case QUERY_OPTIONS:
                return R.layout.options_fragment_delete;
            default:
                return 0;
        }
    }

    private FoodType mFoodType;
    private void addOptions(final View view, int options) {
        if (view != null) {
            switch (options) {
                case ADD_OPTIONS:
                    view.findViewById(R.id.addFoodType).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CharSequence footTypeName = ((TextView) view.findViewById(R.id.footTypeNameText)).getText();
                            if (!TextUtils.isEmpty(footTypeName)) {
                                FoodType foodType = new FoodType(null, footTypeName.toString());
                                insert(getActivity(), FoodTypeDao.TABLENAME, foodType);
                            }
                        }
                    });
                    view.findViewById(R.id.addFood).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CharSequence foodName = ((TextView) view.findViewById(R.id.foodName)).getText();
                            if (!TextUtils.isEmpty(foodName)) {
                                Food food = new Food(null, foodName.toString(), mFoodType != null ? mFoodType.getId() : null);
                                insert(getActivity(), FoodDao.TABLENAME, food);
                                mFoodType = null;
                            }
                        }
                    });
                    ListView listView = (ListView) view.findViewById(R.id.foodSelectId);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mFoodType = (FoodType) view.getTag();
                        }
                    });
                    listView.setAdapter(mCursorAdapter);
                    new AsyncTask<Void, Void, Cursor>() {
                        @Override
                        protected Cursor doInBackground(Void... params) {
                            Cursor cursor = query(getActivity(), FoodType.class);
                            return cursor;
                        }

                        @Override
                        protected void onPostExecute(Cursor cursor) {
                            if (mCursorAdapter != null) {
                                mCursorAdapter.changeCursor(cursor);
                            }
                        }
                    }.execute();
                    break;
                case QUERY_OPTIONS:
                    ListView queryListView = (ListView) view.findViewById(R.id.queryListView);
                    queryListView.setAdapter(mQueryAdapter);
                    new AsyncTask<Void, Void, List<Food>>() {
                        @Override
                        protected List<Food> doInBackground(Void... params) {
                            List<Food> foods = query(getActivity());
                            return foods;
                        }

                        @Override
                        protected void onPostExecute(List<Food> foods) {
                            if(mQueryAdapter != null) {
                                mQueryAdapter.changeData(foods);
                            }
                        }
                    }.execute();

                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    private void insert(Context context, String tableName, Object object) {
        long insertCount = mDaoSession.insert(object);
        if (insertCount > 0) {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    private Cursor query(Context context, Class entryClazz) {
        SQLiteOpenHelper devOpenHelper = DaoMaster.getSQLiteDatabaseInstance(context);
        SQLiteDatabase db = devOpenHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        CursorQuery cursorQuery = daoSession.queryBuilder(entryClazz).buildCursor();
        cursorQuery.forCurrentThread();
        Cursor query = cursorQuery.query();
        return query;
    }

    private List<Food> query(Context context) {
        SQLiteOpenHelper devOpenHelper = DaoMaster.getSQLiteDatabaseInstance(context);
        SQLiteDatabase db = devOpenHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        FoodDao foodDao = daoSession.getFoodDao();
        List<Food> foods = foodDao.loadAll();
        return foods;
    }

    private static class OptionsQueryAdpater extends BaseAdapter{

        private Context mContext;
        public OptionsQueryAdpater(Context context){
            this.mContext = context;
        }
        public List<Food> list = new ArrayList<Food>();
        public void changeData( @NotNull List<Food> list){
            this.list = list;
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.list_item_query_item, null);
            }
            Food food = list.get(position);
            TextView queryFoodId = (TextView) view.findViewById(R.id.queryFoodId);
            queryFoodId.setText("foodId ： " + food.getId());

            TextView queryFoodName = (TextView) view.findViewById(R.id.queryFoodName);
            queryFoodName.setText("  foodName ： " + food.getName());

            TextView queryFoodTypeName = (TextView) view.findViewById(R.id.queryFoodTypeName);
            queryFoodTypeName.setText("  foodTypeName ： " + (food.getFoodType()==null ?  "NULL" : food.getFoodType().getName()));
            return view;
        }
    }


    private static class OptionsCursorAdapter extends CursorAdapter{

        public OptionsCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item_layout, null);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView foodTypeId = (TextView) view.findViewById(R.id.foodTypeid);
            TextView foodTypeName = (TextView) view.findViewById(R.id.foodTypeName);
            FoodType foodType = FoodTypeDao.readEntity(cursor);
            foodTypeId.setText(""+foodType.getId());
            foodTypeName.setText(foodType.getName());
            view.setTag(foodType);
        }
    }
}
