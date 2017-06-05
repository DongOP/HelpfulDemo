package eagle.contentresolverdemo;

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
    private Button btn_cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_cp = (Button) findViewById(R.id.btn_content_provider);

        btn_cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testContentResolver();
                Toast.makeText(MainActivity.this, "获取数据库信息", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void testContentResolver() {
        Log.d("ContentResolver","testContentResolver......");
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Log.e("ContentResolver", "......photo info id:" + cursor.getInt(cursor.getColumnIndex("id")));
                Log.e("ContentResolver", "......photo info pid:" + cursor.getString(cursor.getColumnIndex("pid")));
                Log.e("ContentResolver", "......photo info openid:" + cursor.getString(cursor.getColumnIndex("openid")));
                Log.e("ContentResolver", "......photo info headimgurl:" + cursor.getString(cursor.getColumnIndex("headimgurl")));
                Log.e("ContentResolver", "......photo info picurl1:" + cursor.getString(cursor.getColumnIndex("picurl1")));
                Log.e("ContentResolver", "......photo info picurl2:" + cursor.getString(cursor.getColumnIndex("picurl2")));
                Log.e("ContentResolver", "......photo info fromuser:" + cursor.getString(cursor.getColumnIndex("fromuser")));
                Log.e("ContentResolver", "......photo info sendtime:" + cursor.getString(cursor.getColumnIndex("sendtime")));
                Log.e("ContentResolver", "......photo info sendtext:" + cursor.getString(cursor.getColumnIndex("sendtext")));
                Log.e("ContentResolver", "......photo info mark:" + cursor.getInt(cursor.getColumnIndex("mark")));
                Log.e("ContentResolver", "......photo info viseble:" + cursor.getInt(cursor.getColumnIndex("viseble")));
                Log.e("ContentResolver", "......photo info favor:" + cursor.getInt(cursor.getColumnIndex("favor")));
                Log.e("ContentResolver", "......photo info isVideo:" + cursor.getInt(cursor.getColumnIndex("isVideo")));
                Log.e("ContentResolver", "......photo info mediaurl:" + cursor.getString(cursor.getColumnIndex("mediaurl")));
                Log.e("ContentResolver", "......photo info mediapic:" + cursor.getString(cursor.getColumnIndex("mediapic")));
                Log.e("ContentResolver", "......photo info loadtime:" + cursor.getInt(cursor.getColumnIndex("loadtime")));

                Log.e("ContentResolver", "!!!!!!!!!!!!!!!!!!!!! photo info finish !!!!!!!!!!!!!!");
            }

            cursor.close();
        } else {
            Log.e("ContentResolver","cursor = null");
        }
    }
}
