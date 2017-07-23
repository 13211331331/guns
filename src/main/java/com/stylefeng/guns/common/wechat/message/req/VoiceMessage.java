package com.stylefeng.guns.common.wechat.message.req;


/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class VoiceMessage extends BaseMessage
{
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
