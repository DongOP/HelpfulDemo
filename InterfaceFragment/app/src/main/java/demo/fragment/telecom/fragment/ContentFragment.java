package demo.fragment.telecom.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.fragment.telecom.R;

/**
 * Created by Dong on 2017/4/1 0001.
 */

public class ContentFragment extends Fragment {

    private TextView tvDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);

        return rootView;
    }

    public void updateContentView(int position) {

        if (position == 1) {
            tvDetail.setText("Item 1");
        } else if (position == 2) {
            tvDetail.setText("Item 2");
        } else if (position == 3) {
            tvDetail.setText("Item 3");
        } else if (position == 4) {
            tvDetail.setText("Item 4");
        } else if (position == 5) {
            tvDetail.setText("Item 5");
        } else if (position == 6) {
            tvDetail.setText("Item 6");
        } else {
            tvDetail.setText("Item ...");
        }
    }
}
