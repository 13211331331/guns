/**
 * 部门管理初始化
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
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', align: 'center', valign: 'middle',width:'50px'},
        {title: '公众号', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: 'appid', field: 'appid', align: 'center', valign: 'middle', sortable: true},
        {title: 'appsecret', field: 'appsecret', align: 'center', valign: 'middle', sortable: true},
        {title: 'token', field: 'token', align: 'center', valign: 'middle', sortable: true},
        {title: 'url', field: 'url', align: 'center', valign: 'middle', sortable: true}];
};

/**
 * 检查是否选中
 */
Public.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Public.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加部门
 */
Public.openAdd = function () {
    var index = layer.open({
        type: 2,
        title: '添加公众号',
        area: ['800px', '430px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/public-signal/to-add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看部门详情
 */
Public.openDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '部门详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/public-signal/update/' + Dept.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除部门
 */
Public.delete = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/public-signal/delete", function () {
                Feng.success("删除成功!");
                Public.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("deptId",Dept.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该公众号?", operation);
    }
};


Public.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Dept.table.refresh({query: queryData});
};

Public.formParams = function() {
    var queryData = {};
    queryData['name'] = $("#name").val();
    return queryData;
}

$(function () {
    var defaultColunms = Public.initColumn();
    var table = new BSTable(Public.id, "/public-signal/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Public.formParams());
    Public.table = table.init();
});
