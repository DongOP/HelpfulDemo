package dong.sqlite.order.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dong.sqlite.order.DBOpenHelper;

/**
 * Created by Dong on 2017/11/20 0020.
 */

public class SqlUtils {
    private static SqlUtils instance = null;
    private DBOpenHelper mDbOpenHelper;
//    private static SQLiteDatabase mDB = SQLiteDatabase.openOrCreateDatabase(SDUtils.getSDCardPath() + "/info.db", null);

    public SqlUtils(Context context) {
        this.mDbOpenHelper = new DBOpenHelper(context);
    }

    public synchronized static SqlUtils getInstance(Context ctx) {
        if (null == instance) {
            instance = new SqlUtils(ctx);
        }
        return instance;
    }

//    /**
//     * 建表
//     */
//    public void create() {
//        String createSql = "CREATE TABLE IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT,username,password)";
//        mDB.execSQL(createSql);
//    }

    /**
     * 增加
     *
     * 纯sql指令：INSERT INTO user(_id,username,password) VALUES (5, 'Allen', '123456789');
     */
    public void insert(String username, String password) {
//        String insertSql = "insert into user(username,password) values(?,?)";
//        mDB.execSQL(insertSql, new String[]{username, password});
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        int result = (int) db.insert("user", null, values);
    }

    /**
     * 删除
     *
     * 纯sql指令：delete from user where _id=1;
     */
    public void deleteById(String id) {
//        String deleteSql = "delete from user where _id=?";
//        mDB.execSQL(deleteSql, new String[]{id});
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        db.delete("user", "_id=?", new String[] {id});
    }

    /**
     * 查询
     *
     * 纯sql指令：select * from user
     */
    public Cursor selectAll() {
//        String selectSql = "select _id,username,password from user";
//        Cursor cursor = mDB.rawQuery(selectSql, null);
        String sql = "select _id,username,password from user";
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    /**
     * 修改
     *
     * 纯sql指令：UPDATE user SET username= 'Zhan', password= "123" where _id = 1;
     */
    public void update(String username, String password, String id) {
//        String updateSql = "update user set username=?,password=? where _id=?";
//        mDB.execSQL(updateSql, new String[]{username, password, id});
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("_id", id);
        db.update("user", values, "_id=?", new String[] {id});
    }

    public void closeDb() {
        if (mDbOpenHelper != null) {
            mDbOpenHelper.close();
        }
    }
}
