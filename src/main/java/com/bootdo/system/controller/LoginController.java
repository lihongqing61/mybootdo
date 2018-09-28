package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: Lihq
 * @Date: 2018/9/28 21:02
 * @Description: 登录控制器
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 访问首页 重定向到/blog
     * @return
     */
    @GetMapping({"", "/"})
    public String index() {
        return "redirect:/blog";
    }
}
