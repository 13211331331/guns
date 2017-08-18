package cn.leiyy.modules.wechat.controller;

import cn.leiyy.common.annotion.Permission;
import cn.leiyy.common.annotion.log.BussinessLog;
import cn.leiyy.common.constant.Dict;
import cn.leiyy.common.controller.BaseController;
import cn.leiyy.common.exception.BizExceptionEnum;
import cn.leiyy.common.exception.BussinessException;
import cn.leiyy.common.persistence.model.Dept;
import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.core.util.ToolUtil;
import cn.leiyy.modules.system.warpper.DeptWarpper;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
       // List<Map<String, Object>> list = this.deptDao.list(condition);
       // return super.warpObject(new DeptWarpper(list));
        return null;
    }


    /**
     * 新增部门
     */
    @BussinessLog(value = "添加公众号", key = "name", dict = Dict.PublicSignalDict)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(PublicSignal publicSignal) {
        publicSignalService.add(publicSignal);
        return SUCCESS_TIP;
    }




}
