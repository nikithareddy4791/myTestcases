package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AdaList Entity Tests")
class AdaListTest {

    private AdaList ada;

    @BeforeEach
    void setUp() {
        ada = new AdaList();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(ada).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        ada.setId(42);
        assertThat(ada.getId()).isEqualTo(42);
    }

    @Test
    @DisplayName("archiveFlg getter and setter")
    void archiveFlg_getterSetter() {
        ada.setArchiveFlg((short) 1);
        assertThat(ada.getArchiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("archiveFlg is null by default")
    void archiveFlg_nullByDefault() {
        assertThat(ada.getArchiveFlg()).isNull();
    }

    @Test
    @DisplayName("boroughNm getter and setter")
    void boroughNm_getterSetter() {
        ada.setBoroughNm("Manhattan");
        assertThat(ada.getBoroughNm()).isEqualTo("Manhattan");
    }

    @Test
    @DisplayName("busPhoneNum getter and setter")
    void busPhoneNum_getterSetter() {
        ada.setBusPhoneNum("212-555-1234");
        assertThat(ada.getBusPhoneNum()).isEqualTo("212-555-1234");
    }

    @Test
    @DisplayName("cellPhoneNum getter and setter")
    void cellPhoneNum_getterSetter() {
        ada.setCellPhoneNum("917-555-9876");
        assertThat(ada.getCellPhoneNum()).isEqualTo("917-555-9876");
    }

    @Test
    @DisplayName("emailAddrDesc getter and setter")
    void emailAddrDesc_getterSetter() {
        ada.setEmailAddrDesc("ada@nnnn.finest");
        assertThat(ada.getEmailAddrDesc()).isEqualTo("ada@nnnn.finest");
    }

    @Test
    @DisplayName("faxNum getter and setter")
    void faxNum_getterSetter() {
        ada.setFaxNum("212-555-0000");
        assertThat(ada.getFaxNum()).isEqualTo("212-555-0000");
    }

    @Test
    @DisplayName("frstNm getter and setter")
    void frstNm_getterSetter() {
        ada.setFrstNm("Jane");
        assertThat(ada.getFrstNm()).isEqualTo("Jane");
    }

    @Test
    @DisplayName("inactiveFlg getter and setter")
    void inactiveFlg_getterSetter() {
        ada.setInactiveFlg((short) 1);
        assertThat(ada.getInactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("jobTitleDesc getter and setter")
    void jobTitleDesc_getterSetter() {
        ada.setJobTitleDesc("Assistant District Attorney");
        assertThat(ada.getJobTitleDesc()).isEqualTo("Assistant District Attorney");
    }

    @Test
    @DisplayName("lastNm getter and setter")
    void lastNm_getterSetter() {
        ada.setLastNm("Doe");
        assertThat(ada.getLastNm()).isEqualTo("Doe");
    }

    @Test
    @DisplayName("noteDesc getter and setter")
    void noteDesc_getterSetter() {
        ada.setNoteDesc("Some note");
        assertThat(ada.getNoteDesc()).isEqualTo("Some note");
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ada.setRowInsertTs(ts);
        assertThat(ada.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ada.setRowUpdtTs(ts);
        assertThat(ada.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(ada.getBoroughNm()).isNull();
        assertThat(ada.getBusPhoneNum()).isNull();
        assertThat(ada.getCellPhoneNum()).isNull();
        assertThat(ada.getEmailAddrDesc()).isNull();
        assertThat(ada.getFaxNum()).isNull();
        assertThat(ada.getFrstNm()).isNull();
        assertThat(ada.getJobTitleDesc()).isNull();
        assertThat(ada.getLastNm()).isNull();
        assertThat(ada.getNoteDesc()).isNull();
    }
}



===============

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DluCodeRef Entity Tests")
class DluCodeRefTest {

    private DluCodeRef codeRef;

    @BeforeEach
    void setUp() {
        codeRef = new DluCodeRef();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(codeRef).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        codeRef.setId(10);
        assertThat(codeRef.getId()).isEqualTo(10);
    }

    @Test
    @DisplayName("codeDesc getter and setter")
    void codeDesc_getterSetter() {
        codeRef.setCodeDesc("Description");
        assertThat(codeRef.getCodeDesc()).isEqualTo("Description");
    }

    @Test
    @DisplayName("codeType getter and setter")
    void codeType_getterSetter() {
        codeRef.setCodeType("TYPE_A");
        assertThat(codeRef.getCodeType()).isEqualTo("TYPE_A");
    }

    @Test
    @DisplayName("codeVal getter and setter")
    void codeVal_getterSetter() {
        codeRef.setCodeVal("VAL_01");
        assertThat(codeRef.getCodeVal()).isEqualTo("VAL_01");
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        codeRef.setRowInsertTs(ts);
        assertThat(codeRef.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        codeRef.setRowUpdtTs(ts);
        assertThat(codeRef.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(codeRef.getCodeDesc()).isNull();
        assertThat(codeRef.getCodeType()).isNull();
        assertThat(codeRef.getCodeVal()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(codeRef.getRowInsertTs()).isNull();
        assertThat(codeRef.getRowUpdtTs()).isNull();
    }
}


===================


package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DluCase Entity Tests")
class DluCaseTest {

    private DluCase dluCase;

    @BeforeEach
    void setUp() {
        dluCase = new DluCase();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(dluCase).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        dluCase.setId(100);
        assertThat(dluCase.getId()).isEqualTo(100);
    }

    @Test
    @DisplayName("version getter and setter")
    void version_getterSetter() {
        dluCase.setVersion(3L);
        assertThat(dluCase.getVersion()).isEqualTo(3L);
    }

    @Test
    @DisplayName("version is null by default")
    void version_nullByDefault() {
        assertThat(dluCase.getVersion()).isNull();
    }

    @Test
    @DisplayName("arrId getter and setter")
    void arrId_getterSetter() {
        dluCase.setArrId("ARR-999");
        assertThat(dluCase.getArrId()).isEqualTo("ARR-999");
    }

    @Test
    @DisplayName("assignedNm getter and setter")
    void assignedNm_getterSetter() {
        dluCase.setAssignedNm("jdoe");
        assertThat(dluCase.getAssignedNm()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("completeDt getter and setter")
    void completeDt_getterSetter() {
        Date date = new Date();
        dluCase.setCompleteDt(date);
        assertThat(dluCase.getCompleteDt()).isEqualTo(date);
    }

    @Test
    @DisplayName("dueDt getter and setter")
    void dueDt_getterSetter() {
        Date date = new Date();
        dluCase.setDueDt(date);
        assertThat(dluCase.getDueDt()).isEqualTo(date);
    }

    @Test
    @DisplayName("dvFlg getter and setter")
    void dvFlg_getterSetter() {
        dluCase.setDvFlg((short) 1);
        assertThat(dluCase.getDvFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("felonyFlg getter and setter")
    void felonyFlg_getterSetter() {
        dluCase.setFelonyFlg((short) 1);
        assertThat(dluCase.getFelonyFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("indexFlg getter and setter")
    void indexFlg_getterSetter() {
        dluCase.setIndexFlg((short) 1);
        assertThat(dluCase.getIndexFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("parentId getter and setter")
    void parentId_getterSetter() {
        dluCase.setParentId(50);
        assertThat(dluCase.getParentId()).isEqualTo(50);
    }

    @Test
    @DisplayName("parentId is null by default")
    void parentId_nullByDefault() {
        assertThat(dluCase.getParentId()).isNull();
    }

    @Test
    @DisplayName("proactiveFlg getter and setter")
    void proactiveFlg_getterSetter() {
        dluCase.setProactiveFlg((short) 1);
        assertThat(dluCase.getProactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("proactiveFlg is null by default")
    void proactiveFlg_nullByDefault() {
        assertThat(dluCase.getProactiveFlg()).isNull();
    }

    @Test
    @DisplayName("requestDt getter and setter")
    void requestDt_getterSetter() {
        Date date = new Date();
        dluCase.setRequestDt(date);
        assertThat(dluCase.getRequestDt()).isEqualTo(date);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        dluCase.setRowInsertTs(ts);
        assertThat(dluCase.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        dluCase.setRowUpdtTs(ts);
        assertThat(dluCase.getRowUpdtTs()).isEqualTo(ts);
    }

    // -------------------------------------------------------------------------
    // Relationships
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("category relationship getter and setter")
    void category_getterSetter() {
        CategoryList category = new CategoryList();
        category.setId(1);
        category.setCategoryDesc("Criminal");
        dluCase.setCategory(category);
        assertThat(dluCase.getCategory()).isEqualTo(category);
        assertThat(dluCase.getCategory().getCategoryDesc()).isEqualTo("Criminal");
    }

    @Test
    @DisplayName("ada relationship getter and setter")
    void ada_getterSetter() {
        AdaList ada = new AdaList();
        ada.setId(5);
        ada.setFrstNm("Jane");
        dluCase.setAda(ada);
        assertThat(dluCase.getAda()).isEqualTo(ada);
        assertThat(dluCase.getAda().getFrstNm()).isEqualTo("Jane");
    }

    @Test
    @DisplayName("status relationship getter and setter")
    void status_getterSetter() {
        StatusList status = new StatusList();
        status.setId(2);
        status.setStatusDesc("Open");
        dluCase.setStatus(status);
        assertThat(dluCase.getStatus()).isEqualTo(status);
        assertThat(dluCase.getStatus().getStatusDesc()).isEqualTo("Open");
    }

    @Test
    @DisplayName("dlu (office) relationship getter and setter")
    void dlu_getterSetter() {
        DluOfficeList office = new DluOfficeList();
        office.setId(3);
        office.setDluOfficeDesc("Brooklyn Office");
        dluCase.setDlu(office);
        assertThat(dluCase.getDlu()).isEqualTo(office);
        assertThat(dluCase.getDlu().getDluOfficeDesc()).isEqualTo("Brooklyn Office");
    }

    @Test
    @DisplayName("tags collection getter and setter")
    void tags_getterSetter() {
        CaseTag tag = new CaseTag();
        tag.setId(10);
        Set<CaseTag> tags = new HashSet<>();
        tags.add(tag);
        dluCase.setTags(tags);
        assertThat(dluCase.getTags()).hasSize(1);
        assertThat(dluCase.getTags()).contains(tag);
    }

    @Test
    @DisplayName("tags collection is empty by default")
    void tags_emptyByDefault() {
        assertThat(dluCase.getTags()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("notes collection getter and setter")
    void notes_getterSetter() {
        CaseNote note = new CaseNote();
        note.setId(20);
        Set<CaseNote> notes = new HashSet<>();
        notes.add(note);
        dluCase.setNotes(notes);
        assertThat(dluCase.getNotes()).hasSize(1).contains(note);
    }

    @Test
    @DisplayName("notes collection is empty by default")
    void notes_emptyByDefault() {
        assertThat(dluCase.getNotes()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("items collection getter and setter")
    void items_getterSetter() {
        CaseItem item = new CaseItem();
        item.setId(30);
        Set<CaseItem> items = new HashSet<>();
        items.add(item);
        dluCase.setItems(items);
        assertThat(dluCase.getItems()).hasSize(1).contains(item);
    }

    @Test
    @DisplayName("items collection is empty by default")
    void items_emptyByDefault() {
        assertThat(dluCase.getItems()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("uploads collection getter and setter")
    void uploads_getterSetter() {
        CaseUpload upload = new CaseUpload();
        upload.setId(40);
        Set<CaseUpload> uploads = new HashSet<>();
        uploads.add(upload);
        dluCase.setUploads(uploads);
        assertThat(dluCase.getUploads()).hasSize(1).contains(upload);
    }

    @Test
    @DisplayName("uploads collection is empty by default")
    void uploads_emptyByDefault() {
        assertThat(dluCase.getUploads()).isNotNull().isEmpty();
    }
}


==================

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DluOfficeList Entity Tests")
class DluOfficeListTest {

    private DluOfficeList office;

    @BeforeEach
    void setUp() {
        office = new DluOfficeList();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(office).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        office.setId(7);
        assertThat(office.getId()).isEqualTo(7);
    }

    @Test
    @DisplayName("dluOfficeDesc getter and setter")
    void dluOfficeDesc_getterSetter() {
        office.setDluOfficeDesc("Brooklyn Office");
        assertThat(office.getDluOfficeDesc()).isEqualTo("Brooklyn Office");
    }

    @Test
    @DisplayName("adSgNm getter and setter")
    void adSgNm_getterSetter() {
        office.setAdSgNm("SG-DDD-ANALYST-BK");
        assertThat(office.getAdSgNm()).isEqualTo("SG-DDD-ANALYST-BK");
    }

    @Test
    @DisplayName("inactiveFlg getter and setter")
    void inactiveFlg_getterSetter() {
        office.setInactiveFlg((short) 1);
        assertThat(office.getInactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        office.setRowInsertTs(ts);
        assertThat(office.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        office.setRowUpdtTs(ts);
        assertThat(office.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(office.getDluOfficeDesc()).isNull();
        assertThat(office.getAdSgNm()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(office.getRowInsertTs()).isNull();
        assertThat(office.getRowUpdtTs()).isNull();
    }
}

==============

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseNote Entity Tests")
class CaseNoteTest {

    private CaseNote caseNote;

    @BeforeEach
    void setUp() {
        caseNote = new CaseNote();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(caseNote).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        caseNote.setId(55);
        assertThat(caseNote.getId()).isEqualTo(55);
    }

    @Test
    @DisplayName("noteDesc getter and setter")
    void noteDesc_getterSetter() {
        caseNote.setNoteDesc("This is a case note.");
        assertThat(caseNote.getNoteDesc()).isEqualTo("This is a case note.");
    }

    @Test
    @DisplayName("userNm getter and setter")
    void userNm_getterSetter() {
        caseNote.setUserNm("jdoe");
        assertThat(caseNote.getUserNm()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        caseNote.setRowInsertTs(ts);
        assertThat(caseNote.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        caseNote.setRowUpdtTs(ts);
        assertThat(caseNote.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("dluCase relationship getter and setter")
    void dluCase_getterSetter() {
        DluCase dluCase = new DluCase();
        dluCase.setId(10);
        caseNote.setDluCase(dluCase);
        assertThat(caseNote.getDluCase()).isEqualTo(dluCase);
        assertThat(caseNote.getDluCase().getId()).isEqualTo(10);
    }

    @Test
    @DisplayName("upload relationship getter and setter")
    void upload_getterSetter() {
        CaseUpload upload = new CaseUpload();
        upload.setId(99);
        upload.setFileNm("evidence.pdf");
        caseNote.setUpload(upload);
        assertThat(caseNote.getUpload()).isEqualTo(upload);
        assertThat(caseNote.getUpload().getFileNm()).isEqualTo("evidence.pdf");
    }

    @Test
    @DisplayName("upload is null by default")
    void upload_nullByDefault() {
        assertThat(caseNote.getUpload()).isNull();
    }

    @Test
    @DisplayName("dluCase is null by default")
    void dluCase_nullByDefault() {
        assertThat(caseNote.getDluCase()).isNull();
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(caseNote.getNoteDesc()).isNull();
        assertThat(caseNote.getUserNm()).isNull();
    }
}

=======================


package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CategoryList Entity Tests")
class CategoryListTest {

    private CategoryList category;

    @BeforeEach
    void setUp() {
        category = new CategoryList();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(category).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        category.setId(3);
        assertThat(category.getId()).isEqualTo(3);
    }

    @Test
    @DisplayName("categoryDesc getter and setter")
    void categoryDesc_getterSetter() {
        category.setCategoryDesc("Criminal");
        assertThat(category.getCategoryDesc()).isEqualTo("Criminal");
    }

    @Test
    @DisplayName("inactiveFlg getter and setter")
    void inactiveFlg_getterSetter() {
        category.setInactiveFlg((short) 1);
        assertThat(category.getInactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("inactiveFlg is 0 by default")
    void inactiveFlg_zeroByDefault() {
        assertThat(category.getInactiveFlg()).isEqualTo((short) 0);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        category.setRowInsertTs(ts);
        assertThat(category.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        category.setRowUpdtTs(ts);
        assertThat(category.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("categoryDesc is null by default")
    void categoryDesc_nullByDefault() {
        assertThat(category.getCategoryDesc()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(category.getRowInsertTs()).isNull();
        assertThat(category.getRowUpdtTs()).isNull();
    }
}

=========================


package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StatusList Entity Tests")
class StatusListTest {

    private StatusList status;

    @BeforeEach
    void setUp() {
        status = new StatusList();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(status).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        status.setId(4);
        assertThat(status.getId()).isEqualTo(4);
    }

    @Test
    @DisplayName("statusDesc getter and setter")
    void statusDesc_getterSetter() {
        status.setStatusDesc("Open");
        assertThat(status.getStatusDesc()).isEqualTo("Open");
    }

    @Test
    @DisplayName("inactiveFlg getter and setter")
    void inactiveFlg_getterSetter() {
        status.setInactiveFlg((short) 1);
        assertThat(status.getInactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("inactiveFlg is 0 by default")
    void inactiveFlg_zeroByDefault() {
        assertThat(status.getInactiveFlg()).isEqualTo((short) 0);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        status.setRowInsertTs(ts);
        assertThat(status.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        status.setRowUpdtTs(ts);
        assertThat(status.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("statusDesc is null by default")
    void statusDesc_nullByDefault() {
        assertThat(status.getStatusDesc()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(status.getRowInsertTs()).isNull();
        assertThat(status.getRowUpdtTs()).isNull();
    }
}


===========================

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DluAudit Entity Tests")
class DluAuditTest {

    private DluAudit audit;

    @BeforeEach
    void setUp() {
        audit = new DluAudit();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(audit).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        audit.setId(1);
        assertThat(audit.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("actionDetail getter and setter")
    void actionDetail_getterSetter() {
        audit.setActionDetail("Case created");
        assertThat(audit.getActionDetail()).isEqualTo("Case created");
    }

    @Test
    @DisplayName("actionType getter and setter")
    void actionType_getterSetter() {
        audit.setActionType("CREATE");
        assertThat(audit.getActionType()).isEqualTo("CREATE");
    }

    @Test
    @DisplayName("arrId getter and setter")
    void arrId_getterSetter() {
        audit.setArrId("ARR-001");
        assertThat(audit.getArrId()).isEqualTo("ARR-001");
    }

    @Test
    @DisplayName("caseId getter and setter")
    void caseId_getterSetter() {
        audit.setCaseId(200);
        assertThat(audit.getCaseId()).isEqualTo(200);
    }

    @Test
    @DisplayName("caseId is null by default")
    void caseId_nullByDefault() {
        assertThat(audit.getCaseId()).isNull();
    }

    @Test
    @DisplayName("userNm getter and setter")
    void userNm_getterSetter() {
        audit.setUserNm("jdoe");
        assertThat(audit.getUserNm()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        audit.setRowInsertTs(ts);
        assertThat(audit.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(audit.getActionDetail()).isNull();
        assertThat(audit.getActionType()).isNull();
        assertThat(audit.getArrId()).isNull();
        assertThat(audit.getUserNm()).isNull();
    }

    @Test
    @DisplayName("rowInsertTs is null by default")
    void rowInsertTs_nullByDefault() {
        assertThat(audit.getRowInsertTs()).isNull();
    }
}


=========================

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ItemList Entity Tests")
class ItemListTest {

    private ItemList item;

    @BeforeEach
    void setUp() {
        item = new ItemList();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(item).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        item.setId(9);
        assertThat(item.getId()).isEqualTo(9);
    }

    @Test
    @DisplayName("itemDesc getter and setter")
    void itemDesc_getterSetter() {
        item.setItemDesc("Evidence bag");
        assertThat(item.getItemDesc()).isEqualTo("Evidence bag");
    }

    @Test
    @DisplayName("inactiveFlg getter and setter")
    void inactiveFlg_getterSetter() {
        item.setInactiveFlg((short) 1);
        assertThat(item.getInactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("inactiveFlg is 0 by default")
    void inactiveFlg_zeroByDefault() {
        assertThat(item.getInactiveFlg()).isEqualTo((short) 0);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        item.setRowInsertTs(ts);
        assertThat(item.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        item.setRowUpdtTs(ts);
        assertThat(item.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("itemDesc is null by default")
    void itemDesc_nullByDefault() {
        assertThat(item.getItemDesc()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(item.getRowInsertTs()).isNull();
        assertThat(item.getRowUpdtTs()).isNull();
    }
}


===========================


package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TagList Entity Tests")
class TagListTest {

    private TagList tag;

    @BeforeEach
    void setUp() {
        tag = new TagList();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(tag).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        tag.setId(11);
        assertThat(tag.getId()).isEqualTo(11);
    }

    @Test
    @DisplayName("tagDesc getter and setter")
    void tagDesc_getterSetter() {
        tag.setTagDesc("Priority");
        assertThat(tag.getTagDesc()).isEqualTo("Priority");
    }

    @Test
    @DisplayName("inactiveFlg getter and setter")
    void inactiveFlg_getterSetter() {
        tag.setInactiveFlg((short) 1);
        assertThat(tag.getInactiveFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("inactiveFlg is 0 by default")
    void inactiveFlg_zeroByDefault() {
        assertThat(tag.getInactiveFlg()).isEqualTo((short) 0);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        tag.setRowInsertTs(ts);
        assertThat(tag.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        tag.setRowUpdtTs(ts);
        assertThat(tag.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("tagDesc is null by default")
    void tagDesc_nullByDefault() {
        assertThat(tag.getTagDesc()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(tag.getRowInsertTs()).isNull();
        assertThat(tag.getRowUpdtTs()).isNull();
    }
}


-===========================================

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseUpload Entity Tests")
class CaseUploadTest {

    private CaseUpload upload;

    @BeforeEach
    void setUp() {
        upload = new CaseUpload();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(upload).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        upload.setId(77);
        assertThat(upload.getId()).isEqualTo(77);
    }

    @Test
    @DisplayName("fileNm getter and setter")
    void fileNm_getterSetter() {
        upload.setFileNm("report.pdf");
        assertThat(upload.getFileNm()).isEqualTo("report.pdf");
    }

    @Test
    @DisplayName("userNm getter and setter")
    void userNm_getterSetter() {
        upload.setUserNm("jdoe");
        assertThat(upload.getUserNm()).isEqualTo("jdoe");
    }

    @Test
    @DisplayName("deletedFlg getter and setter")
    void deletedFlg_getterSetter() {
        upload.setDeletedFlg((short) 1);
        assertThat(upload.getDeletedFlg()).isEqualTo((short) 1);
    }

    @Test
    @DisplayName("deletedFlg is 0 by default")
    void deletedFlg_zeroByDefault() {
        assertThat(upload.getDeletedFlg()).isEqualTo((short) 0);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        upload.setRowInsertTs(ts);
        assertThat(upload.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        upload.setRowUpdtTs(ts);
        assertThat(upload.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("dluCase relationship getter and setter")
    void dluCase_getterSetter() {
        DluCase dluCase = new DluCase();
        dluCase.setId(50);
        upload.setDluCase(dluCase);
        assertThat(upload.getDluCase()).isEqualTo(dluCase);
        assertThat(upload.getDluCase().getId()).isEqualTo(50);
    }

    @Test
    @DisplayName("dluCase is null by default")
    void dluCase_nullByDefault() {
        assertThat(upload.getDluCase()).isNull();
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(upload.getFileNm()).isNull();
        assertThat(upload.getUserNm()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(upload.getRowInsertTs()).isNull();
        assertThat(upload.getRowUpdtTs()).isNull();
    }
}


===============================

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseItem Entity Tests")
class CaseItemTest {

    private CaseItem caseItem;

    @BeforeEach
    void setUp() {
        caseItem = new CaseItem();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(caseItem).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        caseItem.setId(33);
        assertThat(caseItem.getId()).isEqualTo(33);
    }

    @Test
    @DisplayName("quantity getter and setter")
    void quantity_getterSetter() {
        caseItem.setQuantity(5);
        assertThat(caseItem.getQuantity()).isEqualTo(5);
    }

    @Test
    @DisplayName("quantity is null by default")
    void quantity_nullByDefault() {
        assertThat(caseItem.getQuantity()).isNull();
    }

    @Test
    @DisplayName("noteDesc getter and setter")
    void noteDesc_getterSetter() {
        caseItem.setNoteDesc("Item note");
        assertThat(caseItem.getNoteDesc()).isEqualTo("Item note");
    }

    @Test
    @DisplayName("statusDesc getter and setter")
    void statusDesc_getterSetter() {
        caseItem.setStatusDesc("Pending");
        assertThat(caseItem.getStatusDesc()).isEqualTo("Pending");
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        caseItem.setRowInsertTs(ts);
        assertThat(caseItem.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        caseItem.setRowUpdtTs(ts);
        assertThat(caseItem.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("item relationship getter and setter")
    void item_getterSetter() {
        ItemList itemList = new ItemList();
        itemList.setId(7);
        itemList.setItemDesc("Evidence bag");
        caseItem.setItem(itemList);
        assertThat(caseItem.getItem()).isEqualTo(itemList);
        assertThat(caseItem.getItem().getItemDesc()).isEqualTo("Evidence bag");
    }

    @Test
    @DisplayName("item is null by default")
    void item_nullByDefault() {
        assertThat(caseItem.getItem()).isNull();
    }

    @Test
    @DisplayName("dluCase relationship getter and setter")
    void dluCase_getterSetter() {
        DluCase dluCase = new DluCase();
        dluCase.setId(88);
        caseItem.setDluCase(dluCase);
        assertThat(caseItem.getDluCase()).isEqualTo(dluCase);
        assertThat(caseItem.getDluCase().getId()).isEqualTo(88);
    }

    @Test
    @DisplayName("dluCase is null by default")
    void dluCase_nullByDefault() {
        assertThat(caseItem.getDluCase()).isNull();
    }

    @Test
    @DisplayName("all string fields are null by default")
    void stringFields_nullByDefault() {
        assertThat(caseItem.getNoteDesc()).isNull();
        assertThat(caseItem.getStatusDesc()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(caseItem.getRowInsertTs()).isNull();
        assertThat(caseItem.getRowUpdtTs()).isNull();
    }
}


===============================

package org.nnnn.ddd.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseTag Entity Tests")
class CaseTagTest {

    private CaseTag caseTag;

    @BeforeEach
    void setUp() {
        caseTag = new CaseTag();
    }

    @Test
    @DisplayName("default constructor creates instance")
    void constructor_createsInstance() {
        assertThat(caseTag).isNotNull();
    }

    @Test
    @DisplayName("id getter and setter")
    void id_getterSetter() {
        caseTag.setId(22);
        assertThat(caseTag.getId()).isEqualTo(22);
    }

    @Test
    @DisplayName("rowInsertTs getter and setter")
    void rowInsertTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        caseTag.setRowInsertTs(ts);
        assertThat(caseTag.getRowInsertTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("rowUpdtTs getter and setter")
    void rowUpdtTs_getterSetter() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        caseTag.setRowUpdtTs(ts);
        assertThat(caseTag.getRowUpdtTs()).isEqualTo(ts);
    }

    @Test
    @DisplayName("tag relationship getter and setter")
    void tag_getterSetter() {
        TagList tagList = new TagList();
        tagList.setId(5);
        tagList.setTagDesc("Priority");
        caseTag.setTag(tagList);
        assertThat(caseTag.getTag()).isEqualTo(tagList);
        assertThat(caseTag.getTag().getTagDesc()).isEqualTo("Priority");
    }

    @Test
    @DisplayName("tag is null by default")
    void tag_nullByDefault() {
        assertThat(caseTag.getTag()).isNull();
    }

    @Test
    @DisplayName("dluCase relationship getter and setter")
    void dluCase_getterSetter() {
        DluCase dluCase = new DluCase();
        dluCase.setId(15);
        caseTag.setDluCase(dluCase);
        assertThat(caseTag.getDluCase()).isEqualTo(dluCase);
        assertThat(caseTag.getDluCase().getId()).isEqualTo(15);
    }

    @Test
    @DisplayName("dluCase is null by default")
    void dluCase_nullByDefault() {
        assertThat(caseTag.getDluCase()).isNull();
    }

    @Test
    @DisplayName("timestamp fields are null by default")
    void timestampFields_nullByDefault() {
        assertThat(caseTag.getRowInsertTs()).isNull();
        assertThat(caseTag.getRowUpdtTs()).isNull();
    }
}



