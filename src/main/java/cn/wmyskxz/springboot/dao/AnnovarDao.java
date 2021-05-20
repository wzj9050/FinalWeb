package cn.wmyskxz.springboot.dao;


import cn.wmyskxz.springboot.dbutils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AnnovarDao extends BaseDao {

    private Logger log = LoggerFactory.getLogger(AnnovarDao.class.getSimpleName());

    public void save(int sampleId, String content) {
        String[] lines = content.split("\\r|\\n");
        DBUtils.execSQL(connection -> {
            String sql = "INSERT INTO annovar (sample_id, Chr, Start, End, Ref, Alt, `Func.refGene`, `Gene.refGene`, `GeneDetail.refGene`, `ExonicFunc.refGene`, `AAChange.refGene`, cytoBand, `1000g2015aug_all`, `1000g2015aug_afr`, `1000g2015aug_amr`, `1000g2015aug_eas`, `1000g2015aug_eur`, `1000g2015aug_sas`, ExAC_ALL, ExAC_AFR, ExAC_AMR, ExAC_EAS, ExAC_FIN, ExAC_NFE, ExAC_OTH, ExAC_SAS, avsnp150, esp6500siv2_all, esp6500siv2_ea, esp6500siv2_aa, gnomAD_exome_ALL, gnomAD_exome_AFR, gnomAD_exome_AMR, gnomAD_exome_ASJ, gnomAD_exome_EAS, gnomAD_exome_FIN, gnomAD_exome_NFE, gnomAD_exome_OTH, gnomAD_exome_SAS, SIFT_score, SIFT_converted_rankscore, SIFT_pred, Polyphen2_HDIV_score, Polyphen2_HDIV_rankscore, Polyphen2_HDIV_pred, Polyphen2_HVAR_score, Polyphen2_HVAR_rankscore, Polyphen2_HVAR_pred, LRT_score, LRT_converted_rankscore, LRT_pred, MutationTaster_score, MutationTaster_converted_rankscore, MutationTaster_pred, MutationAssessor_score, MutationAssessor_score_rankscore, MutationAssessor_pred, FATHMM_score, FATHMM_converted_rankscore, FATHMM_pred, PROVEAN_score, PROVEAN_converted_rankscore, PROVEAN_pred, VEST3_score, VEST3_rankscore, MetaSVM_score, MetaSVM_rankscore, MetaSVM_pred, MetaLR_score, MetaLR_rankscore, MetaLR_pred, `M-CAP_score`, `M-CAP_rankscore`, `M-CAP_pred`, REVEL_score, REVEL_rankscore, MutPred_score, MutPred_rankscore, CADD_raw, CADD_raw_rankscore, CADD_phred, DANN_score, DANN_rankscore, `fathmm-MKL_coding_score`, `fathmm-MKL_coding_rankscore`, `fathmm-MKL_coding_pred`, Eigen_coding_or_noncoding, `Eigen-raw`, `Eigen-PC-raw`, GenoCanyon_score, GenoCanyon_score_rankscore, integrated_fitCons_score, integrated_fitCons_score_rankscore, integrated_confidence_value, `GERP++_RS`, `GERP++_RS_rankscore`, phyloP100way_vertebrate, phyloP100way_vertebrate_rankscore, phyloP20way_mammalian, phyloP20way_mammalian_rankscore, phastCons100way_vertebrate, phastCons100way_vertebrate_rankscore, phastCons20way_mammalian, phastCons20way_mammalian_rankscore, SiPhy_29way_logOdds, SiPhy_29way_logOdds_rankscore, Interpro_domain, GTEx_V6p_gene, GTEx_V6p_tissue, gnomAD_genome_ALL, gnomAD_genome_AFR, gnomAD_genome_AMR, gnomAD_genome_ASJ, gnomAD_genome_EAS, gnomAD_genome_FIN, gnomAD_genome_NFE, gnomAD_genome_OTH, CLNALLELEID, CLNDN, CLNDISDB, CLNREVSTAT, CLNSIG, cosmic70, ICGC_Id, ICGC_Occurrence, InterVar_automated, PVS1, PS1, PS2, PS3, PS4, PM1, PM2, PM3, PM4, PM5, PM6, PP1, PP2, PP3, PP4, PP5, BA1, BS1, BS2, BS3, BS4, BP1, BP2, BP3, BP4, BP5, BP6, BP7, Otherinfo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < lines.length; i++) {
                    preparedStatement.setInt(1, sampleId);
                    String[] split = lines[i].split("\\t");
                    for (int j = 1; j <= 153; j++) {
                        preparedStatement.setString(j + 1, split[j - 1]);
                    }
                    StringJoiner otherInfo = new StringJoiner("\t");
                    for (int j = 154; j <= split.length; j++) {
                        otherInfo.add(split[j - 1]);
                    }
                    preparedStatement.setString(155, otherInfo.toString());
                    preparedStatement.addBatch();
                    if (i % 1000 == 0) {
                        preparedStatement.executeBatch();
                        connection.commit();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public List<String> getRefGenes(int sampleId) {
        String sql = "select distinct `Gene.refGene` from annovar where `ExonicFunc.refGene` != 'synonymous SNV' and sample_id = ?";
        List<String> genes = new ArrayList<>();
        DBUtils.execSQL(connection -> {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, sampleId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    genes.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return genes;
    }
}
