package com.stylefeng.guns.common.wechat.message.resp;


/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class VideoMessage  extends BaseMessage
{
    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
