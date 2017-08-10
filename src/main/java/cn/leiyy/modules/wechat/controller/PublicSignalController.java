package cn.leiyy.modules.wechat.controller;

import cn.leiyy.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanlin.huang on 2017/8/6.
 */
@Controller
@RequestMapping("/public-signal")
public class PublicSignalController extends BaseController {

    private String PREFIX = "/wechat/public-signal/";

    @RequestMapping("")
    public String index() {
        return PREFIX + "index.html";
    }




}
