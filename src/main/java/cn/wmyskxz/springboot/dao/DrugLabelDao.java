package cn.wmyskxz.springboot.dao;


import cn.wmyskxz.springboot.bean.DrugLabel;
import cn.wmyskxz.springboot.dbutils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugLabelDao extends BaseDao {

    private static final Logger log = LoggerFactory.getLogger(DrugLabelDao.class);

    public boolean existsById(String id) {
        return super.existsById(id, "drug_label");
    }

    public void saveDrugLabel(DrugLabel drugLabel) {
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into drug_label (id,name,obj_cls,alternate_drug_available,dosing_information,prescribing_markdown,source,text_markdown,summary_markdown,raw,drug_id) values (?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, drugLabel.getId());
                preparedStatement.setString(2, drugLabel.getName());
                preparedStatement.setString(3, drugLabel.getObjCls());
                preparedStatement.setBoolean(4, drugLabel.isAlternateDrugAvailable());
                preparedStatement.setBoolean(5, drugLabel.isDosingInformation());
                preparedStatement.setString(6, drugLabel.getPrescribingMarkdown());
                preparedStatement.setString(7, drugLabel.getSource());
                preparedStatement.setString(8, drugLabel.getTextMarkdown());
                preparedStatement.setString(9, drugLabel.getSummaryMarkdown());
                preparedStatement.setString(10, drugLabel.getRaw());
                preparedStatement.setString(11, drugLabel.getDrugId());
                preparedStatement.execute();
            } catch (SQLException e) {
                log.info("", e);
            }
        });

    }

    public List<DrugLabel> findAll() {
        List<DrugLabel> drugLabels = new ArrayList<>();
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("select id, name, obj_cls, alternate_drug_available, dosing_information, prescribing_markdown, source, text_markdown, summary_markdown, raw, drug_id from drug_label");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String obj_cls = resultSet.getString("obj_cls");
                    boolean alternate_drug_available = resultSet.getBoolean("alternate_drug_available");
                    boolean dosing_information = resultSet.getBoolean("dosing_information");
                    String prescribing_markdown = resultSet.getString("prescribing_markdown");
                    String source = resultSet.getString("source");
                    String text_markdown = resultSet.getString("text_markdown");
                    String summary_markdown = resultSet.getString("summary_markdown");
                    String raw = resultSet.getString("raw");
                    String drug_id = resultSet.getString("drug_id");
                    DrugLabel drugLabel = new DrugLabel(id, name, obj_cls, alternate_drug_available, dosing_information, prescribing_markdown, source, text_markdown, summary_markdown, raw, drug_id);
                    drugLabels.add(drugLabel);
                }
            } catch (SQLException e) {
                log.info("", e);
            }
        });
        return drugLabels;
    }
}
