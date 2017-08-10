package cn.leiyy.common.wechat.message.event;
/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class QRCodeEvent  extends BaseEvent
{
    // 事件KEY值
    private String EventKey;
    // 用于换取二维码图片
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
