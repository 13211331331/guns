package cn.leiyy.common.wechat.message.menu;


import java.util.List;

/**
 * 类名: Menu </br>
 * 包名： com.souvc.weixin.menu
 * 描述: 整个菜单对象的封装 </br>
 * 开发人员：souvc </br>
 * 创建时间：  2015-12-1 </br>
 * 发布版本：V1.0  </br>
 */
public class MenuList
{
    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
