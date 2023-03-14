package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.BookInfo;
import com.YYT.springbootbookmanager.entity.LendList;
import com.YYT.springbootbookmanager.entity.ReaderInfo;
import com.YYT.springbootbookmanager.service.IBookInfoService;
import com.YYT.springbootbookmanager.service.ILendListService;
import com.YYT.springbootbookmanager.service.IReaderInfoService;
import com.YYT.springbootbookmanager.utils.Result;
import com.YYT.springbootbookmanager.utils.pageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 借阅记录
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Controller
@RequestMapping("/lend-list")
@Log
public class LendListController {

    @Resource
    private pageHelper pageHelper;

    @Resource
    private ILendListService lendListService;

    @Resource
    private IReaderInfoService readerInfoService;

    @Resource
    private IBookInfoService bookInfoService;


    /**
     * 借阅列表首页
     *
     * @return lendListIndex.html
     */
    @GetMapping("/lendlistIndex")
    public String lendlistIndex() {
        return "lend/lendListIndex";
    }

    /**
     * 查询借阅信息
     *
     * @param type         类型（int）
     * @param readerNumber 读者卡号
     * @param name         书名
     * @param status       书籍状态
     * @return JSON(所有的记录信息)
     */
    @GetMapping("/lendAll")
    @ResponseBody
    public Result lendAll(Integer type, String readerNumber, String name, Integer status, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int limit) {
        LendList info = new LendList();
        info.setBackType(type);
        //创建读者对象
        ReaderInfo reader = new ReaderInfo();
        reader.setReaderNumber(readerNumber);
        //把以上对象交给info
        info.setReaderInfo(reader);
        //图书对象
        BookInfo book = new BookInfo();
        book.setName(name);
        book.setStatus(status);
        info.setBookInfo(book);
        List<LendList> lendLists = lendListService.queryLengListAll(info);


        //分页查询所有的记录信息
        return Result.ok(lendLists);
    }

    /**
     * 添加借阅信息
     *
     * @return addLendList.html
     */
    @GetMapping("/addLendList")
    public String addLendList() {
        return "lend/addLendList";
    }

    /**
     * 借书信息提交
     * 1判断借阅号码是否存在
     * 2、可借的数据是否大于等于当前的借书量
     * 3、添加借书记录，同时改变书的状态信息
     * cardnumber:借书号码
     * ids：字符串 书id的集合
     */
    @ResponseBody
    @PostMapping("/addLend")
    public Result addLend(@RequestParam("readerNumber") String readerNumber, @RequestParam("ids") String ids) {
        //获取图书id的集合
        List<String> list = Arrays.asList(ids.split(","));
        //判断卡号是否存在
        QueryWrapper<ReaderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("readerNumber", readerNumber);
        ReaderInfo one = readerInfoService.getOne(queryWrapper);

        if (one == null) {
            return Result.error("卡号信息不存在");
        } else {
            ReaderInfo readerCard2 = one;
            //可借书
            for (String bid : list) {
                LendList lendList = new LendList();
                lendList.setReaderId(readerCard2.getId());//读者id
                lendList.setBookId(Integer.valueOf(bid));//书的id
                lendList.setLendDate(LocalDateTime.now());
                lendListService.save(lendList);
                //更变书的状态
                BookInfo info = bookInfoService.getById(Integer.valueOf(bid));
                //设置书的状态
                info.setStatus(1);
                System.err.println(info);
                bookInfoService.updateById(info);
            }
        }
        return Result.ok();
    }


    /**
     * 删除借阅记录
     *
     * @param ids     借阅信息的ID
     * @param bookIds 书籍的ID
     * @return JSON(删除借阅记录)
     */
    @ResponseBody
    @PostMapping("/deleteLendListByIds")
    public Result deleteLendListByIds(String ids, String bookIds) {
        List list = Arrays.asList(ids.split(","));//借阅记录的id
        List<String> blist = Arrays.asList(bookIds.split(","));//图书信息的id

        lendListService.removeByIds(list);
        for (String bid : blist) {
            BookInfo binfo = bookInfoService.getById(bid);
            binfo.setStatus(0);//改为未借出
            bookInfoService.updateById(binfo);
        }
        return Result.ok();
    }

    /**
     * 异常还书
     *
     * @param lendList 借阅信息
     * @return JSON(异常还书)
     */
    @ResponseBody
    @PostMapping("/updateLendInfoSubmit")
    public Result updateLendInfoSubmit(LendList lendList) {
        LendList lendListTemp = new LendList()
                .setId(lendList.getId())
                .setBackType(lendList.getBackType())
                .setBackDate(LocalDateTime.now())
                .setExceptRemarks(lendList.getExceptRemarks())
                .setBookId(lendList.getBookId());
        lendListService.updateById(lendListTemp);
        if (lendListTemp.getBackType() == 0 || lendListTemp.getBackType() == 1
        ) {
            BookInfo byId = bookInfoService.getById(lendListTemp.getBookId());
            byId.setStatus(0);
            bookInfoService.updateById(byId);
        }
        return Result.ok();
    }

    /**
     * 显示读者/借书的时间线
     *
     * @param flag  根据借书者或书名来显示时间线
     * @param id    书籍ID或读者ID
     * @param model
     * @return
     */
    @RequestMapping("/queryLookBookList")
    public String queryLookBookList(String flag, Integer id, Model model) {
        List<LendList> list = null;
        if (flag.equals("book")) {
            list = lendListService.queryLookBookList(null, id);
        } else {
            list = lendListService.queryLookBookList(id, null);
        }
        model.addAttribute("list", list);
        System.out.println(list);
        return "lend/lookBookList";
    }

    /**
     * 读者登录后显示时间线
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/queryLookBookList2")
    public String queryLookBookList(HttpServletRequest request, Model model) {
        ReaderInfo readerInfo = (ReaderInfo) request.getSession().getAttribute("user");
        List<LendList> list = list = lendListService.queryLookBookList(readerInfo.getId(), null);
        model.addAttribute("list", list);
        System.out.println("查询时间线" + list);

        return "lend/lookBookList";
    }

    /**
     * 还书
     *
     * @param ids     借阅记录ID
     * @param bookIds 图书ID
     * @return JSON
     */
    @ResponseBody
    @PostMapping("/backLendListByIds")
    public Result backLendListByIds(String ids, String bookIds) {
        List list = Arrays.asList(ids.split(","));//借阅记录的id
        List blist = Arrays.asList(bookIds.split(","));//图书信息的id
        lendListService.updateLendListSubmit(list, blist);
        return Result.ok();
    }


    /**
     * 异常还书页面
     *
     * @param id     借阅ID
     * @param bookId 书籍ID
     * @param model
     * @return 前端页面，通过thymeleaf添加ID和BID
     */
    @GetMapping("/excBackBook")
    public String excBackBook(@RequestParam("id") String id, @RequestParam("bookId") String bookId, Model model) {
        //获取借阅记录id
        model.addAttribute("id", id);
        model.addAttribute("bid", bookId);
        return "lend/excBackBook";
    }


}
