package demo.fragment.telecom;

import android.app.Activity;
import android.os.Bundle;

import demo.fragment.telecom.fragment.ContentFragment;
import demo.fragment.telecom.fragment.TitleFragment;

public class MainActivity extends Activity implements TitleFragment.OnTitleSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onTitleSelected(int position) {
        ContentFragment contentFragment = (ContentFragment) getFragmentManager().findFragmentById(R.id.fragment_content);
        if (contentFragment != null) {
            contentFragment.updateContentView(position);
        } else {
            ContentFragment newFragment = new ContentFragment();
            newFragment.updateContentView(position);
        }
    }
}
