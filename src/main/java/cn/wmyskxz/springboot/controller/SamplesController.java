/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.bean.Sample;
import cn.wmyskxz.springboot.dao.AnnovarDao;
import cn.wmyskxz.springboot.dao.DrugLabelDao;
import cn.wmyskxz.springboot.dao.SampleDao;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ZijinDesktop2
 * @date 2021/4/22 22:15
 */
@Controller
public class SamplesController {
    private static final Logger log = LoggerFactory.getLogger(SamplesController.class);

    private final SampleDao sampleDao = new SampleDao();
    private final AnnovarDao annovarDao = new AnnovarDao();
    private final DrugLabelDao drugLabelDao = new DrugLabelDao();
    @RequestMapping("/samples")
    public String samples(@NotNull Model m) {
        List<Sample> samples = sampleDao.findAll();
        m.addAttribute("samples", samples);

        return "samples";
    }

}
