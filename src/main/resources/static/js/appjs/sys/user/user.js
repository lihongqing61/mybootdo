var prefix = "/sys/user";

$(function() {
    //1. 加载部门组织架构树
	getTreeData();
	
	//2. 加载用户列表
    var deptId = '';
    loadList(deptId);
});

function loadList(deptId) {
    $("#exampleTable").bootstrapTable({
		method: "get",				// 请求方法
		url: prefix + "/list",		//请求接口
		dataType: "json",			//返回数据类型

        pagination : true, 			// 设置为true会在底部显示分页条
        pageSize : 10, 				// 如果设置了分页，每页数据条数
        pageNumber : 1, 			// 如果设置了分布，首页页码

        toolbar : '#exampleToolbar',

        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 有此属性 则可显示数据sidePagination
		striped: true,				// 设置为true会有隔行变色效果
        iconSize : 'outline',
        pagination : true, 			// 设置为true会在底部显示分页条
        singleSelect : false, 		// 设置为true将禁止多选
        showColumns : false, 		// 是否显示内容下拉框（选择显示的列）

		queryParams: function (param) {
			return {
				limit: param.limit,			// pagination, pageSize, pageNumber都传 则limit, offset才可以被传入后台
				offset: param.offset,
                deptId: deptId,
                name: $("#searchName").val(),
				sort: "gmt_create",
				order: "desc"
			}
        },
        columns : [
            {checkbox : true},
            {field : 'userId', title : '序号'},
            {field : 'name', title : '姓名' },
            {field : 'username', title : '用户名'},
            {field : 'email', title : '邮箱'},
            {field : 'gmtCreate', title : '注册时间'},
            {field : 'status', title : '状态', align : 'center', formatter : function(value, row, index) {
                    if (value == '0') {
                        return '<span class="label label-danger">禁用</span>';
                    } else if (value == '1') {
                        return '<span class="label label-primary">正常</span>';
                    }
                }
            },
            {title : '操作', field : 'id', align : 'center', formatter : function(value, row, index) {
                    var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                        + row.userId
                        + '\')"><i class="fa fa-edit "></i></a> ';
                    var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.userId
                        + '\')"><i class="fa fa-remove"></i></a> ';
                    var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
                        + row.userId
                        + '\')"><i class="fa fa-key"></i></a> ';
                    return e + d + f;
                }
            }]
	});
}

/**
 * 根据用户名称查询列表
 */
function reLoad() {
    $("#exampleTable").bootstrapTable("refresh");
}

/**
 * 加载部门树
 */
function getTreeData() {
	$.get("/system/sysDept/tree", null, function (r) {
		loadTree(r);
    }, 'json');
}

function loadTree(tree) {
	$("#jstree").jstree({
		"core": {"data": tree},
		"plugins": ["search"]
	});
	$("#jstree").jstree().open_all();

    /**
     * 点击部门 根据部门查询用户
     */
    $("#jstree").on("changed.jstree", function (e, data) {
        var opt = '';
		if (data.selected == -1) {
            opt = {query: {deptId: ''}};

		} else {
			opt = {query: {deptId: data.selected[0]}};
		}
        $("#exampleTable").bootstrapTable("refresh", opt);
    });

}

/**
 * 用户新增
 */
function add() {
    layer.open({
        type: 2,    // layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
        title: '用户新增',
     //   maxmin: true,   // 最大最小化
        shadeClose: true,   // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add'
    });
}

/**
 * 用户编辑
 */
function edit(id) {
    layer.open({
        type: 2,
        title: "用户编辑",
        shadeClose: true,
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id
    });
}

/**
 * 删除用户 单条删除
 */
function remove(id) {
    layer.confirm("确定要删除选中的记录吗?", {btn: ["确定", "取消"]}, function () {
        $.post(prefix + '/remove/'+id, null, function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.alert(r.msg);
            }
        }, 'json');
    });
}

/**
 * 批量删除用户
 */
function batchRemove() {
    // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    var row = $("#exampleTable").bootstrapTable("getSelections");
    if (row.length == 0) {
        layer.msg("请选择要删除的记录");
        return;
    }
    layer.confirm("您确定要删除选中的"+row.length+"条记录吗", {btn: ["确定", "取消"]}, function () {
        var ids = new Array();
        $.each(row, function(i, val) {
            ids[i] = val["userId"];
        });
        $.post(prefix + '/batchRemove/', {"userIds": ids}, function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.alert(r.msg);
            }
        }, 'json');
    });
}

/**
 * 密码重置
 * @param id
 */
function resetPwd(id) {

}