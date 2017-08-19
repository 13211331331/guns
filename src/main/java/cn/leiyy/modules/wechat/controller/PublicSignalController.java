package cn.leiyy.modules.wechat.controller;

import cn.leiyy.common.annotion.Permission;
import cn.leiyy.common.annotion.log.BussinessLog;
import cn.leiyy.common.constant.Const;
import cn.leiyy.common.constant.Dict;
import cn.leiyy.common.constant.cache.Cache;
import cn.leiyy.common.constant.factory.ConstantFactory;
import cn.leiyy.common.constant.factory.PageFactory;
import cn.leiyy.common.constant.tips.Tip;
import cn.leiyy.common.controller.BaseController;
import cn.leiyy.common.exception.BizExceptionEnum;
import cn.leiyy.common.exception.BussinessException;
import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.common.persistence.model.Role;
import cn.leiyy.core.cache.CacheKit;
import cn.leiyy.core.log.LogObjectHolder;
import cn.leiyy.core.util.ToolUtil;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

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
        page.setRecords(publicSignalService.findByCondition(page, condition));
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

    /**
     * 删除部门
     */
    @BussinessLog(value = "删除公众号", key = "id", dict = Dict.PublicSignalDict)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam Integer id) {

        //缓存被删除的部门名称
       // LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));

        publicSignalService.deleteById(id);

        return SUCCESS_TIP;
    }


    /**
     * 跳转到修改角色
     */
    @Permission
    @RequestMapping(value = "/update/{id}")
    public String toUpdate(@PathVariable Integer id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        PublicSignal publicSignal = this.publicSignalService.selectById(id);
        model.addAttribute("publicSignal", publicSignal);
        LogObjectHolder.me().set(publicSignal);
        return PREFIX + "/update.html";
    }


    /**
     * 角色修改
     */
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改公众号", key = "name", dict = Dict.PublicSignalDict)
    @ResponseBody
    public Tip update(PublicSignal publicSignal) {
        this.publicSignalService.updateById(publicSignal);
        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }
}
