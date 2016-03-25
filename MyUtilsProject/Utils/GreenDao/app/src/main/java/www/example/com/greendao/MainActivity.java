package www.example.com.greendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import www.example.com.greendao.modle.DaoMaster;
import www.example.com.greendao.modle.DaoSession;
import www.example.com.greendao.modle.Food;
import www.example.com.greendao.modle.FoodType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "foods", null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
//        for (int i = 0; i< 10; i++) {
//            FoodType foodType = new FoodType(null,"Name " + i);
//            daoSession.getFoodTypeDao().insert(foodType);
//        }
//        for (int i = 0; i< 2 ; i++){
//            long j = i+1;
//            Food food = new Food(null,"haha", j);
//            daoSession.getFoodDao().insert(food);
//        }
        QueryBuilder<Food> foodQueryBuilder = daoSession.getFoodDao().queryBuilder();
        List<Food> list = foodQueryBuilder.list();
        for (Food food : list){
            FoodType foodType = food.getFoodType();
            Log.e("CCCCCCC", "food : " + food.toString() + "  foodType : " + foodType);
        }
    }
}
