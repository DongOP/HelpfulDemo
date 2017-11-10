package dong.builder.mode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dong.builder.mode.builder.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mResult;
    private Button mBTNResult;
    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mResult = (TextView) findViewById(R.id.tv_show_result);
        mBTNResult = (Button) findViewById(R.id.btn_result);

        mBTNResult.setOnClickListener(this);
    }

    private void initData() {
        Person.Builder builder = new Person.Builder();
        mPerson = builder
                .name("小李")
                .age(18)
                .height(172)
                .weight(56)
                .build();
    }

    @Override
    public void onClick(View v) {
        String result = "结果=" + mPerson.toString();
        mResult.setText(result);
    }

}
