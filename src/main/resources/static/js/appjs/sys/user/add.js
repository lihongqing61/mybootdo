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