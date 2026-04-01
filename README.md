package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the ADA_LIST database table.
 * 
 */
@Entity
@Audited
@Table(name="ADA_LIST", schema = "DDD")
@NamedQuery(name="AdaList.findAll", query="SELECT a FROM AdaList a")
public class AdaList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="ARCHIVE_FLG")
	private Short archiveFlg;

	@Column(name="BOROUGH_NM")
	private String boroughNm;

	@Column(name="BUS_PHONE_NUM")
	private String busPhoneNum;

	@Column(name="CELL_PHONE_NUM")
	private String cellPhoneNum;

	@Column(name="EMAIL_ADDR_DESC")
	private String emailAddrDesc;

	@Column(name="FAX_NUM")
	private String faxNum;

	@Column(name="FRST_NM")
	private String frstNm;

	@Column(name="INACTIVE_FLG")
	private short inactiveFlg;

	@Column(name="JOB_TITLE_DESC")
	private String jobTitleDesc;

	@Column(name="LAST_NM")
	private String lastNm;

	@Column(name="NOTE_DESC")
	private String noteDesc;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	public AdaList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Short getArchiveFlg() {
		return this.archiveFlg;
	}

	public void setArchiveFlg(Short archiveFlg) {
		this.archiveFlg = archiveFlg;
	}

	public String getBoroughNm() {
		return this.boroughNm;
	}

	public void setBoroughNm(String boroughNm) {
		this.boroughNm = boroughNm;
	}

	public String getBusPhoneNum() {
		return this.busPhoneNum;
	}

	public void setBusPhoneNum(String busPhoneNum) {
		this.busPhoneNum = busPhoneNum;
	}

	public String getCellPhoneNum() {
		return this.cellPhoneNum;
	}

	public void setCellPhoneNum(String cellPhoneNum) {
		this.cellPhoneNum = cellPhoneNum;
	}

	public String getEmailAddrDesc() {
		return this.emailAddrDesc;
	}

	public void setEmailAddrDesc(String emailAddrDesc) {
		this.emailAddrDesc = emailAddrDesc;
	}

	public String getFaxNum() {
		return this.faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getFrstNm() {
		return this.frstNm;
	}

	public void setFrstNm(String frstNm) {
		this.frstNm = frstNm;
	}

	public short getInactiveFlg() {
		return this.inactiveFlg;
	}

	public void setInactiveFlg(short inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public String getJobTitleDesc() {
		return this.jobTitleDesc;
	}

	public void setJobTitleDesc(String jobTitleDesc) {
		this.jobTitleDesc = jobTitleDesc;
	}

	public String getLastNm() {
		return this.lastNm;
	}

	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}

	public String getNoteDesc() {
		return this.noteDesc;
	}

	public void setNoteDesc(String noteDesc) {
		this.noteDesc = noteDesc;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

}

==================




package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;


/**y
 * The persistent class for the DDD_CODE_REF database table.
 * 
 */
@Entity
@Table(name="DDD_CODE_REF", schema = "DDD")
@NamedQuery(name="DluCodeRef.findAll", query="SELECT d FROM DluCodeRef d")
public class DluCodeRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="CODE_DESC")
	private String codeDesc;

	@Column(name="CODE_TYPE")
	private String codeType;

	@Column(name="CODE_VAL")
	private String codeVal;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	public DluCodeRef() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeVal() {
		return this.codeVal;
	}

	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

}



=====================



package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;


/**
 * The persistent class for the DDD_CASE database table.
 * 
 */
