package eagle.contentresolverdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String CONTENT_AUTHORITY = "com.allwinner.theatreplayer.album.PhotoContentProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY + "/photo");
    private Button btnQuary, btnUpdate, btnDelete, btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnQuary = (Button) findViewById(R.id.btn_quary);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnInsert = (Button) findViewById(R.id.btn_insert);

        btnQuary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doQuery();
                Toast.makeText(MainActivity.this, "获取数据库信息", Toast.LENGTH_LONG).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdate();
                Toast.makeText(MainActivity.this, "Update数据库信息", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDelete();
                Toast.makeText(MainActivity.this, "Delete数据库信息", Toast.LENGTH_LONG).show();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInsert();
                Toast.makeText(MainActivity.this, "Insert数据库信息", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void doQuery() {
        Log.d("ContentResolver","testContentResolver......");
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Log.d("ContentResolver", "......photo info id:" + cursor.getInt(cursor.getColumnIndex("id")));
                Log.d("ContentResolver", "......photo info pid:" + cursor.getString(cursor.getColumnIndex("pid")));
                Log.d("ContentResolver", "......photo info openid:" + cursor.getString(cursor.getColumnIndex("openid")));
                Log.d("ContentResolver", "......photo info headimgurl:" + cursor.getString(cursor.getColumnIndex("headimgurl")));
                Log.d("ContentResolver", "......photo info picurl1:" + cursor.getString(cursor.getColumnIndex("picurl1")));
                Log.d("ContentResolver", "......photo info picurl2:" + cursor.getString(cursor.getColumnIndex("picurl2")));
                Log.d("ContentResolver", "......photo info fromuser:" + cursor.getString(cursor.getColumnIndex("fromuser")));
                Log.d("ContentResolver", "......photo info sendtime:" + cursor.getString(cursor.getColumnIndex("sendtime")));
                Log.d("ContentResolver", "......photo info sendtext:" + cursor.getString(cursor.getColumnIndex("sendtext")));
                Log.d("ContentResolver", "......photo info mark:" + cursor.getInt(cursor.getColumnIndex("mark")));
                Log.d("ContentResolver", "......photo info viseble:" + cursor.getInt(cursor.getColumnIndex("viseble")));
                Log.d("ContentResolver", "......photo info favor:" + cursor.getInt(cursor.getColumnIndex("favor")));
                Log.d("ContentResolver", "......photo info isVideo:" + cursor.getInt(cursor.getColumnIndex("isVideo")));
                Log.d("ContentResolver", "......photo info mediaurl:" + cursor.getString(cursor.getColumnIndex("mediaurl")));
                Log.d("ContentResolver", "......photo info mediapic:" + cursor.getString(cursor.getColumnIndex("mediapic")));
                Log.d("ContentResolver", "......photo info loadtime:" + cursor.getInt(cursor.getColumnIndex("loadtime")));

                Log.e("ContentResolver", "!!!!!!!!!!!!!!!!!!!!! photo info finish !!!!!!!!!!!!!!");
            }

            cursor.close();
        } else {
            Log.e("ContentResolver","cursor = null");
        }
    }

    private void doUpdate() {
        ContentValues values = new ContentValues();
        values.put("sendtext", "My name is dong.");
        this.getContentResolver().update(CONTENT_URI, values, "id = 2", null);

        doQuery();
    }

    private void doDelete() {
        this.getContentResolver().delete(CONTENT_URI, "id = 2", null);

        doQuery();
    }

    private void doInsert() {
        ContentValues values = new ContentValues();
        values.put("id", "66");
        values.put("openid", "795E77B9-3124-442C-8AFC-F872AC01F2EF");
        /**Uri uri = **/this.getContentResolver().insert(CONTENT_URI, values);
//        Log.e("test ", uri.toString());

        doQuery();
    }

}
