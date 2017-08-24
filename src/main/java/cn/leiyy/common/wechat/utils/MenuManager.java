package cn.leiyy.common.wechat.utils;


import cn.leiyy.common.wechat.message.menu.*;
import cn.leiyy.common.wechat.message.pojo.AccessToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuangHL
 * @date 2017/2/27
 * @see
 */
public class MenuManager
{

    public static void main(String[] args) {
        //Token token = CommonUtil.getToken("wxc0bb68f91a99b46c", "2972a0f9b1cb18c35af96d732bbe7b9d");
        // 第三方用户唯一凭证
        String appId = "wxc0bb68f91a99b46c";
        // 第三方用户唯一凭证密钥
        String appSecret = "2972a0f9b1cb18c35af96d732bbe7b9d";


        //Token token = CommonUtil.getToken("wx6626003fc32864f0", "2d9f3ebeccf55c77ce22bfc5c15a5f3c");

        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
            String result = WeixinUtil.createMenuList(getMenuList(), at.getToken());

            // 判断菜单创建结果
            if ("0".equals(result))
            {
                System.out.println("菜单创建成功");
            }
            else
                System.out.println("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("周边搜索");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("历史上的今天");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("歌曲点播");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("经典游戏");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("美女电台");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("人脸识别");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("聊天唠嗑");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q友圈");
        btn31.setType("click");
        btn31.setKey("31");



        ViewButton btn33 = new ViewButton();
        btn33.setName("幽默笑话");
        btn33.setUrl("https://baidu.com");




        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new Button[] { btn31, btn33 });


        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
      //  menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });



        ViewButton btn32 = new ViewButton();
        btn32.setName("百度");
        btn32.setType("view");
        btn32.setUrl("https://www.baidu.com/");


        ComplexButton a = new ComplexButton();
        a.setName("更多体验");
        a.setSub_button(new Button[] { btn32 });




        menu.setButton(new Button[] { a,mainBtn1 });

        return menu;
    }






    private static MenuList getMenuList() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("天气预报");
        btn11.setType("click");
        btn11.setKey("11");

        CommonButton btn12 = new CommonButton();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        CommonButton btn13 = new CommonButton();
        btn13.setName("周边搜索");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("历史上的今天");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("歌曲点播");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("经典游戏");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("美女电台");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("人脸识别");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("聊天唠嗑");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q友圈");
        btn31.setType("click");
        btn31.setKey("31");



        ViewButton btn33 = new ViewButton();
        btn33.setName("幽默笑话");
        btn33.setUrl("https://baidu.com");




        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });


        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });


        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new Button[] { btn31, btn33 });


        /**
         * 封装整个菜单
         */
        MenuList menu = new MenuList();
        //  menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });



        ViewButton btn32 = new ViewButton();
        btn32.setName("百度22");
        btn32.setType("view");
        btn32.setUrl("https://www.baidu.com/");


        List<Button> list1 = new ArrayList<Button>();
        list1.add(btn32);
        ComplexButtonList a = new ComplexButtonList();
        a.setName("更多体验22");
        a.setSub_button(list1);




        List<Button> list = new ArrayList<Button>();
        list.add(a);
        list.add(mainBtn1);
        menu.setButton(list);

        return menu;
    }
}
