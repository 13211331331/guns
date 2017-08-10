package cn.leiyy.common.wechat.message.event;
/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class MenuEvent extends BaseEvent
{
    // 事件KEY值，与自定义菜单接口中KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
