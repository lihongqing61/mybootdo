var prefix = "/sys/role";

$(function () {
    loadList();
});

function loadList() {
    $("#exampleTable").bootstrapTable({
        method: "get",
        url: prefix + "/list",
        dataType: "json",
        pagination: true, 			// 设置为true会在底部显示分页条
        pageSize: 10, 				// 如果设置了分页，每页数据条数
        pageNumber: 1, 			// 如果设置了分布，首页页码

        toolbar: '#exampleToolbar',

        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 有此属性 则可显示数据sidePagination
        striped: true,				// 设置为true会有隔行变色效果
        iconSize: 'outline',
        pagination: true, 			// 设置为true会在底部显示分页条
        singleSelect: false, 		// 设置为true将禁止多选
        showColumns: false, 		// 是否显示内容下拉框（选择显示的列）
        queryParams: function (param) {
            return {
                limit: param.limit,			// pagination, pageSize, pageNumber都传 则limit, offset才可以被传入后台
                offset: param.offset,
                //   deptId: deptId,
                name: $("#searchName").val(),
                sort: "gmt_create",
                order: "desc"
            }
        },
        columns: [
            {checkbox: true},
            {field: 'roleId', title: '序号'},
            {field: 'roleName', title: '角色名'},
            {field: 'remark', title: '备注'},
            {field: '', title: '权限'},
            {title: '操作', field: 'roleId', align: 'center',
                formatter: function (value, row, index) {
                    var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                        + row.roleId
                        + '\')"><i class="fa fa-edit"></i></a> ';
                    var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.roleId
                        + '\')"><i class="fa fa-remove"></i></a> ';
                    return e + d;
                }
            }]
    });
}

function add() {
    layer.open({
        type: 2,    // layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
        title: '角色新增',
        //   maxmin: true,   // 最大最小化
        shadeClose: true,   // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add'
    });
}