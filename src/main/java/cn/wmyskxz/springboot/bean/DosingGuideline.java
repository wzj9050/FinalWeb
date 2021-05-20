package cn.wmyskxz.springboot.bean;

public class DosingGuideline {

    public DosingGuideline() {
    }

    public DosingGuideline(String id, String objCls, String name, boolean recommendation, String drugId, String source, String summaryMarkdown, String textMarkdown, String raw) {
        this.id = id;
        this.objCls = objCls;
        this.name = name;
        this.recommendation = recommendation;
        this.drugId = drugId;
        this.source = source;
        this.summaryMarkdown = summaryMarkdown;
        this.textMarkdown = textMarkdown;
        this.raw = raw;
    }

    private String id;
    private String objCls;
    private String name;
    private boolean recommendation;
    private String drugId;
    private String source;
    private String summaryMarkdown;
    private String textMarkdown;
    private String raw;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjCls() {
        return objCls;
    }

    public void setObjCls(String objCls) {
        this.objCls = objCls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSummaryMarkdown() {
        return summaryMarkdown;
    }

    public void setSummaryMarkdown(String summaryMarkdown) {
        this.summaryMarkdown = summaryMarkdown;
    }

    public String getTextMarkdown() {
        return textMarkdown;
    }

    public void setTextMarkdown(String textMarkdown) {
        this.textMarkdown = textMarkdown;
    }
}
