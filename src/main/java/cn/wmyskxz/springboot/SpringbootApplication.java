package cn.wmyskxz.springboot;

import cn.wmyskxz.springboot.crawler.DosingGuidelineCrawler;
import cn.wmyskxz.springboot.crawler.DrugLabelCrawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);

//Run the crawler when apply the project
        DrugLabelCrawler drugLabelCrawler = new DrugLabelCrawler();
        DosingGuidelineCrawler dosingGuidelineCrawler = new DosingGuidelineCrawler();

        // comment the step, if you have finished it

        // Step 1
        drugLabelCrawler.doCrawlerDrug();

        // Step 2
        drugLabelCrawler.doCrawlerDrugLabel();

        // Step 3
        dosingGuidelineCrawler.doCrawlerDosingGuidelineList();
    }

}
