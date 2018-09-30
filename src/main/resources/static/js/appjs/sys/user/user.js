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
            {
                checkbox : true
            },
            {
                field : 'userId', // 列字段名
                title : '序号' // 列标题
            },
            {
                field : 'name',
                title : '姓名'
            },
            {
                field : 'username',
                title : '用户名'
            },
            {
                field : 'email',
                title : '邮箱'
            },
            {
                field : 'gmtCreate',
                title : '注册时间'
            },
            {
                field : 'status',
                title : '状态',
                align : 'center',
                formatter : function(value, row, index) {
                    if (value == '0') {
                        return '<span class="label label-danger">禁用</span>';
                    } else if (value == '1') {
                        return '<span class="label label-primary">正常</span>';
                    }
                }
            },
            {
                title : '操作',
                field : 'id',
                align : 'center',
                formatter : function(value, row, index) {
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