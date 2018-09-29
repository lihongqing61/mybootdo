package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    public R loginSubmit(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password") String password) {

        password = MD5Utils.encrypt(username, password);            // 加密
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return R.error(e.getMessage());
        }
        return R.ok();
    }
}
