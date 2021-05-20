package cn.wmyskxz.springboot.dao;


import cn.wmyskxz.springboot.bean.DosingGuideline;
import cn.wmyskxz.springboot.dbutils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DosingGuidelineDao extends BaseDao {

    private static final Logger log = LoggerFactory.getLogger(DosingGuidelineDao.class);

    public boolean existsById(String id) {
        return super.existsById(id, "dosing_guideline");
    }

    public void saveDosingGuideline(DosingGuideline dosingGuideline) {
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into dosing_guideline (id,obj_cls,name,recommendation,drug_id,source,summary_markdown,text_markdown,raw) values (?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, dosingGuideline.getId());
                preparedStatement.setString(2, dosingGuideline.getObjCls());
                preparedStatement.setString(3, dosingGuideline.getName());
                preparedStatement.setBoolean(4, dosingGuideline.isRecommendation());
                preparedStatement.setString(5, dosingGuideline.getDrugId());
                preparedStatement.setString(6, dosingGuideline.getSource());
                preparedStatement.setString(7, dosingGuideline.getSummaryMarkdown());
                preparedStatement.setString(8, dosingGuideline.getTextMarkdown());
                preparedStatement.setString(9, dosingGuideline.getRaw());
                preparedStatement.execute();
            } catch (SQLException e) {
                log.info("", e);
            }
        });

    }
    public List<DosingGuideline> findAll() {
        List<DosingGuideline> dosingGuidelines = new ArrayList<>();
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select id,obj_cls,name,recommendation,drug_id,source,summary_markdown,text_markdown,raw from dosing_guideline");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String objCls = resultSet.getString("obj_cls");
                    String name = resultSet.getString("name");
                    boolean recommendation = resultSet.getBoolean("recommendation");
                    String drugId = resultSet.getString("drug_id");
                    String source = resultSet.getString("source");
                    String summaryMarkdown = resultSet.getString("summary_markdown");
                    String textMarkdown = resultSet.getString("text_markdown");
                    String raw = resultSet.getString("raw");
                    DosingGuideline dosingGuideline = new DosingGuideline(id, objCls, name, recommendation, drugId, source, summaryMarkdown, textMarkdown, raw);
                    dosingGuidelines.add(dosingGuideline);
                }
            } catch (SQLException e) {
                log.info("", e);
            }
        });
        return dosingGuidelines;
    }

}
