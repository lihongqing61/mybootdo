$(function () {
	//1. 加载菜单树
	loadMenuTree();
});

function loadMenuTree() {
	$.post("/sys/menu/tree", null, function(menuTree) {
        loadMenuTree(menuTree);
	}, 'json');
}

function loadMenuTree(menuTree) {

}