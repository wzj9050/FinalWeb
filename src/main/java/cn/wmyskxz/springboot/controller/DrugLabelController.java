/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.bean.DrugLabel;
import cn.wmyskxz.springboot.dao.DrugLabelDao;
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
 * @date 2021/5/3 19:53
 */
@Controller
public class DrugLabelController {

    @Autowired

    private ServletContext servletContext;

    public ServletContext getServletContext() {
        return servletContext;
    }
    private final DrugLabelDao drugLabelDao = new DrugLabelDao();
    @RequestMapping("/drug_labels")
    public String drugLabels(@NotNull Model m) {

//        List<String> drugLabelsContent = null;
//
//        try {
//            drugLabelsContent = Files.readAllLines(Path.of(getServletContext().getRealPath("/WEB-INF/drugLabels.data")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        List<Map> drugLabels = drugLabelsContent.stream().map(x -> gson.fromJson(x, Map.class)).collect(toList());
//        m.addAttribute("drug_labels", drugLabels);



        List<DrugLabel> drugLabels = drugLabelDao.findAll();
        m.addAttribute("drug_labels", drugLabels);
        return "drug_labels";
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

