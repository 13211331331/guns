
var InfoDlg = {
    InfoData : {},
    zTreeInstance : null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '菜单名称不能为空'
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


InfoDlg.onClick = function(e, treeId, treeNode) {
    $("#pName").attr("value", InfoDlg.zTreeInstance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
}


InfoDlg.hideSelectTree = function() {
    $("#parentMenu").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
}

/**
 * 收集数据
 */
InfoDlg.collectData = function() {
    this.set('id').set('name').set('key').set('url').set('pid').set('type').set('publicSignalId');
}

/**
 * 验证数据是否为空
 */
InfoDlg.validate = function () {
    $('#InfoForm').data("bootstrapValidator").resetForm();
    $('#InfoForm').bootstrapValidator('validate');
    return $("#InfoForm").data('bootstrapValidator').isValid();
}


InfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/public-signal/menu-add", function(data){
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
});
