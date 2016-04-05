package www.example.com.greendao;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.CursorQuery;
import de.greenrobot.dao.query.QueryBuilder;
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
    public static final byte DELETE_OPTIONS = 2;
    public static final byte UPDATE_OPTIONS = 3;
    public static final byte QUERY_OPTIONS = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
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
            case DELETE_OPTIONS:
                return R.layout.options_fragment_delete
            default:
                return 0;
        }
    }

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
                                Food food = new Food(null, foodName.toString(), null);
                                insert(getActivity(), FoodDao.TABLENAME, food);
                            }
                        }
                    });
                    break;
                case DELETE_OPTIONS:
                    Cursor cursor = query(getActivity(), FoodDao.class);
                    CursorAdapter cursorAdapter =
                    /*ListView delectListView = (ListView) view.findViewById(R.id.deleteListView);
                    delectListView.setAdapter();*/
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
        SQLiteOpenHelper devOpenHelper = DaoMaster.getSQLiteDatabaseInstance(context);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        final DaoSession daoSession = daoMaster.newSession();
        long insertCount = daoSession.insert(object);
        if (insertCount > 0) {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    private Cursor query(Context context, Class dao) {
        SQLiteOpenHelper devOpenHelper = DaoMaster.getSQLiteDatabaseInstance(context);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        CursorQuery cursorQuery = daoSession.queryBuilder(dao).buildCursor();
        Cursor query = cursorQuery.query();
        return query;
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
        }
    }
    /*ew CursorAdapter() {
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return null;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }
    }*/

}
