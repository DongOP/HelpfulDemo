package com.dong.data.processing;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dong.data.processing.data.Person;
import com.dong.data.processing.util.XMLUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private Context mContext;
    private Button mPullParseBTN;
    private Button mPullProduceBTN;
    private ListView mDataList;
    private ArrayAdapter<Person> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initView();
    }

    private void initView() {
        mDataList = (ListView) findViewById(R.id.list);
        mPullParseBTN = (Button) findViewById(R.id.pull_parse);
        mPullProduceBTN = (Button) findViewById(R.id.pull_produce);

        mPullParseBTN.setOnClickListener(this);
        mPullProduceBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pull_parse:
                mAdapter = new ArrayAdapter<Person>(
                        mContext,
                        android.R.layout.simple_expandable_list_item_1,
                        XMLUtils.readXml(mContext)
                );
                mDataList.setAdapter(mAdapter);
                break;
            case R.id.pull_produce:
                List<Person> persons = new ArrayList<Person>();
                persons.add(new Person(1, "路飞", 20));
                persons.add(new Person(2, "艾伦", 18));
                persons.add(new Person(3, "鸣人", 30));

                XMLUtils.startSaveXML(mContext, "Anime.xml", persons);
                break;
            default:
                break;
        }
    }

}
