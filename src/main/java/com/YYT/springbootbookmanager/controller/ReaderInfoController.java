package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.ReaderInfo;
import com.YYT.springbootbookmanager.service.IAdminService;
import com.YYT.springbootbookmanager.service.IReaderInfoService;
import com.YYT.springbootbookmanager.utils.BaseUtils;
import com.YYT.springbootbookmanager.utils.Result;
import com.YYT.springbootbookmanager.utils.pageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 读者信息（包括登录账号密码等） 前端控制器
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Controller
@RequestMapping("/reader-info")
public class ReaderInfoController {
    @Resource
    private pageHelper pageHelper;

    @Resource
    private IReaderInfoService readerInfoService;
    @Resource
    private IAdminService adminService;

    /**
     * 读者管理页面
     *
     * @param model
     * @return
     */
    @GetMapping("/readerIndex")
    public String readerIndex(Model model) {
        model.addAttribute("display", "读者首页");
        return "reader/readerIndex";


    }


    /**
     * 查询所有读者信息（带搜索）
     *
     * @param reader
     * @return
     */
    @GetMapping("/readerAll")
    @ResponseBody
    public Result ReaderAll(ReaderInfo reader, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int limit) {
        QueryWrapper<ReaderInfo> queryWrapper = new QueryWrapper<>();
        if (reader.getTel() != null) {
            queryWrapper.like(true, "tel", reader.getTel());
        }
        if (reader.getUsername() != null) {
            queryWrapper.like(true, "username", reader.getUsername());
        }
        if (reader.getReaderNumber() != null) {
            queryWrapper.like(true, "readerNumber", reader.getReaderNumber());
        }

        Result result = pageHelper.defaultPage(page, limit, readerInfoService, queryWrapper);
        return result;

    }

    /**
     * 添加读者信息的页面
     *
     * @param model
     * @return 读者信息添加页面
     */
    @GetMapping("/addreader")
    public String readerAdd(Model model) {
        model.addAttribute("display", "读者信息添加页面");
        return "reader/readerAdd";
    }

    /**
     * 添加读者，默认密码为123456
     *
     * @param readerInfo
     * @return
     */
    @RequestMapping("/addreader")
    @ResponseBody
    public Result readerAdd(@RequestBody ReaderInfo readerInfo) {
        readerInfo.setRegisterDate(LocalDateTime.now());
        readerInfo.setPassword("123456");//默认密码
        boolean save = readerInfoService.save(readerInfo);
        return BaseUtils.OkError(save, "成功，默认密码为123456");
    }

    /**
     * 通过单个ID查询读者，
     *
     * @param id
     * @param model
     * @return 可供修改的页面
     */
    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(int id, Model model) {
        ReaderInfo reader = readerInfoService.getById(id);
        model.addAttribute("readerinfo", reader);
        return "reader/updateReader";
    }

    /**
     * 更改读者信息
     *
     * @param readerInfo
     * @return code200, 成功
     */
    @RequestMapping("/updateReaderInfo")
    @ResponseBody
    public Result updateReaderInfo(@RequestBody ReaderInfo readerInfo) {
        System.out.println(readerInfo);
        boolean b = readerInfoService.updateById(readerInfo);
        return BaseUtils.OkError(b, null);
    }

    /**
     * 删除读者信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deletereader")
    @ResponseBody
    public Result deleteReader(String ids) {
        List<String> strings = Arrays.asList(ids.split(","));
        boolean b = readerInfoService.removeByIds(strings);
        return BaseUtils.OkError(b, null);
    }

    @RequestMapping("/updateReaderPwd")
    @ResponseBody
    public Result updateReaderPwd(HttpServletRequest request, @RequestBody Map<String, String> map) {
        String oldpassword = map.get("oldpassword");
        String newPassword = map.get("newPassword");

        HttpSession session = request.getSession();
        ReaderInfo user = (ReaderInfo) session.getAttribute("user");
        ReaderInfo readerInfo = readerInfoService.getById(user.getId());
        if (oldpassword.equals(readerInfo.getPassword())) {
            user.setPassword(newPassword);
            boolean b = readerInfoService.updateById(user);
            return BaseUtils.OkError(b, null);
        } else {

            return Result.error("原密码不正确");
        }
    }


    @GetMapping("session")
    @ResponseBody
    public Result session(HttpServletRequest request) {
        request.getSession().setAttribute("user", readerInfoService.getById(8));
        return Result.ok();
    }
}
