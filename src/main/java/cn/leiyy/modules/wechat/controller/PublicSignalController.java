package cn.leiyy.modules.wechat.controller;

import cn.leiyy.common.annotion.Permission;
import cn.leiyy.common.annotion.log.BussinessLog;
import cn.leiyy.common.constant.Dict;
import cn.leiyy.common.constant.factory.PageFactory;
import cn.leiyy.common.controller.BaseController;
import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hanlin.huang on 2017/8/6.
 */
@Controller
@RequestMapping("/public-signal")
public class PublicSignalController extends BaseController {

    @Autowired
    private IPublicSignalService publicSignalService;

    private String PREFIX = "/wechat/public-signal/";

    @RequestMapping("")
    public String index() {
        return PREFIX + "index.html";
    }


    @RequestMapping("/to-add")
    public String toAdd() {
        return PREFIX + "add.html";
    }



    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(String condition) {
        Page<PublicSignal> page = new PageFactory<PublicSignal>().defaultPage();
        page.setRecords(publicSignalService.findByCondition(page,condition));
        return super.packForBT(page);
    }


    /**
     * 新增部门
     */
    @BussinessLog(value = "添加公众号", key = "name", dict = Dict.PublicSignalDict)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(PublicSignal publicSignal) {
        publicSignalService.insert(publicSignal);
        return SUCCESS_TIP;
    }




}
