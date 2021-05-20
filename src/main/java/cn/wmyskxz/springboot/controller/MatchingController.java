/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.bean.DrugLabel;
import cn.wmyskxz.springboot.bean.Sample;
import cn.wmyskxz.springboot.dao.AnnovarDao;
import cn.wmyskxz.springboot.dao.DrugLabelDao;
import cn.wmyskxz.springboot.dao.SampleDao;

//import cn.wmyskxz.springboot.servlet.DispatchServlet;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZijinDesktop2
 * @date 2021/4/22 22:15
 */
@Controller
public class MatchingController {






    private static final Logger log = LoggerFactory.getLogger(MatchingController.class);

    private final SampleDao sampleDao = new SampleDao();
    private final AnnovarDao annovarDao = new AnnovarDao();
    private final DrugLabelDao drugLabelDao = new DrugLabelDao();

//    public void register(DispatchServlet.Dispatcher dispatcher) {
//        dispatcher.registerPostMapping("/upload", this::uploadAnnovarOutput);
//        dispatcher.registerGetMapping("/matchingIndex", this::matchingIndex);
//        dispatcher.registerGetMapping("/matching", this::matching);
//        dispatcher.registerGetMapping("/samples", this::samples);
//
//    }

//    public void matchingIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.getRequestDispatcher("/views/matching_index.jsp").forward(request, response);
//    }
    @RequestMapping("/matchingIndex")
    public String Match() {

        return "matching_index";
    }

//    public void samples(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        List<Sample> samples = sampleDao.findAll();
//        request.setAttribute("samples", samples);
//        request.getRequestDispatcher("/views/samples.jsp").forward(request, response);
//    }



    @RequestMapping("/matching")
    public String matching(HttpServletRequest request, HttpServletResponse response, Model m) throws IOException, ServletException {
        String sampleIdParameter = request.getParameter("sampleId");
        if (sampleIdParameter == null) {
            return "samples";
        }
        //在Response响应(response.getOutputStream())之前进行Session创建(request.getSession())。
        request.getSession();
        Integer sampleId = null;
        try {
            sampleId = Integer.valueOf(sampleIdParameter);
        } catch (NumberFormatException e) {
            response.sendRedirect("samples");
            return "samples";//不知道该到哪一页。。
        }
        List<String> refGenes = annovarDao.getRefGenes(sampleId);
        if (refGenes.isEmpty()) {
            response.sendRedirect("samples");
            return "samples";//不知道该到哪一页。。
        }
        List<DrugLabel> drugLabels = drugLabelDao.findAll();
        List<DrugLabel> matched = doMatch(refGenes, drugLabels);
        m.addAttribute("matched", matched);
        m.addAttribute("sample", sampleDao.findById(sampleId));
        return "matching_index_search";
    }


    private List<DrugLabel> doMatch(List<String> refGenes, List<DrugLabel> drugLabels) {
        List<DrugLabel> matchedLabels = new ArrayList<>();
        for (DrugLabel drugLabel : drugLabels) {
            boolean matched = false;
            for (String gene: refGenes) {
                if (drugLabel.getSummaryMarkdown().contains(gene)) {
                    matched = true;
                }
            }
            if (matched) {
                matchedLabels.add(drugLabel);
            }
        }
        return matchedLabels;
    }
    @RequestMapping("/upload")
    public String uploadAnnovarOutput(HttpServletRequest request, HttpServletResponse response, Model m) throws IOException, ServletException {
        String uploadedBy = request.getParameter("uploaded_by");
        if (uploadedBy == null || uploadedBy.isBlank()) {
            m.addAttribute("validateError", "Uploaded by can not be blank");

            return "matching_index_error";
        }
        Part requestPart = request.getPart("annovar");
        if (requestPart == null) {
            m.addAttribute("validateError", "annovar output file can not be blank");

            return "matching_index_error";
        }
        InputStream inputStream = requestPart.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String content = new String(bytes);
        int sampleId = sampleDao.save(uploadedBy);
        request.getSession();
        try {
            annovarDao.save(sampleId, content);
        } catch (ArrayIndexOutOfBoundsException e) {
            m.addAttribute("validateError", "annovar output file is invalid");

            return "matching_index_error";
        }
        response.sendRedirect("matching?sampleId=" + sampleId);
        return "samples";
    }



}
