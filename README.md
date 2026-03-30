package org.nnnn.ddd.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.nnnn.ddd.model.ArrestInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CDWRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ArrestInfo getArrestInfo(final String arrId) {
        Query query = entityManager.createNativeQuery(
                "select ARR.ARR_ID, ARR.ARR_DT, ARR.ARR_PCT_CD AS ARR_PCT, ARR.ARR_SEALED_FLG, LWC.LAW_LONG_DESC AS TOP_CHARGE, PSB.PATRL_BORO_CD AS ARR_PB,  OFR.OFCR_TAX_NUM AS AO_TAX, OFR.LAST_NM AS AO_LAST_NM, OFR.FRST_NM AS AO_FRST_NM, OFR.OFCR_CMD_CD AS AO_CMD, PSN.FRST_NM AS DEFT_FRST_NM, PSN.LAST_NM AS DEFT_LAST_NM,  PSN.NYSID_NUM AS DEFT_NYSID, SEX.SEX_DESC AS DEFT_GENDER, PSN.BRTH_DT AS DEFT_BRTH_DT, LISTAGG(RTRIM(CAA.CMPLNT_ID), ',') WITHIN GROUP (ORDER BY ARR.ARR_ID) AS CMPLNT_ID, PKC.KY_CD, ARR.DV_FLG, PKC.LAW_CAT_CD from CID.ARR_ARREST ARR LEFT JOIN CID.PKC_PD_KEY_CODE PKC on PKC.PD_CD=ARR.PD_CD LEFT JOIN CID.CAA_CMPT_ARST_ASOC CAA on CAA.ARR_KEY=ARR.ARR_KEY LEFT JOIN CID.OFR_OFFICER_OCCUR OFR on ARR.ARR_KEY=OFR.EVNT_KEY AND OFR.ROLE_CD='6'  LEFT JOIN CID.PSB_PUB_SERV_GEO PSB ON PSB.PSB_GEO_KEY=ARR.PSB_GEO_KEY  LEFT JOIN CID.PSN_PERSON_OCCUR PSN ON PSN.EVNT_KEY=ARR.ARR_KEY AND PSN.ROLE_CD='A'  LEFT JOIN CID.SEX_CD SEX ON SEX.SEX_CD=PSN.SEX_CD  LEFT JOIN CID.CCH_CRIMINAL_CHARGE CCH ON CCH.EVNT_KEY=ARR.ARR_KEY AND CCH.ROLE_CD='A' AND SEQ_NUM=1  LEFT JOIN CID.LWC_LAW_CODE LWC ON LWC.LAW_CD=CCH.LAW_CD WHERE ARR.ARR_ID = ?1  GROUP BY ARR.ARR_ID, ARR.ARR_DT, ARR.ARR_PCT_CD, ARR.ARR_SEALED_FLG , LWC.LAW_LONG_DESC, PSB.PATRL_BORO_CD,  OFR.OFCR_TAX_NUM, OFR.LAST_NM, OFR.FRST_NM, OFR.OFCR_CMD_CD, PSN.FRST_NM, PSN.LAST_NM,  PSN.NYSID_NUM, SEX.SEX_DESC, PSN.BRTH_DT, PKC.KY_CD, ARR.DV_FLG, PKC.LAW_CAT_CD ",
                ArrestInfo.class);
        query.setParameter(1, arrId);
        return (ArrestInfo) query.getSingleResult();
    }

    public ArrestInfo getArrestInfoSummary(final String arrId) {
        Query query = entityManager.createNativeQuery(
                "select ARR.ARR_ID, LWC.LAW_LONG_DESC AS TOP_CHARGE from CID.ARR_ARREST ARR LEFT JOIN CID.CCH_CRIMINAL_CHARGE CCH ON CCH.EVNT_KEY=ARR.ARR_KEY AND CCH.ROLE_CD='A' AND SEQ_NUM=1 LEFT JOIN CID.LWC_LAW_CODE LWC ON LWC.LAW_CD=CCH.LAW_CD WHERE ARR.ARR_ID = ?1",
                ArrestInfo.class);
        query.setParameter(1, arrId);
        return (ArrestInfo) query.getSingleResult();
    }

    public List<ArrestInfo> getArrestInfoSummary(List<String> arrIds) {
        Query q = entityManager.createNativeQuery(
                "select RTRIM(ARR.ARR_ID), RTRIM(LWC.LAW_LONG_DESC) AS TOP_CHARGE , ARR.ARR_DT, PSN.FRST_NM AS DEFT_FRST_NM, PSN.LAST_NM AS DEFT_LAST_NM, PSN.NYSID_NUM AS DEFT_NYSID, ARR.ARR_SEALED_FLG from CID.ARR_ARREST ARR LEFT JOIN CID.CCH_CRIMINAL_CHARGE CCH ON CCH.EVNT_KEY=ARR.ARR_KEY AND CCH.ROLE_CD='A' AND SEQ_NUM=1 LEFT JOIN CID.PSN_PERSON_OCCUR PSN ON PSN.EVNT_KEY=ARR.ARR_KEY AND PSN.ROLE_CD='A' LEFT JOIN CID.LWC_LAW_CODE LWC ON LWC.LAW_CD=CCH.LAW_CD WHERE ARR.ARR_ID IN (?1)");
        q.setParameter(1, arrIds);
        List<ArrestInfo> returns = new ArrayList<>();
        List<Object[]> results = q.getResultList();
        for (Object[] result : results) {
            ArrestInfo info = new ArrestInfo((String) result[0], (String) result[1], (Date) result[2], (String) result[3], (String) result[4], (String) result[5], (Character) result[6], false);
            returns.add(info);
        }
        return returns;
    }

    public List<ArrestInfo> getMaskedArrestInfoSummary(List<String> arrIds) {
        Query q = entityManager.createNativeQuery(
                "select RTRIM(ARR.ARR_ID), RTRIM(LWC.LAW_LONG_DESC) AS TOP_CHARGE , ARR.ARR_DT, PSN.FRST_NM AS DEFT_FRST_NM, PSN.LAST_NM AS DEFT_LAST_NM, PSN.NYSID_NUM AS DEFT_NYSID, ARR.ARR_SEALED_FLG from CID.ARR_ARREST ARR LEFT JOIN CID.CCH_CRIMINAL_CHARGE CCH ON CCH.EVNT_KEY=ARR.ARR_KEY AND CCH.ROLE_CD='A' AND SEQ_NUM=1 LEFT JOIN CID.PSN_PERSON_OCCUR PSN ON PSN.EVNT_KEY=ARR.ARR_KEY AND PSN.ROLE_CD='A' LEFT JOIN CID.LWC_LAW_CODE LWC ON LWC.LAW_CD=CCH.LAW_CD WHERE ARR.ARR_ID IN (?1)");
        q.setParameter(1, arrIds);
        List<ArrestInfo> returns = new ArrayList<>();
        List<Object[]> results = q.getResultList();
        for (Object[] result : results) {
            ArrestInfo info = new ArrestInfo((String) result[0], (String) result[1], (Date) result[2], (String) result[3], (String) result[4], (String) result[5], (Character) result[6], true);
            returns.add(info);
        }
        return returns;
    }
}
