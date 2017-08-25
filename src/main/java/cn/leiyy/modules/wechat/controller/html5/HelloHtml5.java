package cn.leiyy.modules.wechat.controller.html5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanlin.huang on 2017/8/25.
 */
@Controller
@RequestMapping("/h5/hello")
public class HelloHtml5 {


    private String PREFIX = "/wechat/html5/hello/";


    @RequestMapping("/index")
    public String index() {
        return PREFIX + "index.html";
    }

    @RequestMapping(value = "/examples/{page}")
    public String examples(@PathVariable String page) {
        return PREFIX + "examples/"+page;
    }
}
