var prefix = "/sys/user";

$(function() {
	getTreeData();
});

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