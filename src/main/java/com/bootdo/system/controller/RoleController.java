package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lihq on 2018/10/10 16:15
 * Description: 角色控制器
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    private String prefix = "system/role/";

    @GetMapping("")
    public String toRolePage() {
        return prefix + "role";
    }
}
