package com.YYT.springbootbookmanager.controller;

import com.YYT.springbootbookmanager.entity.Admin;
import com.YYT.springbootbookmanager.service.impl.AdminServiceImpl;
import com.YYT.springbootbookmanager.utils.Result;
import com.YYT.springbootbookmanager.utils.pageHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 管理员
 *
 * @author YeYutong
 * @since 2022-11-22
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private pageHelper pageHelper;

    @Resource
    private AdminServiceImpl adminService;

    @GetMapping("/adminIndex")
    public String adminIndex() {
        return "admin/adminIndex";
    }

    @RequestMapping("/adminAll")
    @ResponseBody
    public Result queryAdminAll(Admin admin, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int limit) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (admin.getUsername() != null) {
            queryWrapper.like(true, "username", admin.getUsername());
        }
        if (admin.getAdminType() != null) {
            queryWrapper.like(true, "adminType", admin.getAdminType());
        }
        Result result = pageHelper.defaultPage(page, limit, adminService, queryWrapper);
        return result;
    }

    @GetMapping("/adminAdd")
    public String adminAdd() {
        return "admin/adminAdd";
    }


    @RequestMapping("/addAdminSubmit")
    @ResponseBody
    public Result addBookSubmit(Admin admin) {
        adminService.save(admin);
        return Result.ok();
    }

    /**
     * 根据id查询
     */
    @GetMapping("/queryAdminById")
    public String queryAdminById(Integer id, Model model) {
        model.addAttribute("id", id);
        return "admin/updateAdmin";
    }

    /**
     * 修改提交
     */
    @RequestMapping("/updatePwdSubmit")
    @ResponseBody
    public Result updatePwdSubmit(Integer id, String oldPwd, String newPwd) {
        Admin admin = adminService.getById(id);//根据id查询对象
        if (!oldPwd.equals(admin.getPassword())) {
            return Result.error("输入的旧密码错误");
        } else {
            admin.setPassword(newPwd);
            adminService.updateById(admin);//数据库修改
            return Result.ok();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteAdminByIds")
    @ResponseBody
    public Result deleteAdminByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        adminService.removeByIds(list);
        return Result.ok();
    }

}
