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
import cn.leiyy.common.node.ZTreeNode;
import cn.leiyy.common.persistence.model.PublicSignal;
import cn.leiyy.common.persistence.model.WechatMenu;
import cn.leiyy.core.cache.CacheKit;
import cn.leiyy.core.log.LogObjectHolder;
import cn.leiyy.core.util.ToolUtil;
import cn.leiyy.modules.system.warpper.MenuWarpper;
import cn.leiyy.modules.wechat.service.IPublicSignalService;
import cn.leiyy.modules.wechat.service.IWechatMenuService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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

    @Autowired
    private IWechatMenuService wechatMenuService;

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



    @BussinessLog(value = "添加公众号", key = "name", dict = Dict.PublicSignalDict)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(PublicSignal publicSignal) {
        publicSignalService.insert(publicSignal);
        return SUCCESS_TIP;
    }


    @BussinessLog(value = "删除公众号", key = "id", dict = Dict.PublicSignalDict)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
       // LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));
        publicSignalService.deleteById(id);
        return SUCCESS_TIP;
    }



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


    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改公众号", key = "name", dict = Dict.PublicSignalDict)
    @ResponseBody
    public Tip update(PublicSignal publicSignal) {
        this.publicSignalService.updateById(publicSignal);
        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }



    @Permission
    @RequestMapping(value = "/menu/{id}")
    public String toMenu(@PathVariable Integer id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        PublicSignal publicSignal = this.publicSignalService.selectById(id);
        model.addAttribute("publicSignal", publicSignal);
        model.addAttribute("id", id);
        LogObjectHolder.me().set(publicSignal);
        return PREFIX + "/menu.html";
    }

    @Permission
    @RequestMapping(value = "/menu-list/{id}")
    @ResponseBody
    public Object menuList(@PathVariable Integer id) {
        return  wechatMenuService.getMenusByPublicSignalId(id);
    }


    @RequestMapping("/menu-to-add/{id}")
    public String toMenuAdd(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return PREFIX + "menu_add.html";
    }


    @BussinessLog(value = "添加公众号菜单", key = "name", dict = Dict.WechatMenuDict)
    @RequestMapping("/menu-add")
    @Permission
    @ResponseBody
    public Object addMenu(WechatMenu wechatMenu) {
        wechatMenuService.insert(wechatMenu);
        return SUCCESS_TIP;
    }


    @BussinessLog(value = "同步公众号菜单至微信服务器", key = "name", dict = Dict.WechatMenuDict)
    @RequestMapping("/menu-synch/{id}")
    @Permission
    @ResponseBody
    public Object synchMenu(@PathVariable Integer id) {
        wechatMenuService.synch(id);
        return SUCCESS_TIP;
    }

    @RequestMapping("/menu-to-update/{id}")
    public String toMenuUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return PREFIX + "addMenu.html";
    }


    @BussinessLog(value = "修改公众号菜单", key = "name", dict = Dict.WechatMenuDict)
    @RequestMapping("/menu-update")
    @Permission
    @ResponseBody
    public Object updateMenu(WechatMenu wechatMenu) {
        wechatMenuService.updateById(wechatMenu);
        return SUCCESS_TIP;
    }


    @BussinessLog(value = "删除公众号菜单", key = "name", dict = Dict.WechatMenuDict)
    @RequestMapping("/menu-delete")
    @Permission
    @ResponseBody
    public Object deleteMenu(WechatMenu wechatMenu) {
        wechatMenuService.deleteById(wechatMenu);
        return SUCCESS_TIP;
    }


    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/menu-tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = wechatMenuService.getTree();
        return tree;
    }

}
