/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.bean.DosingGuideline;
import cn.wmyskxz.springboot.dao.DosingGuidelineDao;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author ZijinDesktop2
 * @date 2021/5/4 12:05
 */
@Controller
public class DosingGuidelineController {
    @Autowired
    private ServletContext servletContext;
    public ServletContext getServletContext() {
        return servletContext;
    }
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    private DosingGuidelineDao dosingGuidelineDao = new DosingGuidelineDao();

    @RequestMapping("/dosing_guideline")
    public String DosingGuideline(@NotNull Model m) {

//        List<String> drugLabelsContent = null;
//        try {
//            drugLabelsContent = Files.readAllLines(Path.of(getServletContext().getRealPath("/WEB-INF/dosingGuideline.data")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        List<Object> drugLabels = drugLabelsContent.stream().map(x -> {
//            Map map = gson.fromJson(x, Map.class);
//            return map.get("data");
//        }).collect(toList());
//        m.addAttribute("dosing_Guidelines", drugLabels);

        List<DosingGuideline> dosingGuidelines = dosingGuidelineDao.findAll();
        m.addAttribute("dosing_Guidelines", dosingGuidelines);

        return "dosing_guideline";
    }
}
