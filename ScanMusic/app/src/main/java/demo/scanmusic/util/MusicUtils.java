package demo.scanmusic.util;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import demo.scanmusic.bean.Song;

/**
 * music utils
 */
public class MusicUtils {

    // 扫描系统里面的音频文件，返回一个list集合
    public static List<Song> getMusicData(Context context) {
        List<Song> list = new ArrayList<Song>();

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.AudioColumns.IS_MUSIC
        );
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Song song = new Song();
                // get song name
                song.song = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DISPLAY_NAME));
                // get singer
                song.singer = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.ARTIST));
                // get song path
                song.path = cursor.getString(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DATA));
                //song duration
                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.DURATION));
                // song size
                song.size = cursor.getLong(cursor.getColumnIndexOrThrow(
                        MediaStore.Audio.Media.SIZE));
                if (song.size > 1000 * 800) {
                    // 分离出歌曲名和歌手
                    if (song.song.contains("-")) {
                        String[] str = song.song.split("-");
                        song.singer = str[0];
                        song.song = str[1];
                    }

                }

                list.add(song);
            }

            cursor.close();
        }
        return list;
    }

    /**
     * 格式化获取到的时间
     */
    public static String formatTime(int time) {

        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;
        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }
    }

}
