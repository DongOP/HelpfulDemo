package demo.scanmusic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import demo.scanmusic.R;
import demo.scanmusic.adapter.MusicAdapter;
import demo.scanmusic.bean.Song;
import demo.scanmusic.util.MusicUtils;

public class MainActivity extends Activity {

    private ListView mListView;
    private List<Song> mList;
    private MusicAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.music_listview);
        mList = new ArrayList<>();
        // 扫描到的music放置在list
        mList = MusicUtils.getMusicData(this);
        mAdapter = new MusicAdapter(this, mList);

        mListView.setAdapter(mAdapter);
    }
}
