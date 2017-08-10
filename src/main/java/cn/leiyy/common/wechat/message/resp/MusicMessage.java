package cn.leiyy.common.wechat.message.resp;
/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class MusicMessage extends BaseMessage
{
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
