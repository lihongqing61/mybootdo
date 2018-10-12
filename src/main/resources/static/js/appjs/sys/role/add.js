$(function () {
	//1. 加载菜单树
    getMenuTreeData();

    //2. 校验输入项
    validateRule();
});

function getMenuTreeData() {
	$.post("/sys/menu/tree", null, function(menuTree) {
        loadMenuTree(menuTree);
	}, 'json');
}

/**
 * 加载树
 * @param menuTree
 */
function loadMenuTree(menuTree) {
    $("#menuTree").jstree({
        "core": {"data": menuTree},      // 显示树
        "checkbox": {"three_state": true},   //
        "plugins": ["wholerow", "checkbox"]     // 前面显示复选框
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            roleName : {
                required : true
            }
        },
        messages : {
            roleName : {
                required : icon + "请输入角色名"
            }
        }
    });
}

$.validator.setDefaults({
    submitHandler: function () {
        getAllSelectNodes();
        save();
    }
});

function getAllSelectNodes () {
    var ref = $('#menuTree').jstree(true); // 获得整个树
    menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
    $("#menuTree").find(".jstree-undetermined").each(function(i, element) {
        menuIds.push($(element).closest('.jstree-node').attr("id"));
    });
}

function save() {
    $('#menuIds').val(menuIds);
    var role = $('#signupForm').serialize();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/sys/role/save",
        data : role, // 你的formid

        async : false,
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.msg(data.msg);
            }
        }
    });
}