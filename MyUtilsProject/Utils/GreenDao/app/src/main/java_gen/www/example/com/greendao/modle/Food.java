package www.example.com.greendao.modle;

import www.example.com.greendao.modle.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "FOOD".
 */
public class Food {

    private Long id;
    /** Not-null value. */
    private String name;
    private Long foodTypeId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient FoodDao myDao;

    private FoodType foodType;
    private Long foodType__resolvedKey;


    public Food() {
    }

    public Food(Long id) {
        this.id = id;
    }

    public Food(Long id, String name, Long foodTypeId) {
        this.id = id;
        this.name = name;
        this.foodTypeId = foodTypeId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFoodDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    /** To-one relationship, resolved on first access. */
    public FoodType getFoodType() {
        Long __key = this.foodTypeId;
        if (foodType__resolvedKey == null || !foodType__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FoodTypeDao targetDao = daoSession.getFoodTypeDao();
            FoodType foodTypeNew = targetDao.load(__key);
            synchronized (this) {
                foodType = foodTypeNew;
            	foodType__resolvedKey = __key;
            }
        }
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        synchronized (this) {
            this.foodType = foodType;
            foodTypeId = foodType == null ? null : foodType.getId();
            foodType__resolvedKey = foodTypeId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
