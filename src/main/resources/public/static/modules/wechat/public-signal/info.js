/**
 * 初始化部门详情对话框
 */
var InfoDlg = {
    InfoData : {},
    zTreeInstance : null,
    validateFields: {
        simplename: {
            validators: {
                notEmpty: {
                    message: '部门名称不能为空'
                }
            }
        },
        fullname: {
            validators: {
                notEmpty: {
                    message: '部门全称不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '上级名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
InfoDlg.clearData = function() {
    this.InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoDlg.set = function(key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoDlg.close = function() {
    parent.layer.close(window.parent.Public.layerIndex);
}

/**
 * 点击部门ztree列表的选项时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
InfoDlg.onClick = function(e, treeId, treeNode) {
    $("#pName").attr("value", InfoDlg.zTreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
}

/**
 * 显示部门选择的树
 *
 * @returns
 */
InfoDlg.showSelectTree = function() {
    var pName = $("#pName");
    var pNameOffset = $("#pName").offset();
    $("#parentMenu").css({
        left : pNameOffset.left + "px",
        top : pNameOffset.top + pName.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}

/**
 * 隐藏部门选择的树
 */
InfoDlg.hideSelectTree = function() {
    $("#parentMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

/**
 * 收集数据
 */
InfoDlg.collectData = function() {
    this.set('id').set('simplename').set('fullname').set('tips').set('num').set('pid');
}

/**
 * 验证数据是否为空
 */
InfoDlg.validate = function () {
    $('#InfoForm').data("bootstrapValidator").resetForm();
    $('#InfoForm').bootstrapValidator('validate');
    return $("#InfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加部门
 */
InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/public-signal/add", function(data){
        Feng.success("添加成功!");
        window.parent.Public.table.refresh();
        InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/public-signal/update", function(data){
        Feng.success("修改成功!");
        window.parent.Public.table.refresh();
        InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.InfoData);
    ajax.start();
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "parentMenu" || $(
            event.target).parents("#parentMenu").length > 0)) {
        InfoDlg.hideSelectTree();
    }
}

$(function() {
    Feng.initValidator("InfoForm", InfoDlg.validateFields);
    var ztree = new $ZTree("parentMenuTree", "/public-signal/tree");
    ztree.bindOnClick(InfoDlg.onClick);
    ztree.init();
    InfoDlg.zTreeInstance = ztree;
});
