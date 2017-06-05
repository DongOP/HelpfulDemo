package com.allwinner.theatreplayer.album.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.allwinner.theatreplayer.album.push.DBOpenHelper;

/**
 * Created by Dong on 2017/6/2 0002.
 * <p>
 * 共享 photo数据库
 */

public class PhotoContentProvider extends ContentProvider {

    private DBOpenHelper mOpenHelper;
    private static final int PHOTO_CODE = 1;
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String CONTENT_AUTHORITY = "com.allwinner.theatreplayer.album.PhotoContentProvider";
    private static final String PHOTO_TABLE_NAME = "photo";
    private static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY + "/photo");

    static {
        mUriMatcher.addURI(CONTENT_AUTHORITY, PHOTO_TABLE_NAME, PHOTO_CODE);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DBOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        switch (mUriMatcher.match(uri)) {
            case PHOTO_CODE:
                cursor = db.query(PHOTO_TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI: " + uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case PHOTO_CODE:
                long rowID = db.insert(PHOTO_TABLE_NAME, null, values);
                if (rowID > 0) {
                    Uri retUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
                    return retUri;
                }
            break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case PHOTO_CODE:
                count = db.delete(PHOTO_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI :" + uri);
        }
        // 更新数据时，通知其他ContentObserver
        this.getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case PHOTO_CODE:
                count = db.update(PHOTO_TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI : " + uri);
        }
        this.getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}
