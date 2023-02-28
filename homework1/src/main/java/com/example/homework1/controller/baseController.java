package com.example.homework1.controller;

import com.example.homework1.entity.Teacher;
import com.example.homework1.mapper.TeacherMapper;
import com.example.homework1.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class baseController {
    @Resource
    TeacherMapper teacherMapper;

    @GetMapping("/index")
    public String index() {
        return "adminIndex";
    }

    @GetMapping("/adminAll")
    @ResponseBody
    public Result adminAll() {

        List<Teacher> teachers = teacherMapper.selectAll();
        return Result.ok(teachers);

    }

    @GetMapping("/queryAdminById")
    public String queryAdminById(String id, Model model) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(Long.valueOf(id));
        model.addAttribute("teacher", teacher);
        return "updateAdmin";
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Teacher teacher) {
        int i = teacherMapper.updateByPrimaryKey(teacher);
        if (i <= 0) {
            return Result.error();
        } else {
            return Result.ok();
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String ids) {
        int i = teacherMapper.deleteByPrimaryKey(Long.valueOf(ids));
        if (i <= 0) {
            return Result.error();
        } else {
            return Result.ok();
        }
    }
}
