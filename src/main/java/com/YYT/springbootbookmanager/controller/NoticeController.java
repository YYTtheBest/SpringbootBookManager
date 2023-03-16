package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.Admin;
import com.YYT.springbootbookmanager.entity.Notice;
import com.YYT.springbootbookmanager.service.INoticeService;
import com.YYT.springbootbookmanager.utils.Result;
import com.YYT.springbootbookmanager.utils.pageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 公告
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private pageHelper pageHelper;
    @Resource
    private INoticeService noticeService;

    /**
     * 管理员公告管理页面
     *
     * @return
     */
    @GetMapping("/noticeIndexOfAdmin")
    public String noticeIndexOfAdmin() {
        return "notice/noticeIndexOfAdmin";
    }

    /**
     * 读者公告管理页面
     *
     * @return
     */
    @GetMapping("/noticeIndexOfReader")
    public String noticeIndexOfReader() {
        return "notice/noticeIndexOfReader";
    }

    /**
     * 查询所有公告，带查询
     *
     * @param topic 公告标题
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/noticeAll")
    @ResponseBody
    public Result noticeAll(String topic, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int limit) {

        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createDate");
        
        if (topic != null) {
            queryWrapper.like(true, "topic", topic);
        }
        Result result = pageHelper.defaultPage(page, limit, noticeService, queryWrapper);

        return result;

    }

    /**
     * 添加公告处理，时间默认为当前时间
     *
     * @param notice 公告信息
     * @return
     */
    @PostMapping("/addNotice")
    @ResponseBody
    public Result addNotice(@RequestBody Notice notice, HttpSession session) {
        Admin user = (Admin) session.getAttribute("user");

        System.out.println(notice);

        boolean save = noticeService.save(notice.setCreateDate(LocalDateTime.now()).setAuthor(user.getUsername()));

        if (save) {
            return Result.ok("消息添加成功");
        } else {
            return Result.error();
        }
    }

    /**
     * 添加通知
     *
     * @return
     */
    @GetMapping("/addNotice")
    public String addNotice() {
        return "notice/noticeAdd";
    }

    /**
     * 删除公告
     *
     * @param ids 公告ID
     * @return
     */
    @PostMapping("/deleteNotice")
    @ResponseBody
    public Result deleteNotice(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean b = noticeService.removeByIds(idList);
        if (b) {
            return Result.ok("消息删除成功");
        } else {
            return Result.error();
        }
    }

    /**
     * 查看公告详询
     *
     * @param id 公告ID
     * @param m
     * @return
     */
    @GetMapping("/queryNoticeById")
    public String queryNoticeById(int id, Model m) {
        Notice notice = noticeService.getById(id);
        m.addAttribute("notice", notice);
        return "notice/updateNoticce";

    }

}
