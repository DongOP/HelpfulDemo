package dong.sqlite.order;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dong on 2017/11/20 0020.
 */

public class DBOpenHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final int DATABASE_VERSION_2 = 2;
    private static final String DBName = "Dong.db";

    public DBOpenHelper(Context context) {
        super(context, DBName, null, DATABASE_VERSION_2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user ("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username varchar(64),"
                + "password varchar(64))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("TEST","oldversion="+oldVersion);
        if( oldVersion < DATABASE_VERSION_2 ){
            db.execSQL("ALTER TABLE user ADD id Integer DEFAULT 0");
        }
    }
}
