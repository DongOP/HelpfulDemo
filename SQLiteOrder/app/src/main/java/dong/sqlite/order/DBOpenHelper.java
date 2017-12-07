package dong.sqlite.order;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dong on 2017/11/20 0020.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DBName = "Dong.db";

    // 数据库升级 start
    private String CREATE_TEMP_USER = "alter table user rename to _temp_user";
    // 创建新数据库，增加 isVip 数据
    private String CREATE_USER = ("CREATE TABLE IF NOT EXISTS user ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "username varchar(64),"
            + "password varchar(64),"
            + "isVip varchar(64))");
    // 旧数据库中的_id,username,password数据移到新表中
    private String INSERT_DATA = "insert into user select _id,username,password,'' from _temp_user";
    private String DROP_USER = "drop table _temp_user";
    // 数据库升级 stop

    public DBOpenHelper(Context context) {
        super(context, DBName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username varchar(64),"
                + "password varchar(64))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("TEST", "oldVersion=" + oldVersion + ", newVersion=" + newVersion);
        if (newVersion > oldVersion) {
            db.execSQL(CREATE_TEMP_USER); // 更改旧表的临时名称
            db.execSQL(CREATE_USER); // 创建新表
            db.execSQL(INSERT_DATA); // 复制旧表数据到新建的数据表中
            db.execSQL(DROP_USER); // 删除旧的数据库
            Log.d("TEST", "newVersion=" + newVersion + "onUpgrade 数据库升级");
        }
    }
}
