package cn.leiyy.common.wechat.message.resp;


/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class TextMessage extends BaseMessage
{
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
