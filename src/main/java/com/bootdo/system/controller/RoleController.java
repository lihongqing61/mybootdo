package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * Created by Lihq on 2018/10/10 16:15
 * Description: 角色控制器
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    private String prefix = "system/role/";

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String toRolePage() {
        return prefix + "role";
    }

    /**
     * 角色列表查询
     * @param paramMap
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:role:role")
    public PageUtils list(@RequestParam Map<String, Object> paramMap) {
        return roleService.listAll(paramMap);
    }

    /**
     * 跳转到角色新增页面
     * @return
     */
    @GetMapping("/add")
    public String toAddPage() {
        return prefix + "add";
    }
}
