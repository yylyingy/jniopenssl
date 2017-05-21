package demo.rsa.gkbn.rsademo.sqlcore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

import demo.rsa.gkbn.rsademo.DbHelper;

/**
 * Created by Yangyl on 2017/5/22.
 */

public class BaseDaoManager {
    SQLiteDatabase sqLiteDatabase;
    private static  BaseDaoManager ourInstance;
    DbHelper mDBHelper ;

    public synchronized static BaseDaoManager getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new BaseDaoManager(context);
        }
        return ourInstance;
    }

    private BaseDaoManager(Context context) {
        mDBHelper = new DbHelper(context,"yyl.db",null,1);
//        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(new File(Environment.getExternalStorageDirectory(),"yyl.db"),null);
        sqLiteDatabase = mDBHelper.getWritableDatabase();
    }

    //T 返回值
    public synchronized <T extends AbstactBaseDao<M> ,M> T getBaseDao
            (Class<T> baseDaoClass,Class<M> entityClass){
        AbstactBaseDao<T> baseDao = null;
        try {
            //UserDao
            baseDao = (AbstactBaseDao<T>) baseDaoClass.newInstance();
            baseDao.init(sqLiteDatabase,entityClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }
}