@Entity
@Audited
@Table(name="DDD_CASE", schema = "DDD")
@NamedQuery(name="DluCase.findAll", query="SELECT d FROM DluCase d")
public class DluCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Version
	@Column(name = "VERSION")
    private Long version; 

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@Column(name="ADA_ID")
	//private Integer adaId;

	@Column(name="ARR_ID")
	private String arrId;

	@Column(name="ASSIGNED_NM")
	private String assignedNm;

	//@Column(name="CATEGORY_ID")
	//private int categoryId;

	@Temporal(TemporalType.DATE)
	@Column(name="COMPLETE_DT")
	private Date completeDt;

	//@Column(name="DDD_DESC")
	//private String dddDesc;

	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DT")
	private Date dueDt;

	@Column(name="DV_FLG")
	private short dvFlg;

	@Column(name="FELONY_FLG")
	private short felonyFlg;

	@Column(name="INDEX_FLG")
	private short indexFlg;

	@Column(name="PARENT_ID")
	private Integer parentId;

	@Column(name="PROACTIVE_FLG")
	private Short proactiveFlg;

	@Temporal(TemporalType.DATE)
	@Column(name="REQUEST_DT")
	private Date requestDt;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	//@LastModifiedDate
	//@UpdateTimestamp
	//@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	//@Column(name="STATUS_DESC")
	//private String statusDesc;

	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "category_id", nullable = true) 
    private CategoryList category;

	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "ada_id", nullable = true) 
    private AdaList ada;

	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "case_status_id", nullable = true) 
    private StatusList status;

	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "ddd_office_id", nullable = true) 
    private DluOfficeList ddd;

	@OneToMany(mappedBy = "dddCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CaseItem> items = new HashSet<>();

	@OneToMany(mappedBy = "dddCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OrderBy("id DESC")
    private Set<CaseNote> notes = new HashSet<>();

	@OneToMany(mappedBy = "dddCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CaseTag> tags = new HashSet<>();

	@OneToMany(mappedBy = "dddCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CaseUpload> uploads = new HashSet<>();

	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
    	this.version = version;
	}

	public Set<CaseUpload> getUploads() {
		return uploads;
	}

	public void setUploads(Set<CaseUpload> uploads) {
		this.uploads = uploads;
	}

	public Set<CaseItem> getItems() {
		return items;
	}

	public void setItems(Set<CaseItem> items) {
		this.items = items;
	}

	public Set<CaseNote> getNotes() {
		return notes;
	}

	public void setNotes(Set<CaseNote> notes) {
		this.notes = notes;
	}

	public Set<CaseTag> getTags() {
		return tags;
	}

	public void setTags(Set<CaseTag> tags) {
		this.tags = tags;
	}

	public CategoryList getCategory() {
		return category;
	}

	public void setCategory(CategoryList category) {
		this.category = category;
	}

	public AdaList getAda() {
		return ada;
	}

	public void setAda(AdaList ada) {
		this.ada = ada;
	}

	public DluCase() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	public Integer getAdaId() {
		return this.adaId;
	}

	public void setAdaId(Integer adaId) {
		this.adaId = adaId;
	} 
	*/

	public String getArrId() {
		return this.arrId;
	}

	public void setArrId(String arrId) {
		this.arrId = arrId;
	}

	public String getAssignedNm() {
		return this.assignedNm;
	}

	public void setAssignedNm(String assignedNm) {
		this.assignedNm = assignedNm;
	}

	/*
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
 	*/

	public Date getCompleteDt() {
		return this.completeDt;
	}

	public void setCompleteDt(Date completeDt) {
		this.completeDt = completeDt;
	}

	public DluOfficeList getDlu() {
		return this.ddd;
	}

	public void setDlu(DluOfficeList dddDesc) {
		this.ddd = dddDesc;
	}

	public Date getDueDt() {
		return this.dueDt;
	}

	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}

	public short getDvFlg() {
		return this.dvFlg;
	}

	public void setDvFlg(short dvFlg) {
		this.dvFlg = dvFlg;
	}

	public short getFelonyFlg() {
		return this.felonyFlg;
	}

	public void setFelonyFlg(short felonyFlg) {
		this.felonyFlg = felonyFlg;
	}

	public short getIndexFlg() {
		return this.indexFlg;
	}

	public void setIndexFlg(short indexFlg) {
		this.indexFlg = indexFlg;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Short getProactiveFlg() {
		return this.proactiveFlg;
	}

	public void setProactiveFlg(Short proactiveFlg) {
		this.proactiveFlg = proactiveFlg;
	}

	public Date getRequestDt() {
		return this.requestDt;
	}

	public void setRequestDt(Date requestDt) {
		this.requestDt = requestDt;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public StatusList getStatus() {
		return this.status;
	}

	public void setStatus(StatusList statusDesc) {
		this.status = statusDesc;
	}

}

===============
package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the DDD_OFFICE_LIST database table.
 * 
 */
@Entity
@Audited
@Table(name="DDD_OFFICE_LIST", schema = "DDD")
@NamedQuery(name="DluOfficeList.findAll", query="SELECT d FROM DluOfficeList d")
public class DluOfficeList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="DDD_OFFICE_DESC")
	private String dddOfficeDesc;

	@Column(name="AD_SG_NM")
	private String adSgNm;

	@Column(name="INACTIVE_FLG")
	private short inactiveFlg;

	@Column(name="ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name="ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	public DluOfficeList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDluOfficeDesc() {
		return this.dddOfficeDesc;
	}

	public void setDluOfficeDesc(String dddOfficeDesc) {
		this.dddOfficeDesc = dddOfficeDesc;
	}

	public String getAdSgNm() {
		return adSgNm;
	}

	public void setAdSgNm(String adSgNm) {
		this.adSgNm = adSgNm;
	}

	public short getInactiveFlg() {
		return this.inactiveFlg;
	}

	public void setInactiveFlg(short inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

}

==================


package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the CASE_NOTE database table.
 * 
 */
@Entity
@Audited
@Table(name="CASE_NOTE", schema = "DDD")
@NamedQuery(name="CaseNote.findAll", query="SELECT c FROM CaseNote c")
public class CaseNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="NOTE_DESC")
	private String noteDesc;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	@Column(name="USER_NM")
	private String userNm;

	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "CASE_ID", nullable = false) 
    private DluCase dddCase;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
    @JoinColumn(name = "UPLOAD_ID", nullable = true) 
    private CaseUpload upload;

	public CaseUpload getUpload() {
		return upload;
	}

	public void setUpload(CaseUpload upload) {
		this.upload = upload;
	}

	public DluCase getDluCase() {
		return dddCase;
	}

	public void setDluCase(DluCase dddCase) {
		this.dddCase = dddCase;
	}

	public CaseNote() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNoteDesc() {
		return this.noteDesc;
	}

	public void setNoteDesc(String noteDesc) {
		this.noteDesc = noteDesc;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

}

=============

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the CATEGORY_LIST database table.
 * 
 */
@Entity
@Audited
@Table(name="CATEGORY_LIST", schema = "DDD")
@NamedQuery(name="CategoryList.findAll", query="SELECT c FROM CategoryList c")
public class CategoryList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="CATEGORY_DESC")
	private String categoryDesc;

	@Column(name="INACTIVE_FLG")
	private short inactiveFlg;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	public CategoryList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public short getInactiveFlg() {
		return this.inactiveFlg;
	}

	public void setInactiveFlg(short inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

}

