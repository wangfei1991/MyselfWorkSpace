package www.example.com.greendao.modle.www.example.com.greendao.modle.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. manager Tables Dao
 * 2. get Dao Session
 */
public class AbstractDaoMaster {
    protected final SQLiteDatabase db;
    protected final Map<Class<? extends AbstractDao<?,?>>, DaoConfig> mDaoConfigMap;

    public AbstractDaoMaster(SQLiteDatabase db) {
        this.db = db;
        mDaoConfigMap = new HashMap<>();
    }
}
