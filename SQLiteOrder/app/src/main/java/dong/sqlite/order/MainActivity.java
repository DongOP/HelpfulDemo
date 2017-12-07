package dong.sqlite.order;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import dong.sqlite.order.util.SqlUtils;
import dong.sqlite.order.util.ToastUtils;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername, mPassword;
    private ListView mListView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SqlUtils.getInstance(mContext).closeDb();
    }

    private void initView() {
        mContext = MainActivity.this;
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.userpwd);
        mListView = (ListView) findViewById(R.id.values_list);

        mListView.setOnItemLongClickListener(mListLongClickListener);
        mListView.setOnItemClickListener(mListItemClickListener);
    }

    AdapterView.OnItemLongClickListener mListLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // 获取所点击项的_id
            TextView tv = (TextView) view.findViewById(R.id.list_id);
            TextView tvName = (TextView) view.findViewById(R.id.list_username);
            final String listId = tv.getText().toString();
            final String listName = tvName.getText().toString();
            showDeleteDialog(listId, listName);

            return true;
        }
    };

    // 长按删除
    private void showDeleteDialog(final String listId, String listName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(listName);
        builder.setMessage("确定要删除吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SqlUtils.getInstance(mContext).deleteById(listId);// 删除
                replaceList();// 删除后刷新列表
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    AdapterView.OnItemClickListener mListItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 获取_id,username,password项
            TextView tvId = (TextView) view.findViewById(R.id.list_id);
            TextView tvName = (TextView) view.findViewById(R.id.list_username);
            TextView tvPass = (TextView) view.findViewById(R.id.list_password);
            final String lisId = tvId.getText().toString();
            String username = tvName.getText().toString();
            String password = tvPass.getText().toString();

            showUpdateDialog(lisId, username, password);
        }
    };

    // 单击更新修改
    private void showUpdateDialog(final String id, String username, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("修改");
        // 自定义界面包括两个文本输入框
        View v = View.inflate(MainActivity.this, R.layout.dialog_update, null);
        final EditText etName = (EditText) v.findViewById(R.id.alert_name);
        final EditText etPsd = (EditText) v.findViewById(R.id.alert_pass);
        etName.setText(username);
        etPsd.setText(password);
        builder.setView(v);
        // 确定按钮
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = etName.getText().toString();
                String newPass = etPsd.getText().toString();

                SqlUtils.getInstance(mContext).update(newName, newPass, id);
                replaceList();// 更新后刷新列表
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * ListView的适配器
     */
    public void replaceList() {
        Cursor cursor = SqlUtils.getInstance(mContext).selectAll();
        SimpleCursorAdapter adapter = null;
//            adapter = new SimpleCursorAdapter(this,
//                    R.layout.list_item, cursor, new String[]{"_id", "username",
//                    "password"}, new int[]{R.id.list_id, R.id.list_username,
//                    R.id.list_password},
//                    SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

            adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.list_item,
                    cursor,
                    new String[]{"_id", "username", "password"},
                    new int[]{R.id.list_id, R.id.list_username, R.id.list_password},
                    SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
            );
        mListView.setAdapter(adapter);
    }

    /**
     * 保存按钮点击事件，首次插入由于没有表必然报错，简化程序利用try-catch在catch中创建表
     */
    public void save(View v) {
        String name = mUsername.getText().toString();
        String psd = mPassword.getText().toString();
        if (name.isEmpty() || psd.isEmpty()) {
            ToastUtils.showToast(this, "账号或者密码不能为空，请重新输入！");
            return;
        }
        SqlUtils.getInstance(mContext).insert(name, psd);

        ToastUtils.showToast(this, "Save Success");
        mUsername.setText("");
        mPassword.setText("");
    }

    /**
     * 读取按钮点击事件，以列表的形式显示所有内容
     */
    public void read(View v) {
        replaceList();
    }
}