===============

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the STATUS_LIST database table.
 * 
 */
@Entity
@Audited
@Table(name="STATUS_LIST", schema = "DDD")
@NamedQuery(name="StatusList.findAll", query="SELECT s FROM StatusList s")
public class StatusList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="INACTIVE_FLG")
	private short inactiveFlg;

	@Column(name="ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name="ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	@Column(name="STATUS_DESC")
	private String statusDesc;

	public StatusList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getInactiveFlg() {
		return this.inactiveFlg;
	}

	public void setInactiveFlg(short inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}

================

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the DDD_AUDIT database table.
 * 
 */
@Entity
@Table(name="DDD_AUDIT", schema = "DDD")
@NamedQuery(name="DluAudit.findAll", query="SELECT d FROM DluAudit d")
public class DluAudit implements Serializable {
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

	public DluAudit() {
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

===============

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the ITEM_LIST database table.
 * 
 */
@Entity
@Audited
@Table(name="ITEM_LIST", schema = "DDD")
@NamedQuery(name="ItemList.findAll", query="SELECT i FROM ItemList i")
public class ItemList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="INACTIVE_FLG")
	private short inactiveFlg;

	@Column(name="ITEM_DESC")
	private String itemDesc;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	public ItemList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getInactiveFlg() {
		return this.inactiveFlg;
	}

	public void setInactiveFlg(short inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public String getItemDesc() {
		return this.itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

}


-===========================

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the TAG_LIST database table.
 * 
 */
@Entity
@Audited
@Table(name="TAG_LIST", schema = "DDD")
@NamedQuery(name="TagList.findAll", query="SELECT t FROM TagList t")
public class TagList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="INACTIVE_FLG")
	private short inactiveFlg;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	@Column(name="TAG_DESC")
	private String tagDesc;

	public TagList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getInactiveFlg() {
		return this.inactiveFlg;
	}

	public void setInactiveFlg(short inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public String getTagDesc() {
		return this.tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

}

============

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the CASE_UPLOAD database table.
 * 
 */
@Entity
@Audited
@Table(name="CASE_UPLOAD", schema = "DDD")
@NamedQuery(name="CaseUpload.findAll", query="SELECT c FROM CaseUpload c")
public class CaseUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="FILE_NM")
	private String fileNm;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	@Column(name="USER_NM")
	private String userNm;

	@Column(name="DELETED_FLG")
	private short deletedFlg;

	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "CASE_ID", nullable = false) 
    private DluCase dddCase;
	
	public short getDeletedFlg() {
		return deletedFlg;
	}

	public void setDeletedFlg(short deletedFlg) {
		this.deletedFlg = deletedFlg;
	}

	public DluCase getDluCase() {
		return dddCase;
	}

	public void setDluCase(DluCase dddCase) {
		this.dddCase = dddCase;
	}

	public CaseUpload() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileNm() {
		return this.fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

}

==============================

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the CASE_ITEM database table.
 * 
 */
@Entity
@Audited
@Table(name="CASE_ITEM", schema = "DDD")
@NamedQuery(name="CaseItem.findAll", query="SELECT c FROM CaseItem c")
public class CaseItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer quantity;

	@Column(name="NOTE_DESC")
	private String noteDesc;
	
	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "ITEM_ID", nullable = true) 
    private ItemList item;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	@Column(name="STATUS_DESC")
	private String statusDesc;

	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "CASE_ID", nullable = false) 
    private DluCase dddCase;
	
	public ItemList getItem() {
		return item;
	}

	public void setItem(ItemList item) {
		this.item = item;
	}

	public void setDluCase(DluCase dddCase) {
		this.dddCase = dddCase;
	}

	public DluCase getDluCase() {
		return dddCase;
	}

	public CaseItem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getNoteDesc() {
		return this.noteDesc;
	}

	public void setNoteDesc(String noteDesc) {
		this.noteDesc = noteDesc;
	}
}

