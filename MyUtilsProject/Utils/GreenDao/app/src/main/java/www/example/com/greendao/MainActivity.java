package www.example.com.greendao;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment mMainFragment;

    public static final String MAIN_FRAGMENT_TAG = "main";
    public static final String OPTIONS_FRAGMENT_TAG = "options";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mMainFragment = mFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG);
        if (mMainFragment == null) {
            mMainFragment = new MainFragmnet();
        }
        fragmentTransaction.add(R.id.frameLayout , mMainFragment, MAIN_FRAGMENT_TAG).commit();
//        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "foods", null);
//        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession daoSession = daoMaster.newSession();

        /*QueryBuilder<Food> foodQueryBuilder = daoSession.getFoodDao().queryBuilder();
        List<Food> list = foodQueryBuilder.list();
        for (Food food : list){
            FoodType foodType = food.getFoodType();
            Log.e("CCCCCCC", "food : " + food.toString() + "  foodType : " + foodType);
        }*/
    }

    @Override
    public void onBackPressed() {
        Fragment optionsFragment = mFragmentManager.findFragmentByTag(OPTIONS_FRAGMENT_TAG);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (optionsFragment != null) {
            fragmentTransaction.remove(optionsFragment);
            fragmentTransaction.show(mMainFragment);
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }
    }
}
