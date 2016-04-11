package www.example.com.greendao.modle.www.example.com.greendao.modle.db;

import android.database.sqlite.SQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import de.greenrobot.dao.identityscope.IdentityScope;

/**
 *Base class for all Daos
 *  1. entity operations : insert, query, delete, update
 *@param <T> Entity type
 *@param <K> Entity Primary Key type
 */
public class AbstractDao<T, K> {
    private final SQLiteDatabase mSQLiteDatabase;
    private final DaoConfig mDaoConfig;
    protected IdentityScope<K, T> identityScope;

    public AbstractDao( DaoConfig mDaoConfig) {
        this.mSQLiteDatabase = mDaoConfig.mSQLiteDatebase;
        this.mDaoConfig = mDaoConfig;
    }

    public T load(@NotNull K key){

    }
}
