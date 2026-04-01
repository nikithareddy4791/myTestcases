

==================

package org.nypd.dlu.entity;

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
        office.setAdSgNm("SG-DLU-ANALYST-BK");
        assertThat(office.getAdSgNm()).isEqualTo("SG-DLU-ANALYST-BK");
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

package org.nypd.dlu.entity;

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


package org.nypd.dlu.entity;

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


package org.nypd.dlu.entity;

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

package org.nypd.dlu.entity;

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

package org.nypd.dlu.entity;

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


package org.nypd.dlu.entity;

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

package org.nypd.dlu.entity;

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

package org.nypd.dlu.entity;

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

package org.nypd.dlu.entity;

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



