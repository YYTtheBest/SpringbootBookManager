package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.BookInfo;
import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.YYT.springbootbookmanager.utils.BaseUtils;
import com.YYT.springbootbookmanager.utils.Result;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**

 * 图书信息 前端控制器
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Log
@Controller
@RequestMapping("/book-info")
public class BookInfoController {

    @Resource
    private IBookInfoService bookService;

    /**
     * 图书信息页面
     * @return
     */
    @GetMapping("/bookIndex")
    public Object bookIndex() {
        return "book/bookIndex";
    }



    /**
     * 查询所有书籍，带搜索
     *
     * @param bookInfo
     * @param pageNum
     * @param limit
     * @return JSON（查询所有书籍）
     */
    @RequestMapping("/bookAll")
    @ResponseBody
    public Object bookAll(BookInfo bookInfo, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "15") int limit) {
        List<BookInfo> bookInfos = bookService.queryBookInfoWithTypeInfo(bookInfo);
        return Result.ok(bookInfos);
    }

    /**
     * 添加书籍页面
     * @return bookAdd.html
     */
    @GetMapping("/addbook")
    public String addBookPage(Model model) {
        return "book/bookAdd";
    }

    /**
     * 添加书
     *
     * @param b
     * @return Result.ok();Result.error();
     */
    @RequestMapping("/addbook")
    @ResponseBody
    public Result addBook(@RequestBody BookInfo b) {
        log.warning(b.toString());
        boolean save = bookService.save(b);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    /**
     * 修改书籍信息
     *
     * @param b
     * @return
     */
    @RequestMapping("/updateBook")
    @ResponseBody
    public Result updateBook(@RequestBody BookInfo b) {
        boolean update = bookService.updateById(b);
        return BaseUtils.OkError(update, null);
    }

    /**
     * 删除书籍
     *
     * @param id
     * @return JSON(删除书籍)
     */
    @RequestMapping("/deleteBook")
    @ResponseBody
    public Result deleteBook(@RequestParam String id) {
        List<String> idList = Arrays.asList(id.split(","));
        boolean b = bookService.removeByIds(idList);
        if (b) {
            return Result.ok("书籍删除成功");
        } else {
            return Result.error();
        }
    }

    /**
     * 通过id查找借阅记录,更新书籍界面
     * @param id
     * @param model
     * @return updateBookInfo.html
     */
    @GetMapping("/queryBookInfoById")
    public String queryBookInfoById(int id,Model model){
        BookInfo bookInfoById = bookService.getById(id);
        model.addAttribute("book", bookInfoById);
        return "book/updateBookInfo";
    }
}