=====================

package org.nnnn.ddd.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;


/**
 * The persistent class for the CASE_TAG database table.
 * 
 */
@Entity
@Audited
@Table(name="CASE_TAG", schema = "DDD")
@NamedQuery(name="CaseTag.findAll", query="SELECT c FROM CaseTag c")
public class CaseTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ROW_INSERT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp rowInsertTs;

	@LastModifiedDate
	@UpdateTimestamp
	@Column(name = "ROW_UPDT_TS", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
	private Timestamp rowUpdtTs;

	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "TAG_ID", nullable = true) 
    private TagList tag;

	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "CASE_ID", nullable = false) 
    private DluCase dddCase;

	public DluCase getDluCase() {
		return dddCase;
	}

	public void setDluCase(DluCase dddCase) {
		this.dddCase = dddCase;
	}

	public CaseTag() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getRowInsertTs() {
		return this.rowInsertTs;
	}

	public void setRowInsertTs(Timestamp rowInsertTs) {
		this.rowInsertTs = rowInsertTs;
	}

	public Timestamp getRowUpdtTs() {
		return this.rowUpdtTs;
	}

	public void setRowUpdtTs(Timestamp rowUpdtTs) {
		this.rowUpdtTs = rowUpdtTs;
	}

	public TagList getTag() {
		return tag;
	}

	public void setTag(TagList tag) {
		this.tag = tag;
	}

}




