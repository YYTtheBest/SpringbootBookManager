package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.Notice;
import com.YYT.springbootbookmanager.service.INoticeService;
import com.YYT.springbootbookmanager.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 公告 前端控制器
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private INoticeService noticeService;

    /**
     * 管理员公告管理页面
     * @return
     */
    @GetMapping("/noticeIndexOfAdmin")
    public String noticeIndexOfAdmin() {
        return "notice/noticeIndexOfAdmin";
    }
    /**
     * 读者公告管理页面
     * @return
     */
    @GetMapping("/noticeIndexOfReader")
    public String noticeIndexOfReader() {
        return "notice/noticeIndexOfReader";
    }

    /**
     * 查询所有通知
     *
     * @return
     */
//    todo:添加标题查询功能
    @GetMapping("/noticeAll")
    @ResponseBody
    public Result noticeAll() {
        List<Notice> list = noticeService.list();
        return Result.ok(list);

    }

    @RequestMapping("/addNotice")
    @ResponseBody
    public Result addNotice(@RequestBody Notice notice) {

        boolean save = noticeService.save(notice.setCreateDate(LocalDateTime.now()));
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
    @ResponseBody
    public Result addNotice() {
        return Result.ok("通知添加页面成功");
    }

    @GetMapping("/deleteNotice")
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

    @GetMapping("/queryNoticeById")
    public String queryNoticeById(int id, Model m) {
        Notice notice = noticeService.getById(id);
        m.addAttribute("notice", notice);
        return "notice/updateNoticce";

    }

}
