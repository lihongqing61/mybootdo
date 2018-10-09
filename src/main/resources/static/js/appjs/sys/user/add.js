$(function () {
    validateRule();
});

/**
 * 通过验证后运行的函数,里面要加上表单提交的函数,否则表单不会提交
 */
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $("#roleIds").val(getCheckedRoles());
    $.post("/sys/user/save", $("#signupForm").serialize(), function(r) {
        if (r.code == 0) {
            parent.layer.msg(r.msg);
            parent.reLoad();
            parent.layer.close(parent.layer.getFrameIndex(window.name));
        } else {
            parent.layer.alert(r.msg);
        }
    }, 'json');
}

function getCheckedRoles() {
    var adIds = "";
    $("input:checkbox[name=role]:checked").each(function(i) {
        if (0 == i) {
            adIds = $(this).val();
        } else {
            adIds += ("," + $(this).val());
        }
    });
    return adIds;
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {required: true},
            username: {
                required: true, minlength: 2, remote: {
                    url : "/sys/user/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式,
                    data: {
                        username: function() {
                            return $("#username").val();
                        }
                    }
                }
            },
            password : {
                required : true,
                minlength : 6
            },
            confirm_password : {
                required : true,
                minlength : 6,
                equalTo : "#password"
            },
            email : {
                required : true,
                email : true
            },
            topic : {
                required : "#newsletter:checked",
                minlength : 2
            },
            agree : "required"
        },
        messages : {

            name : {
                required : icon + "请输入姓名"
            },
            username : {
                required : icon + "请输入您的用户名",
                minlength : icon + "用户名必须两个字符以上",
                remote : icon + "用户名已经存在"
            },
            password : {
                required : icon + "请输入您的密码",
                minlength : icon + "密码必须6个字符以上"
            },
            confirm_password : {
                required : icon + "请再次输入密码",
                minlength : icon + "密码必须6个字符以上",
                equalTo : icon + "两次输入的密码不一致"
            },
            email : icon + "请输入您的E-mail",
        }
    });
}
/**
 * 选择部门
 */
function openDept() {
	layer.open({
		type: 2,
        title:"选择部门",
        area : [ '300px', '450px' ],
        content:"/system/sysDept/treeView"
	});
}

function loadDept(deptId, deptName) {
    $("#deptId").val(deptId);
    $("#deptName").val(deptName);
}