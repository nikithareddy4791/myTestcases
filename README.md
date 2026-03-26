package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("dddCase Model Tests")
class DddCaseModelTest {

    // =========================================================================
    // Default constructor
    // =========================================================================

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_createsInstanceWithNullFields() {
        dddCase c = new dddCase();
        assertThat(c).isNotNull();
        assertThat(c.getId()).isNull();
        assertThat(c.getArrId()).isNull();
        assertThat(c.getAssignedNm()).isNull();
        assertThat(c.getVersion()).isNull();
        assertThat(c.isNew()).isFalse();
    }

    // =========================================================================
    // Getters and Setters
    // =========================================================================

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter work correctly")
        void id_getterAndSetter() {
            dddCase c = new dddCase();
            c.setId(100);
            assertThat(c.getId()).isEqualTo(100);
        }

        @Test
        @DisplayName("parentId getter and setter work correctly")
        void parentId_getterAndSetter() {
            dddCase c = new dddCase();
            c.setParentId(50);
            assertThat(c.getParentId()).isEqualTo(50);
        }

        @Test
        @DisplayName("arrId getter and setter work correctly")
        void arrId_getterAndSetter() {
            dddCase c = new dddCase();
            c.setArrId("ARR001");
            assertThat(c.getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("assignedNm getter and setter work correctly")
        void assignedNm_getterAndSetter() {
            dddCase c = new dddCase();
            c.setAssignedNm("jdoe");
            assertThat(c.getAssignedNm()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("assignedNmInfo getter and setter work correctly")
        void assignedNmInfo_getterAndSetter() {
            dddCase c = new dddCase();
            User user = new User();
            user.setUsername("jdoe");
            c.setAssignedNmInfo(user);
            assertThat(c.getAssignedNmInfo().getUsername()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("requestDt getter and setter work correctly")
        void requestDt_getterAndSetter() {
            dddCase c = new dddCase();
            LocalDate date = LocalDate.now();
            c.setRequestDt(date);
            assertThat(c.getRequestDt()).isEqualTo(date);
        }

        @Test
        @DisplayName("dueDt getter and setter work correctly")
        void dueDt_getterAndSetter() {
            dddCase c = new dddCase();
            LocalDate date = LocalDate.now().plusDays(30);
            c.setDueDt(date);
            assertThat(c.getDueDt()).isEqualTo(date);
        }

        @Test
        @DisplayName("completeDt getter and setter work correctly")
        void completeDt_getterAndSetter() {
            dddCase c = new dddCase();
            LocalDate date = LocalDate.now();
            c.setCompleteDt(date);
            assertThat(c.getCompleteDt()).isEqualTo(date);
        }

        @Test
        @DisplayName("proactiveFlg getter and setter work correctly")
        void proactiveFlg_getterAndSetter() {
            dddCase c = new dddCase();
            c.setProactiveFlg(1);
            assertThat(c.getProactiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("activeFlg getter and setter work correctly")
        void activeFlg_getterAndSetter() {
            dddCase c = new dddCase();
            c.setActiveFlg(1);
            assertThat(c.getActiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("version getter and setter work correctly")
        void version_getterAndSetter() {
            dddCase c = new dddCase();
            c.setVersion(3L);
            assertThat(c.getVersion()).isEqualTo(3L);
        }

        @Test
        @DisplayName("isNew getter and setter work correctly")
        void isNew_getterAndSetter() {
            dddCase c = new dddCase();
            assertThat(c.isNew()).isFalse();
            c.setNew(true);
            assertThat(c.isNew()).isTrue();
            c.setNew(false);
            assertThat(c.isNew()).isFalse();
        }

        @Test
        @DisplayName("category getter and setter work correctly")
        void category_getterAndSetter() {
            dddCase c = new dddCase();
            Category cat = new Category();
            cat.setId(1);
            c.setCategory(cat);
            assertThat(c.getCategory().getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("status getter and setter work correctly")
        void status_getterAndSetter() {
            dddCase c = new dddCase();
            Status status = new Status();
            status.setId(2);
            c.setStatus(status);
            assertThat(c.getStatus().getId()).isEqualTo(2);
        }

        @Test
        @DisplayName("ddd getter and setter work correctly")
        void ddd_getterAndSetter() {
            dddCase c = new dddCase();
            dddOffice office = new dddOffice();
            office.setId(3);
            c.setddd(office);
            assertThat(c.getddd().getId()).isEqualTo(3);
        }

        @Test
        @DisplayName("arrest getter and setter work correctly")
        void arrest_getterAndSetter() {
            dddCase c = new dddCase();
            ArrestInfo arrest = new ArrestInfo();
            arrest.setArrId("ARR001");
            c.setArrest(arrest);
            assertThat(c.getArrest().getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("ada getter and setter work correctly")
        void ada_getterAndSetter() {
            dddCase c = new dddCase();
            ADA ada = new ADA();
            ada.setId(1);
            c.setAda(ada);
            assertThat(c.getAda().getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("tags getter and setter work correctly")
        void tags_getterAndSetter() {
            dddCase c = new dddCase();
            CaseTag tag = new CaseTag();
            c.setTags(List.of(tag));
            assertThat(c.getTags()).hasSize(1);
        }

        @Test
        @DisplayName("items getter and setter work correctly")
        void items_getterAndSetter() {
            dddCase c = new dddCase();
            CaseItem item = new CaseItem();
            c.setItems(List.of(item));
            assertThat(c.getItems()).hasSize(1);
        }

        @Test
        @DisplayName("notes getter and setter work correctly")
        void notes_getterAndSetter() {
            dddCase c = new dddCase();
            CaseNote note = new CaseNote();
            c.setNotes(List.of(note));
            assertThat(c.getNotes()).hasSize(1);
        }

        @Test
        @DisplayName("uploads getter and setter work correctly")
        void uploads_getterAndSetter() {
            dddCase c = new dddCase();
            CaseUpload upload = new CaseUpload();
            c.setUploads(List.of(upload));
            assertThat(c.getUploads()).hasSize(1);
        }

        @Test
        @DisplayName("rowInsertTs getter and setter work correctly")
        void rowInsertTs_getterAndSetter() {
            dddCase c = new dddCase();
            OffsetDateTime now = OffsetDateTime.now();
            c.setRowInsertTs(now);
            assertThat(c.getRowInsertTs()).isEqualTo(now);
        }

        @Test
        @DisplayName("rowUpdtTs getter and setter work correctly")
        void rowUpdtTs_getterAndSetter() {
            dddCase c = new dddCase();
            OffsetDateTime now = OffsetDateTime.now();
            c.setRowUpdtTs(now);
            assertThat(c.getRowUpdtTs()).isEqualTo(now);
        }
    }

    // =========================================================================
    // Fluent builder methods
    // =========================================================================

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent id returns same instance")
        void fluent_id_returnsSameInstance() {
            dddCase c = new dddCase();
            assertThat(c.id(100)).isSameAs(c);
            assertThat(c.getId()).isEqualTo(100);
        }

        @Test
        @DisplayName("fluent arrId returns same instance")
        void fluent_arrId_returnsSameInstance() {
            dddCase c = new dddCase();
            assertThat(c.arrId("ARR001")).isSameAs(c);
            assertThat(c.getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("fluent assignedNm returns same instance")
        void fluent_assignedNm_returnsSameInstance() {
            dddCase c = new dddCase();
            assertThat(c.assignedNm("jdoe")).isSameAs(c);
            assertThat(c.getAssignedNm()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("fluent status returns same instance")
        void fluent_status_returnsSameInstance() {
            dddCase c = new dddCase();
            Status status = new Status();
            assertThat(c.status(status)).isSameAs(c);
            assertThat(c.getStatus()).isEqualTo(status);
        }

        @Test
        @DisplayName("fluent version returns same instance")
        void fluent_version_returnsSameInstance() {
            dddCase c = new dddCase();
            assertThat(c.version(1L)).isSameAs(c);
            assertThat(c.getVersion()).isEqualTo(1L);
        }

        @Test
        @DisplayName("fluent activeFlg returns same instance")
        void fluent_activeFlg_returnsSameInstance() {
            dddCase c = new dddCase();
            assertThat(c.activeFlg(1)).isSameAs(c);
            assertThat(c.getActiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent arrest returns same instance")
        void fluent_arrest_returnsSameInstance() {
            dddCase c = new dddCase();
            ArrestInfo arrest = new ArrestInfo();
            assertThat(c.arrest(arrest)).isSameAs(c);
            assertThat(c.getArrest()).isEqualTo(arrest);
        }
    }

    // =========================================================================
    // addXxxItem methods — initialize list and add
    // =========================================================================

    @Nested
    @DisplayName("addXxxItem Methods")
    class AddItemTests {

        @Test
        @DisplayName("addRelatedCasesItem initializes list and adds")
        void addRelatedCasesItem_initializesAndAdds() {
            dddCase c = new dddCase();
            assertThat(c.getRelatedCases()).isNull();
            c.addRelatedCasesItem(new dddCase());
            assertThat(c.getRelatedCases()).hasSize(1);
        }

        @Test
        @DisplayName("addTagsItem initializes list and adds")
        void addTagsItem_initializesAndAdds() {
            dddCase c = new dddCase();
            assertThat(c.getTags()).isNull();
            c.addTagsItem(new CaseTag());
            assertThat(c.getTags()).hasSize(1);
        }

        @Test
        @DisplayName("addItemsItem initializes list and adds")
        void addItemsItem_initializesAndAdds() {
            dddCase c = new dddCase();
            assertThat(c.getItems()).isNull();
            c.addItemsItem(new CaseItem());
            assertThat(c.getItems()).hasSize(1);
        }

        @Test
        @DisplayName("addNotesItem initializes list and adds")
        void addNotesItem_initializesAndAdds() {
            dddCase c = new dddCase();
            assertThat(c.getNotes()).isNull();
            c.addNotesItem(new CaseNote());
            assertThat(c.getNotes()).hasSize(1);
        }

        @Test
        @DisplayName("addUploadsItem initializes list and adds")
        void addUploadsItem_initializesAndAdds() {
            dddCase c = new dddCase();
            assertThat(c.getUploads()).isNull();
            c.addUploadsItem(new CaseUpload());
            assertThat(c.getUploads()).hasSize(1);
        }

        @Test
        @DisplayName("addRelatedCasesItem returns same instance")
        void addRelatedCasesItem_returnsSameInstance() {
            dddCase c = new dddCase();
            assertThat(c.addRelatedCasesItem(new dddCase())).isSameAs(c);
        }

        @Test
        @DisplayName("addTagsItem appends to existing list")
        void addTagsItem_appendsToExistingList() {
            dddCase c = new dddCase();
            c.addTagsItem(new CaseTag());
            c.addTagsItem(new CaseTag());
            assertThat(c.getTags()).hasSize(2);
        }
    }

    // =========================================================================
    // equals() and hashCode()
    // =========================================================================

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("two instances with same id and arrId are equal")
        void equals_sameFields_returnsTrue() {
            dddCase a = new dddCase();
            a.setId(100);
            a.setArrId("ARR001");

            dddCase b = new dddCase();
            b.setId(100);
            b.setArrId("ARR001");

            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("two instances with different id are not equal")
        void equals_differentId_returnsFalse() {
            dddCase a = new dddCase();
            a.setId(100);
            dddCase b = new dddCase();
            b.setId(200);

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("same instance is equal to itself")
        void equals_sameInstance_returnsTrue() {
            dddCase a = new dddCase();
            a.setId(100);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("instance is not equal to null")
        void equals_null_returnsFalse() {
            assertThat(new dddCase()).isNotEqualTo(null);
        }

        @Test
        @DisplayName("instance is not equal to different type")
        void equals_differentType_returnsFalse() {
            assertThat(new dddCase()).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances_sameHashCode() {
            dddCase a = new dddCase();
            a.setId(100);
            a.setArrId("ARR001");

            dddCase b = new dddCase();
            b.setId(100);
            b.setArrId("ARR001");

            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("toString contains class name")
        void toString_containsClassName() {
            assertThat(new dddCase().toString()).contains("dddCase");
        }

        @Test
        @DisplayName("toString contains id value when set")
        void toString_containsIdValue() {
            dddCase c = new dddCase();
            c.setId(100);
            assertThat(c.toString()).contains("100");
        }

        @Test
        @DisplayName("toString contains arrId value when set")
        void toString_containsArrId() {
            dddCase c = new dddCase();
            c.setArrId("ARR001");
            assertThat(c.toString()).contains("ARR001");
        }

        @Test
        @DisplayName("toString shows null for unset fields")
        void toString_showsNullForUnsetFields() {
            assertThat(new dddCase().toString()).contains("null");
        }
    }
}
