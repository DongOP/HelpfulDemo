package com.dong.data.processing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dong.data.processing.data.Person;
import com.dong.data.processing.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends Activity implements View.OnClickListener{

    // 一个json对象
    private static final String json_1 = "{\"name\":\"dong\",\"age\":18,\"weight\":60}";
    // 一个数字数组
    private static final String json_2 = "[12,13,15]";
    // jsonArray中有object
    private static final String json_3 = "[{\"name\":\"sam\",\"age\":18},{\"name\":\"leo\",\"age\":19},{\"name\":\"sky\", \"age\":20}]";
    private Button mJsonObjectBTN;
    private Button mJsonArrayBTN;
    private Button mComplicatedBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        initView();
    }

    private void initView() {
        mJsonObjectBTN = (Button) findViewById(R.id.jsonObject_parse);
        mJsonArrayBTN = (Button) findViewById(R.id.jsonArray_parse);
        mComplicatedBTN = (Button) findViewById(R.id.complicated_parse);

        mJsonObjectBTN.setOnClickListener(this);
        mJsonArrayBTN.setOnClickListener(this);
        mComplicatedBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jsonObject_parse:
                // {"name":"sam","age":18,"weight":60}
                handleJsonObject();
                break;
            case R.id.jsonArray_parse:
                // [12,13,15]
                handleJsonArray();
                break;
            case R.id.complicated_parse:
                // [{"name":"sam","age":18}, {"name":"leo","age":19}, {"name":"sky", "age":20}]
                handleComplicated();
                break;
            default:
                break;
        }
    }

    private void handleComplicated() {
        List<Person> personLists = new ArrayList<Person>();
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(json_3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            String name = jsonObject.optString("name");
            int age = jsonObject.optInt("age");
            personLists.add(new Person(i + 1, name, age));
        }

        ToastUtils.showToast(this, "personLists=" + personLists.toString());
    }

    private void handleJsonArray() {
        List<String> ageLists = new ArrayList<String>();
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(json_2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            int age = jsonArray.optInt(i);
            ageLists.add(age+"");
        }

        ToastUtils.showToast(this, "ageLists=" + ageLists.toString());
    }

    private void handleJsonObject() {
        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(json_1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String name = jsonObj.optString("name");
        int age = jsonObj.optInt("age");
        int weight = jsonObj.optInt("weight");

        ToastUtils.showToast(this, "name=" + name + ", age=" + age + ", weight=" + weight);
    }
}
