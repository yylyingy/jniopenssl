package demo.rsa.gkbn.rsademo.sqlcore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Yangyl on 2017/5/22.
 */

public abstract class AbstactBaseDao <T> implements IBaseDao<T> {
    private SQLiteDatabase sqLiteDatabase;
    //user的class类型
    private Class entityClass;
    private boolean init = false;

    /**
     * 表名
     */
    private String tabName;
    //映射关系
    private HashMap<String,Field> cacheMap;

    public void init(SQLiteDatabase sqLiteDatabase,Class entityClass){
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;
        if (!init){
            if (sqLiteDatabase.isOpen()){
                //IF NOT EXISTS
                sqLiteDatabase.execSQL(createTable());
            }
            DbTable dbTable = (DbTable) entityClass.getAnnotation(DbTable.class);
            if (dbTable != null){
                tabName = dbTable.value();
            }
            initCacheMap();
            init = true;

        }
    }

    private void initCacheMap(){
        cacheMap = new HashMap<>();
        String sql = "SELECT * FROM " + tabName +
                " limit 1 , 0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        String[] columnNames = cursor.getColumnNames();
        Field[] columnFields = entityClass.getDeclaredFields();
        for (String columnName:columnNames){
            String fieldAnnotionName = null;
            Field columnField = null;
            for (Field field:columnFields){
                if (field.getAnnotation(DbField.class)!= null) {
                    //拿到列名
                    fieldAnnotionName = field.getAnnotation(DbField.class).value();
                    if (fieldAnnotionName.equals(columnName)){
                        //找到映射关系
                        columnField = field;
                        break;
                    }
                }
            }
            if (columnField != null){
                cacheMap.put(columnName,columnField);
            }
        }
        cursor.close();
    }

    @Override
    public long insert(T t) {
        //T---->>contentValues user tb_name  "lisi" password "123456
        HashMap<String,String> hashMap = getValues(t);
        ContentValues contentValues = getContentValues(hashMap);
        sqLiteDatabase.insert(tabName,null,contentValues);
        return 0;
    }

    //hashmap  "tb_name" ---"lisi"
    private HashMap<String,String> getValues(T t){
        HashMap<String,String> result = new HashMap<>();
        //遍历cacheMap 通过反射去除 “lisi”
        Iterator iterator = cacheMap.values().iterator();
        while (iterator.hasNext()){
            Field field = (Field) iterator.next();
            String cacheKey = null;
            String cacheValue = null;
            //得到列名tb_name
            cacheKey = field.getAnnotation(DbField.class).value();
            try {
                if (field.get(t) == null){
                    continue;
                }
                //得到“lisi”
                cacheValue = field.get(t).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (cacheKey != null){
                result.put(cacheKey,cacheValue);
            }
        }
        return result;
    }

    private ContentValues getContentValues(HashMap<String,String> map){
        ContentValues contentValues = new ContentValues();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = map.get(key);
            contentValues.put(key, value);
        }
        return contentValues;
    }

    @Override
    public int updata(T entity, T there) {
        return 0;
    }

    /**
     * 建表语句，子类实现
     * @return
     */
    public abstract String createTable();













}
