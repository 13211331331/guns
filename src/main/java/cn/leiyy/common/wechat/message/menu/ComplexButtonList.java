package cn.leiyy.common.wechat.message.menu;

import java.util.List;

/**
 * 类名: ComplexButton </br>
 * 包名： com.souvc.weixin.menu
 * 描述: 父菜单项 :包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组 </br>
 * 开发人员： souvc  </br>
 * 创建时间：  2015-12-1 </br>
 * 发布版本：V1.0  </br>
 */
public class ComplexButtonList extends Button
{
    private List<Button> sub_button;

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }
}
