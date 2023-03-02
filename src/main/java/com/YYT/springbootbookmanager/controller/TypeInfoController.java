package com.YYT.springbootbookmanager.controller;


import com.YYT.springbootbookmanager.entity.TypeInfo;
import com.YYT.springbootbookmanager.service.impl.TypeInfoServiceImpl;
import com.YYT.springbootbookmanager.utils.BaseUtils;
import com.YYT.springbootbookmanager.utils.Result;
import com.YYT.springbootbookmanager.utils.pageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 图书类型表 前端控制器
 * </p>
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Controller
@RequestMapping("/type-info")
public class TypeInfoController {
    @Resource
    private pageHelper pageHelper;
    @Resource
    private TypeInfoServiceImpl typeInfoService;

    @GetMapping("/typeIndex")
    public Object typeIndex() {

        return "type/typeIndex";
    }

    @RequestMapping("/typeAll")
    @ResponseBody
    public Result typeAll(String name, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int limit) {
        QueryWrapper<TypeInfo> queryWrapper = new QueryWrapper<>();
        if (name != null) {
            queryWrapper.like(true, "tname", name);
        }
        Result result = pageHelper.defaultPage(page, limit, typeInfoService, queryWrapper);


        return result;
    }

    /**
     * 添加书籍类型
     *
     * @param model
     * @return
     */
    @GetMapping("/addType")
    public Object typeAddType(Model model) {
        model.addAttribute("msg", "type添加页面");
        return "type/typeadd";

    }

    /**
     * 添加类型
     *
     * @param type
     * @return
     */
    @PostMapping("/addType")
    @ResponseBody
    public Result addType(TypeInfo type) {
        boolean save = typeInfoService.save(type);
        return BaseUtils.OkError(save, null);
    }

    /**
     * 通过ID获取type信息用于查看或修改
     * GET http://127.0.0.1:8090/type-info/queryTypeById
     *
     * @param id
     * @param m
     * @return
     */
    @GetMapping("/queryTypeById")
    public String queryTypeById(int id, Model m) {
        TypeInfo type = typeInfoService.getById(id);
        m.addAttribute("type", type);
        return "type/updateType";

    }

    /**
     * 修改提交功能
     *
     * @param info
     * @return
     */
    @RequestMapping("/updateTypeSubmit")
    @ResponseBody
    public Result updateTypeSubmit(@RequestBody TypeInfo info) {
        boolean b = typeInfoService.updateById(info);
        return BaseUtils.OkError(b, null);

    }

    /**
     * 删除功能
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteType")
    @ResponseBody
    public Result deleteType(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        boolean b = typeInfoService.removeByIds(list);
        return BaseUtils.OkError(b, null);

    }
}
