/**
 * 角色管理的单例
 */
var Public = {
    id: "PublicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Public.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'50px'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true,width:'17%'},
        {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true,width:'12%'},
        {title: '菜单父编号', field: 'pcode', align: 'center', valign: 'middle', sortable: true},
        {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true,width:'15%'},
        {title: '排序', field: 'num', align: 'center', valign: 'middle', sortable: true},
        {title: '层级', field: 'levels', align: 'center', valign: 'middle', sortable: true},
        {title: '是否是菜单', field: 'isMenuName', align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Public.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Public.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Public.openAdd = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['830px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/public-signal/menu-to-add/'  + publicid
    });
    alert(publicid);
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Public.openUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/public-signal/menu-to-update/'+ publicid + '/'+ this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Public.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/public-signal/menu-delete", function (data) {
                Feng.success("删除成功!");
                Menu.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("menuId", Menu.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该菜单?", operation);
    }
};

/**
 * 搜索
 */
Public.search = function () {
    var queryData = {};

    queryData['menuName'] = $("#menuName").val();
    queryData['level'] = $("#level").val();

    Public.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Public.initColumn();
    var table = new BSTreeTable(Public.id, "/public-signal/menu-list/"+publicid, defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("pcode");
    table.setExpandAll(true);
    table.init();
    Public.table = table;
});
