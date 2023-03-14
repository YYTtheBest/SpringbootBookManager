package com.YYT.springbootbookmanager.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.YYT.springbootbookmanager.entity.Admin;
import com.YYT.springbootbookmanager.entity.ReaderInfo;
import com.YYT.springbootbookmanager.service.IAdminService;
import com.YYT.springbootbookmanager.service.IReaderInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录
 */
@Controller
@Log
public class LoginController {

    @Resource
    private IAdminService adminService;

    @Resource
    private IReaderInfoService readerInfoService;

    /**
     * 登录页面
     *
     * @return html
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录传值处理
     *
     * @param req   包含“验证码”，”用户名“，”密码“，”用户类型“
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(HttpServletRequest req, Model model) {
        LineCaptcha verity = (LineCaptcha) req.getSession().getAttribute("verity");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("captcha");
        String type = req.getParameter("type");
        HttpSession session = req.getSession();

        if (!verity.verify(req.getParameter("VerifyCode"))) {
            model.addAttribute("msg", "验证码不正确");
            return "login";
//            return "login";
        } else {
            if (type.equals("1")) {
                Admin one = null;
                try {
                    one = adminService.getOne(new QueryWrapper<Admin>().eq("username", username).eq("password", password));
                } catch (Exception e) {
                }
                if (one == null) {
                    model.addAttribute("msg", "用户名或密码错误");
                    return "login";

                } else {
                    session.setAttribute("user", one);
                    session.setAttribute("type", "admin");
                    model.addAttribute("type", "admin");
                }
            } else {//来自读者信息表
                ReaderInfo readerInfo;
                try {
                    readerInfo = readerInfoService.getOne(new QueryWrapper<ReaderInfo>().eq("username", username).eq("password", password));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (readerInfo == null) {
                    model.addAttribute("msg", "用户名或密码错误");
                    return "login";

                }
                session.setAttribute("user", readerInfo);
                session.setAttribute("type", "reader");
                model.addAttribute("type", "reader");

            }
            return "index";
        }
    }

    /**
     * 退出登录
     *
     * @param req
     * @return
     */
    @GetMapping("/logout")
    public Object logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "/login";
    }

    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    @RequestMapping("/verifycode")
    public void verifycode(HttpServletRequest request, HttpServletResponse response) {
        //设置响应头
        response.setHeader("Pragma", "no-cache");
        //设置响应头
        response.setHeader("Cache-Control", "no-cache");
        //在代理服务器端防止缓冲
        response.setDateHeader("Expires", 0);
        //设置响应内容类型
        response.setContentType("image/jpeg");
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 36);
        lineCaptcha.setGenerator(randomGenerator);
        request.getSession().setAttribute("verity", lineCaptcha);
        try {
            response.getOutputStream().write(lineCaptcha.getImageBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
