package www.example.com.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.daogenerator.Schema.DaoMaster;
import de.greenrobot.daogenerator.Schema.DaoSession;
import de.greenrobot.daogenerator.Schema.Food;
import de.greenrobot.daogenerator.Schema.FoodType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "foods", null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        for (int i = 0; i< 10; i++) {
            FoodType foodType = new FoodType(null,"Name " + i);
            daoSession.getFoodTypeDao().insert(foodType);
        }
        for (int i = 0; i< 2 ; i++){
            Food food = new Food(null,"haha", null);
            daoSession.getFoodDao().insert(food);
        }
    }
}
