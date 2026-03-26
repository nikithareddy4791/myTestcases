package org.nnnn.ddd.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for all entity classes.
 * Tests getter/setter, constructor, collections, and field defaults.
 */
@DisplayName("Entity Tests")
class EntityTests {

    // =========================================================================
    // AdaList
    // =========================================================================

    @Nested
    @DisplayName("AdaList Entity")
    class AdaListTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            AdaList ada = new AdaList();
            assertThat(ada).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            AdaList ada = new AdaList();
            ada.setId(1);
            ada.setFrstNm("John");
            ada.setLastNm("Doe");
            ada.setEmailAddrDesc("jdoe@nnnn.org");
            ada.setBoroughNm("Manhattan");
            ada.setBusPhoneNum("555-1234");
            ada.setCellPhoneNum("555-5678");
            ada.setFaxNum("555-9999");
            ada.setJobTitleDesc("ADA");
            ada.setNoteDesc("Note here");
            ada.setArchiveFlg((short) 0);
            ada.setInactiveFlg((short) 0);
            ada.setRowInsertTs(ts);
            ada.setRowUpdtTs(ts);

            assertThat(ada.getId()).isEqualTo(1);
            assertThat(ada.getFrstNm()).isEqualTo("John");
            assertThat(ada.getLastNm()).isEqualTo("Doe");
            assertThat(ada.getEmailAddrDesc()).isEqualTo("jdoe@nnnn.org");
            assertThat(ada.getBoroughNm()).isEqualTo("Manhattan");
            assertThat(ada.getBusPhoneNum()).isEqualTo("555-1234");
            assertThat(ada.getCellPhoneNum()).isEqualTo("555-5678");
            assertThat(ada.getFaxNum()).isEqualTo("555-9999");
            assertThat(ada.getJobTitleDesc()).isEqualTo("ADA");
            assertThat(ada.getNoteDesc()).isEqualTo("Note here");
            assertThat(ada.getArchiveFlg()).isEqualTo((short) 0);
            assertThat(ada.getInactiveFlg()).isEqualTo((short) 0);
            assertThat(ada.getRowInsertTs()).isEqualTo(ts);
            assertThat(ada.getRowUpdtTs()).isEqualTo(ts);
        }

        @Test
        @DisplayName("fields are null by default")
        void fields_areNullByDefault() {
            AdaList ada = new AdaList();
            assertThat(ada.getFrstNm()).isNull();
            assertThat(ada.getLastNm()).isNull();
            assertThat(ada.getEmailAddrDesc()).isNull();
        }
    }

    // =========================================================================
    // dddCase
    // =========================================================================

    @Nested
    @DisplayName("dddCase Entity")
    class DddCaseTests {

        @Test
        @DisplayName("default constructor creates instance with empty collections")
        void defaultConstructor_createsInstanceWithEmptyCollections() {
            dddCase c = new dddCase();
            assertThat(c).isNotNull();
            assertThat(c.getItems()).isNotNull().isEmpty();
            assertThat(c.getNotes()).isNotNull().isEmpty();
            assertThat(c.getTags()).isNotNull().isEmpty();
            assertThat(c.getUploads()).isNotNull().isEmpty();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Date now = new Date();
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            dddCase c = new dddCase();

            c.setId(100);
            c.setArrId("ARR001");
            c.setAssignedNm("jdoe");
            c.setDueDt(now);
            c.setRequestDt(now);
            c.setCompleteDt(now);
            c.setDvFlg((short) 1);
            c.setFelonyFlg((short) 1);
            c.setIndexFlg((short) 0);
            c.setProactiveFlg((short) 0);
            c.setParentId(50);
            c.setVersion(1L);
            c.setRowInsertTs(ts);
            c.setRowUpdtTs(ts);

            assertThat(c.getId()).isEqualTo(100);
            assertThat(c.getArrId()).isEqualTo("ARR001");
            assertThat(c.getAssignedNm()).isEqualTo("jdoe");
            assertThat(c.getDueDt()).isEqualTo(now);
            assertThat(c.getRequestDt()).isEqualTo(now);
            assertThat(c.getCompleteDt()).isEqualTo(now);
            assertThat(c.getDvFlg()).isEqualTo((short) 1);
            assertThat(c.getFelonyFlg()).isEqualTo((short) 1);
            assertThat(c.getIndexFlg()).isEqualTo((short) 0);
            assertThat(c.getProactiveFlg()).isEqualTo((short) 0);
            assertThat(c.getParentId()).isEqualTo(50);
            assertThat(c.getVersion()).isEqualTo(1L);
            assertThat(c.getRowInsertTs()).isEqualTo(ts);
            assertThat(c.getRowUpdtTs()).isEqualTo(ts);
        }

        @Test
        @DisplayName("setCategory and setAda and setStatus and setddd work")
        void relationships_setAndGetCorrectly() {
            dddCase c = new dddCase();
            CategoryList cat = new CategoryList();
            cat.setId(1);
            AdaList ada = new AdaList();
            ada.setId(2);
            StatusList status = new StatusList();
            status.setId(3);
            dddOfficeList office = new dddOfficeList();
            office.setId(4);

            c.setCategory(cat);
            c.setAda(ada);
            c.setStatus(status);
            c.setddd(office);

            assertThat(c.getCategory().getId()).isEqualTo(1);
            assertThat(c.getAda().getId()).isEqualTo(2);
            assertThat(c.getStatus().getId()).isEqualTo(3);
            assertThat(c.getddd().getId()).isEqualTo(4);
        }

        @Test
        @DisplayName("can add items to collections")
        void collections_canAddElements() {
            dddCase c = new dddCase();
            CaseItem item = new CaseItem();
            CaseNote note = new CaseNote();
            CaseTag tag = new CaseTag();
            CaseUpload upload = new CaseUpload();

            c.getItems().add(item);
            c.getNotes().add(note);
            c.getTags().add(tag);
            c.getUploads().add(upload);

            assertThat(c.getItems()).hasSize(1);
            assertThat(c.getNotes()).hasSize(1);
            assertThat(c.getTags()).hasSize(1);
            assertThat(c.getUploads()).hasSize(1);
        }
    }

    // =========================================================================
    // dddCodeRef
    // =========================================================================

    @Nested
    @DisplayName("dddCodeRef Entity")
    class DddCodeRefTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            dddCodeRef ref = new dddCodeRef();
            assertThat(ref).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            dddCodeRef ref = new dddCodeRef();

            ref.setId(1);
            ref.setCodeType("CHARGE_TYPE");
            ref.setCodeVal("F");
            ref.setCodeDesc("Felony");
            ref.setRowInsertTs(ts);
            ref.setRowUpdtTs(ts);

            assertThat(ref.getId()).isEqualTo(1);
            assertThat(ref.getCodeType()).isEqualTo("CHARGE_TYPE");
            assertThat(ref.getCodeVal()).isEqualTo("F");
            assertThat(ref.getCodeDesc()).isEqualTo("Felony");
            assertThat(ref.getRowInsertTs()).isEqualTo(ts);
            assertThat(ref.getRowUpdtTs()).isEqualTo(ts);
        }
    }

    // =========================================================================
    // dddOfficeList
    // =========================================================================

    @Nested
    @DisplayName("dddOfficeList Entity")
    class DddOfficeListTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            dddOfficeList office = new dddOfficeList();
            assertThat(office).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            dddOfficeList office = new dddOfficeList();

            office.setId(1);
            office.setdddOfficeDesc("Manhattan");
            office.setAdSgNm("ROLE_SG-ddd-ANALYST-MANHATTAN");
            office.setInactiveFlg((short) 0);
            office.setRowInsertTs(ts);
            office.setRowUpdtTs(ts);

            assertThat(office.getId()).isEqualTo(1);
            assertThat(office.getdddOfficeDesc()).isEqualTo("Manhattan");
            assertThat(office.getAdSgNm()).isEqualTo("ROLE_SG-ddd-ANALYST-MANHATTAN");
            assertThat(office.getInactiveFlg()).isEqualTo((short) 0);
            assertThat(office.getRowInsertTs()).isEqualTo(ts);
            assertThat(office.getRowUpdtTs()).isEqualTo(ts);
        }
    }

    // =========================================================================
    // CaseNote
    // =========================================================================

    @Nested
    @DisplayName("CaseNote Entity")
    class CaseNoteTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            CaseNote note = new CaseNote();
            assertThat(note).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            CaseNote note = new CaseNote();
            dddCase c = new dddCase();
            CaseUpload upload = new CaseUpload();

            note.setId(1);
            note.setNoteDesc("Test note");
            note.setUserNm("jdoe");
            note.setdddCase(c);
            note.setUpload(upload);
            note.setRowInsertTs(ts);
            note.setRowUpdtTs(ts);

            assertThat(note.getId()).isEqualTo(1);
            assertThat(note.getNoteDesc()).isEqualTo("Test note");
            assertThat(note.getUserNm()).isEqualTo("jdoe");
            assertThat(note.getdddCase()).isEqualTo(c);
            assertThat(note.getUpload()).isEqualTo(upload);
            assertThat(note.getRowInsertTs()).isEqualTo(ts);
            assertThat(note.getRowUpdtTs()).isEqualTo(ts);
        }

        @Test
        @DisplayName("upload is null by default")
        void upload_isNullByDefault() {
            CaseNote note = new CaseNote();
            assertThat(note.getUpload()).isNull();
        }
    }

    // =========================================================================
    // CategoryList
    // =========================================================================

    @Nested
    @DisplayName("CategoryList Entity")
    class CategoryListTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            CategoryList cat = new CategoryList();
            assertThat(cat).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            CategoryList cat = new CategoryList();

            cat.setId(1);
            cat.setCategoryDesc("Shootings");
            cat.setInactiveFlg((short) 0);
            cat.setRowInsertTs(ts);
            cat.setRowUpdtTs(ts);

            assertThat(cat.getId()).isEqualTo(1);
            assertThat(cat.getCategoryDesc()).isEqualTo("Shootings");
            assertThat(cat.getInactiveFlg()).isEqualTo((short) 0);
            assertThat(cat.getRowInsertTs()).isEqualTo(ts);
            assertThat(cat.getRowUpdtTs()).isEqualTo(ts);
        }
    }

    // =========================================================================
    // ItemList
    // =========================================================================

    @Nested
    @DisplayName("ItemList Entity")
    class ItemListTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            ItemList item = new ItemList();
            assertThat(item).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ItemList item = new ItemList();

            item.setId(1);
            item.setItemDesc("ECMS");
            item.setInactiveFlg((short) 0);
            item.setRowInsertTs(ts);
            item.setRowUpdtTs(ts);

            assertThat(item.getId()).isEqualTo(1);
            assertThat(item.getItemDesc()).isEqualTo("ECMS");
            assertThat(item.getInactiveFlg()).isEqualTo((short) 0);
            assertThat(item.getRowInsertTs()).isEqualTo(ts);
            assertThat(item.getRowUpdtTs()).isEqualTo(ts);
        }
    }

    // =========================================================================
    // StatusList
    // =========================================================================

    @Nested
    @DisplayName("StatusList Entity")
    class StatusListTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            StatusList status = new StatusList();
            assertThat(status).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            StatusList status = new StatusList();

            status.setId(1);
            status.setStatusDesc("Completed");
            status.setInactiveFlg((short) 0);
            status.setRowInsertTs(ts);
            status.setRowUpdtTs(ts);

            assertThat(status.getId()).isEqualTo(1);
            assertThat(status.getStatusDesc()).isEqualTo("Completed");
            assertThat(status.getInactiveFlg()).isEqualTo((short) 0);
            assertThat(status.getRowInsertTs()).isEqualTo(ts);
            assertThat(status.getRowUpdtTs()).isEqualTo(ts);
        }
    }

    // =========================================================================
    // TagList
    // =========================================================================

    @Nested
    @DisplayName("TagList Entity")
    class TagListTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            TagList tag = new TagList();
            assertThat(tag).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            TagList tag = new TagList();

            tag.setId(1);
            tag.setTagDesc("Gun");
            tag.setInactiveFlg((short) 0);
            tag.setRowInsertTs(ts);
            tag.setRowUpdtTs(ts);

            assertThat(tag.getId()).isEqualTo(1);
            assertThat(tag.getTagDesc()).isEqualTo("Gun");
            assertThat(tag.getInactiveFlg()).isEqualTo((short) 0);
            assertThat(tag.getRowInsertTs()).isEqualTo(ts);
            assertThat(tag.getRowUpdtTs()).isEqualTo(ts);
        }
    }

    // =========================================================================
    // CaseTag
    // =========================================================================

    @Nested
    @DisplayName("CaseTag Entity")
    class CaseTagTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            CaseTag caseTag = new CaseTag();
            assertThat(caseTag).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            CaseTag caseTag = new CaseTag();
            TagList tag = new TagList();
            tag.setId(1);
            dddCase c = new dddCase();
            c.setId(100);

            caseTag.setId(1);
            caseTag.setTag(tag);
            caseTag.setdddCase(c);
            caseTag.setRowInsertTs(ts);
            caseTag.setRowUpdtTs(ts);

            assertThat(caseTag.getId()).isEqualTo(1);
            assertThat(caseTag.getTag().getId()).isEqualTo(1);
            assertThat(caseTag.getdddCase().getId()).isEqualTo(100);
            assertThat(caseTag.getRowInsertTs()).isEqualTo(ts);
            assertThat(caseTag.getRowUpdtTs()).isEqualTo(ts);
        }

        @Test
        @DisplayName("tag is null by default")
        void tag_isNullByDefault() {
            CaseTag caseTag = new CaseTag();
            assertThat(caseTag.getTag()).isNull();
        }
    }

    // =========================================================================
    // CaseItem
    // =========================================================================

    @Nested
    @DisplayName("CaseItem Entity")
    class CaseItemTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            CaseItem item = new CaseItem();
            assertThat(item).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            CaseItem item = new CaseItem();
            ItemList itemList = new ItemList();
            itemList.setId(1);
            dddCase c = new dddCase();
            c.setId(100);

            item.setId(1);
            item.setNoteDesc("Evidence collected");
            item.setQuantity(3);
            item.setStatusDesc("Active");
            item.setItem(itemList);
            item.setdddCase(c);
            item.setRowInsertTs(ts);
            item.setRowUpdtTs(ts);

            assertThat(item.getId()).isEqualTo(1);
            assertThat(item.getNoteDesc()).isEqualTo("Evidence collected");
            assertThat(item.getQuantity()).isEqualTo(3);
            assertThat(item.getStatusDesc()).isEqualTo("Active");
            assertThat(item.getItem().getId()).isEqualTo(1);
            assertThat(item.getdddCase().getId()).isEqualTo(100);
            assertThat(item.getRowInsertTs()).isEqualTo(ts);
            assertThat(item.getRowUpdtTs()).isEqualTo(ts);
        }

        @Test
        @DisplayName("quantity is null by default")
        void quantity_isNullByDefault() {
            CaseItem item = new CaseItem();
            assertThat(item.getQuantity()).isNull();
        }
    }

    // =========================================================================
    // CaseUpload
    // =========================================================================

    @Nested
    @DisplayName("CaseUpload Entity")
    class CaseUploadTests {

        @Test
        @DisplayName("default constructor creates instance")
        void defaultConstructor_createsInstance() {
            CaseUpload upload = new CaseUpload();
            assertThat(upload).isNotNull();
        }

        @Test
        @DisplayName("getters and setters work correctly")
        void gettersAndSetters_workCorrectly() {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            CaseUpload upload = new CaseUpload();
            dddCase c = new dddCase();
            c.setId(100);

            upload.setId(1);
            upload.setFileNm("evidence.pdf");
            upload.setUserNm("jdoe");
            upload.setDeletedFlg((short) 0);
            upload.setdddCase(c);
            upload.setRowInsertTs(ts);
            upload.setRowUpdtTs(ts);

            assertThat(upload.getId()).isEqualTo(1);
            assertThat(upload.getFileNm()).isEqualTo("evidence.pdf");
            assertThat(upload.getUserNm()).isEqualTo("jdoe");
            assertThat(upload.getDeletedFlg()).isEqualTo((short) 0);
            assertThat(upload.getdddCase().getId()).isEqualTo(100);
            assertThat(upload.getRowInsertTs()).isEqualTo(ts);
            assertThat(upload.getRowUpdtTs()).isEqualTo(ts);
        }

        @Test
        @DisplayName("deletedFlg defaults to 0")
        void deletedFlg_defaultsToZero() {
            CaseUpload upload = new CaseUpload();
            assertThat(upload.getDeletedFlg()).isEqualTo((short) 0);
        }

        @Test
        @DisplayName("mark as deleted sets deletedFlg to 1")
        void markAsDeleted_setsFlagToOne() {
            CaseUpload upload = new CaseUpload();
            upload.setDeletedFlg((short) 1);
            assertThat(upload.getDeletedFlg()).isEqualTo((short) 1);
        }
    }
}
