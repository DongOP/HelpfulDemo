package demo.scanmusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.scanmusic.util.MusicUtils;
import demo.scanmusic.R;
import demo.scanmusic.bean.Song;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class MusicAdapter extends BaseAdapter {

    private Context mContext;
    private List<Song> list;

    public MusicAdapter(Context mContext, List<Song> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //引入布局
            convertView = View.inflate(mContext, R.layout.music_list_item, null);
            //实例化对象
            holder.song = (TextView) convertView.findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) convertView.findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) convertView.findViewById(R.id.item_mymusic_duration);
            holder.position = (TextView) convertView.findViewById(R.id.item_mymusic_postion);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 控件赋值
        holder.song.setText(list.get(position).song.trim());
        holder.singer.setText(list.get(position).singer.trim());
        // 时间转换
        int duration = list.get(position).duration;
        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(time);
        holder.position.setText(position + 1 + "");

        return convertView;
    }

    static class ViewHolder{
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号
    }

}
