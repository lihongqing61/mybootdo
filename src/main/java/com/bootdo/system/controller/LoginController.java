package com.bootdo.system.controller;

import com.bootdo.common.annonation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Auther: Lihq
 * @Date: 2018/9/28 21:02
 * @Description: 登录控制器
 */
@Controller
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;

    @Autowired
    private FileService fileService;
    /**
     * 访问首页 重定向到/blog
     * @return
     */
    @GetMapping({"", "/"})
    public String welcome() {
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
    @PostMapping("/login")
    @ResponseBody
    @Log("用户登录")
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

    /**
     * 登录完成后 请求访问主页
     * @param model
     * @return
     */

    @GetMapping("/index")
    @Log("请求访问主页")
    public String index(Model model) {
        // 查询用户菜单权限
        List<Tree<MenuDO>> menuList = menuService.listMenuTree(getUserId());

        // TODO: 文件上传
        FileDO file = fileService.findFileById(getUser().getPicId());
        model.addAttribute("menus", menuList);
        model.addAttribute("name", getName());
        model.addAttribute("username", getUsername());
        return "index_v1";
    }

    /**
     * 返回主页
     * @return
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
