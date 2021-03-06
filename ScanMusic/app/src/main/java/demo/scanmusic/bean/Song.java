package demo.scanmusic.bean;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

/** 放置音乐 */

public class Song {
    /** * 歌手 */
    public String singer;
    /** * 歌曲名 */
    public String song;
    /** * 歌曲的地址 */
    public String path;
    /** * 歌曲长度 */
    public int duration;
    /** * 歌曲的大小 */
    public long size;

    @Override
    public String toString() {
        return "Song{" +
                "singer='" + singer + '\'' +
                ", song='" + song + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                '}';
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSinger() {
        return singer;
    }

    public String getSong() {
        return song;
    }

    public String getPath() {
        return path;
    }

    public int getDuration() {
        return duration;
    }

    public long getSize() {
        return size;
    }
}
