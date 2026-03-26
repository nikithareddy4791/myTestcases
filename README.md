
package org.nnnn.ddd.service;

import org.nnnn.ddd.entity.dddAudit;
import org.nnnn.ddd.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private static final Logger log = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public void auditAction(final String actionType, final String actionDetail) {
        dddAudit audit = new dddAudit();
        audit.setUserNm(authenticationService.getUsername());
        audit.setActionType(actionType);
        audit.setActionDetail(actionDetail);
        auditRepository.save(audit);
    }

    public void auditAction(final String actionType, final String actionDetail, final Integer caseId) {
        dddAudit audit = new dddAudit();
        audit.setUserNm(authenticationService.getUsername());
        audit.setActionType(actionType);
        audit.setActionDetail(actionDetail);
        audit.setCaseId(caseId);
        auditRepository.save(audit);
    }
}






===============================




package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ddd_AUDIT database table.
 * 
 */
@Entity
@Table(name="ddd_AUDIT", schema = "ddd")
@NamedQuery(name="dddAudit.findAll", query="SELECT d FROM dddAudit d")
public class dddAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="ACTION_DETAIL")
	private String actionDetail;

	@Column(name="ACTION_TYPE")
	private String actionType;

	@Column(name="ARR_ID")
	private String arrId;

	@Column(name="CASE_ID")
	private Integer caseId;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@Column(name="USER_NM")
	private String userNm;

	public dddAudit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionDetail() {
		return this.actionDetail;
	}

	public void setActionDetail(String actionDetail) {
		this.actionDetail = actionDetail;
	}

	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getArrId() {
		return this.arrId;
	}

	public void setArrId(String arrId) {
		this.arrId = arrId;
	}

	public Integer getCaseId() {
		return this.caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

}
