package com.bootdo.common.controller;

import com.bootdo.common.domain.DeptDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lihq on 2018/9/30 11:08
 * Description: 部门控制器
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {

    private String prefix = "system/dept";

    @Autowired
    private DeptService deptService;

    /**
     * 部门组织架构树
     * @return
     */
    @GetMapping("/tree")
    @ResponseBody
    public Tree<DeptDO> deptTreeList() {
        return deptService.deptTreeList();
    }

    /**
     * 跳转到选择部门页面
     * @return
     */
    @GetMapping("/treeView")
    public String treeView() {
        return  prefix + "/deptTree";
    }
}
