/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.bean.Drug;
import cn.wmyskxz.springboot.dao.DrugDao;
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

/**
 * @author ZijinDesktop2
 * @date 2021/4/22 22:15
 */
@Controller
public class DrugController {

    @Autowired
    private ServletContext servletContext;
    public ServletContext getServletContext() {
        return servletContext;
    }
    private DrugDao drugDao = new DrugDao();
    @RequestMapping("/drugs")
    public String Drugs(@NotNull Model m) {

//        以下注释为不完整demo的springboot兼容版本，勿删
//        String drugsContent = null;
//        try {
//            drugsContent = Files.readString(Path.of(getServletContext().getRealPath("/WEB-INF/drugs.data")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Gson gson = new Gson();
//        Map drugs = gson.fromJson(drugsContent, Map.class);
//        List<Map> drugList = (List<Map>) drugs.get("data");
//        m.addAttribute("drugs", drugList);


        List<Drug> drugs = drugDao.findAll();
        m.addAttribute("drugs", drugs);
        return "drugs";
    }



    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
