package cn.leiyy.common.wechat.message.req;
/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class ImageMessage extends BaseMessage
{
    // 图片链接
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
