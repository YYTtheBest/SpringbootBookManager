package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.BookInfo;
import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.YYT.springbootbookmanager.service.IReaderInfoService;
import com.YYT.springbootbookmanager.service.ITypeInfoService;
import com.YYT.springbootbookmanager.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 基础控制类
 *
 * @author YeYutong
 */
@Controller

public class BaseController {

    @Resource
    private IBookInfoService bookInfoService;
    @Resource
    private IReaderInfoService readerInfoService;
    @Resource
    private ITypeInfoService typeInfoService;

    /**
     * 网站初始页
     *
     * @return
     */
    @GetMapping("/index")
    public Object index() {
        return "index";
    }

    /**
     * 管理员登录的首页
     *
     * @return
     */
    @GetMapping("/welcome")
    public Object welcome(Model model) {
        long bookscount = bookInfoService.count();
        long lentcount = bookInfoService.count(new QueryWrapper<BookInfo>().eq("status", 1));
        long incount = bookInfoService.count(new QueryWrapper<BookInfo>().eq("status", 0));
        long readercount = readerInfoService.count();
        model.addAttribute("bookscount", bookscount);
        model.addAttribute("lentcount", lentcount);
        model.addAttribute("incount", incount);
        model.addAttribute("readercount", readercount);
        return "welcome";
    }

    /**
     * 图书查询页面
     * 用户登录的首页
     *
     * @return html
     */
    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /**
     * 查询书籍信息
     *
     * @param map
     * @return
     */
    @PostMapping("/search")
    @ResponseBody
    public Result searchBy(@RequestBody Map<String, String> map) {
        QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
        bookInfoQueryWrapper.like(true, "name", map.get("name"));
        List<BookInfo> list = bookInfoService.list(bookInfoQueryWrapper);
        if (list.size() == 0) {
            return Result.error("无书本信息");
        } else {
            list.forEach(e -> {
                if (e != null) {
                    e.setTypeInfo(typeInfoService.getById(e.getTypeId()));
                }
            });
            return Result.ok(list);
        }
    }

    @GetMapping("/api")
    public String api() {
        return "apis";
    }
}

