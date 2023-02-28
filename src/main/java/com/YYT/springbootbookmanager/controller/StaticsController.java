package com.YYT.springbootbookmanager.controller;

import com.YYT.springbootbookmanager.entity.BookInfo;
import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.YYT.springbootbookmanager.service.ITypeInfoService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Log
public class StaticsController {
    @Resource
    private IBookInfoService bookInfoService;
    @Resource
    private ITypeInfoService typeInfoService;

    @GetMapping("/staticIndex")
    public Object staticIndex(Model model) {
//        List<BookInfo> bookCountByTypes = bookInfoService.getBookCountByTypes();
//        model.addAttribute("lists", bookCountByTypes);
        return "staticIndex";
    }
}
