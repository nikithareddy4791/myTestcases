package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("dddCaseSummary Model Tests")
class DddCaseSummaryTest {

    // =========================================================================
    // Default constructor
    // =========================================================================

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_createsInstanceWithNullFields() {
        dddCaseSummary s = new dddCaseSummary();
        assertThat(s).isNotNull();
        assertThat(s.getId()).isNull();
        assertThat(s.getArrId()).isNull();
        assertThat(s.getAssignedNm()).isNull();
        assertThat(s.getTags()).isNull();
        assertThat(s.getStatus()).isNull();
        assertThat(s.getArrest()).isNull();
    }

    // =========================================================================
    // Getters and Setters
    // =========================================================================

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter")
        void id_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            s.setId(100);
            assertThat(s.getId()).isEqualTo(100);
        }

        @Test
        @DisplayName("parentId getter and setter")
        void parentId_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            s.setParentId(50);
            assertThat(s.getParentId()).isEqualTo(50);
        }

        @Test
        @DisplayName("arrId getter and setter")
        void arrId_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            s.setArrId("ARR001");
            assertThat(s.getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("assignedNm getter and setter")
        void assignedNm_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            s.setAssignedNm("jdoe");
            assertThat(s.getAssignedNm()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("assignedNmInfo getter and setter")
        void assignedNmInfo_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            User user = new User();
            user.setUsername("jdoe");
            s.setAssignedNmInfo(user);
            assertThat(s.getAssignedNmInfo().getUsername()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("requestDt getter and setter")
        void requestDt_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            LocalDate date = LocalDate.now();
            s.setRequestDt(date);
            assertThat(s.getRequestDt()).isEqualTo(date);
        }

        @Test
        @DisplayName("dueDt getter and setter")
        void dueDt_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            LocalDate date = LocalDate.now().plusDays(30);
            s.setDueDt(date);
            assertThat(s.getDueDt()).isEqualTo(date);
        }

        @Test
        @DisplayName("completeDt getter and setter")
        void completeDt_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            LocalDate date = LocalDate.now();
            s.setCompleteDt(date);
            assertThat(s.getCompleteDt()).isEqualTo(date);
        }

        @Test
        @DisplayName("proactiveFlg getter and setter")
        void proactiveFlg_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            s.setProactiveFlg(1);
            assertThat(s.getProactiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("activeFlg getter and setter")
        void activeFlg_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            s.setActiveFlg(1);
            assertThat(s.getActiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("ddd getter and setter")
        void ddd_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            dddOffice office = new dddOffice();
            office.setId(3);
            s.setddd(office);
            assertThat(s.getddd().getId()).isEqualTo(3);
        }

        @Test
        @DisplayName("status getter and setter")
        void status_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            Status status = new Status();
            status.setId(2);
            s.setStatus(status);
            assertThat(s.getStatus().getId()).isEqualTo(2);
        }

        @Test
        @DisplayName("category getter and setter")
        void category_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            Category cat = new Category();
            cat.setId(1);
            s.setCategory(cat);
            assertThat(s.getCategory().getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("arrest getter and setter")
        void arrest_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            ArrestInfo arrest = new ArrestInfo();
            arrest.setArrId("ARR001");
            s.setArrest(arrest);
            assertThat(s.getArrest().getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("ada getter and setter")
        void ada_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            ADA ada = new ADA();
            ada.setId(1);
            s.setAda(ada);
            assertThat(s.getAda().getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("tags getter and setter")
        void tags_getterAndSetter() {
            dddCaseSummary s = new dddCaseSummary();
            CaseTag tag = new CaseTag();
            s.setTags(List.of(tag));
            assertThat(s.getTags()).hasSize(1);
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
        void fluent_id() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.id(100)).isSameAs(s);
            assertThat(s.getId()).isEqualTo(100);
        }

        @Test
        @DisplayName("fluent arrId returns same instance")
        void fluent_arrId() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.arrId("ARR001")).isSameAs(s);
            assertThat(s.getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("fluent assignedNm returns same instance")
        void fluent_assignedNm() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.assignedNm("jdoe")).isSameAs(s);
            assertThat(s.getAssignedNm()).isEqualTo("jdoe");
        }

        @Test
        @DisplayName("fluent status returns same instance")
        void fluent_status() {
            dddCaseSummary s = new dddCaseSummary();
            Status status = new Status();
            assertThat(s.status(status)).isSameAs(s);
            assertThat(s.getStatus()).isEqualTo(status);
        }

        @Test
        @DisplayName("fluent ddd returns same instance")
        void fluent_ddd() {
            dddCaseSummary s = new dddCaseSummary();
            dddOffice office = new dddOffice();
            assertThat(s.ddd(office)).isSameAs(s);
            assertThat(s.getddd()).isEqualTo(office);
        }

        @Test
        @DisplayName("fluent activeFlg returns same instance")
        void fluent_activeFlg() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.activeFlg(1)).isSameAs(s);
            assertThat(s.getActiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent arrest returns same instance")
        void fluent_arrest() {
            dddCaseSummary s = new dddCaseSummary();
            ArrestInfo arrest = new ArrestInfo();
            assertThat(s.arrest(arrest)).isSameAs(s);
            assertThat(s.getArrest()).isEqualTo(arrest);
        }

        @Test
        @DisplayName("fluent category returns same instance")
        void fluent_category() {
            dddCaseSummary s = new dddCaseSummary();
            Category cat = new Category();
            assertThat(s.category(cat)).isSameAs(s);
            assertThat(s.getCategory()).isEqualTo(cat);
        }

        @Test
        @DisplayName("fluent ada returns same instance")
        void fluent_ada() {
            dddCaseSummary s = new dddCaseSummary();
            ADA ada = new ADA();
            assertThat(s.ada(ada)).isSameAs(s);
            assertThat(s.getAda()).isEqualTo(ada);
        }

        @Test
        @DisplayName("fluent tags returns same instance")
        void fluent_tags() {
            dddCaseSummary s = new dddCaseSummary();
            List<CaseTag> tags = List.of(new CaseTag());
            assertThat(s.tags(tags)).isSameAs(s);
            assertThat(s.getTags()).isEqualTo(tags);
        }

        @Test
        @DisplayName("fluent proactiveFlg returns same instance")
        void fluent_proactiveFlg() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.proactiveFlg(1)).isSameAs(s);
            assertThat(s.getProactiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent parentId returns same instance")
        void fluent_parentId() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.parentId(50)).isSameAs(s);
            assertThat(s.getParentId()).isEqualTo(50);
        }
    }

    // =========================================================================
    // addTagsItem
    // =========================================================================

    @Nested
    @DisplayName("addTagsItem")
    class AddTagsItemTests {

        @Test
        @DisplayName("initializes list when null and adds item")
        void addTagsItem_initializesAndAdds() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.getTags()).isNull();
            s.addTagsItem(new CaseTag());
            assertThat(s.getTags()).hasSize(1);
        }

        @Test
        @DisplayName("appends to existing list")
        void addTagsItem_appendsToExisting() {
            dddCaseSummary s = new dddCaseSummary();
            s.addTagsItem(new CaseTag());
            s.addTagsItem(new CaseTag());
            assertThat(s.getTags()).hasSize(2);
        }

        @Test
        @DisplayName("returns same instance")
        void addTagsItem_returnsSameInstance() {
            dddCaseSummary s = new dddCaseSummary();
            assertThat(s.addTagsItem(new CaseTag())).isSameAs(s);
        }
    }

    // =========================================================================
    // equals() and hashCode()
    // =========================================================================

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("same fields are equal")
        void equals_sameFields_returnsTrue() {
            dddCaseSummary a = new dddCaseSummary();
            a.setId(100);
            a.setArrId("ARR001");
            dddCaseSummary b = new dddCaseSummary();
            b.setId(100);
            b.setArrId("ARR001");
            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId_returnsFalse() {
            dddCaseSummary a = new dddCaseSummary();
            a.setId(100);
            dddCaseSummary b = new dddCaseSummary();
            b.setId(200);
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            dddCaseSummary a = new dddCaseSummary();
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null_returnsFalse() {
            assertThat(new dddCaseSummary()).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType_returnsFalse() {
            assertThat(new dddCaseSummary()).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            dddCaseSummary a = new dddCaseSummary();
            a.setId(100);
            dddCaseSummary b = new dddCaseSummary();
            b.setId(100);
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
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new dddCaseSummary().toString()).contains("dddCaseSummary");
        }

        @Test
        @DisplayName("contains id value when set")
        void toString_containsId() {
            dddCaseSummary s = new dddCaseSummary();
            s.setId(100);
            assertThat(s.toString()).contains("100");
        }

        @Test
        @DisplayName("contains arrId when set")
        void toString_containsArrId() {
            dddCaseSummary s = new dddCaseSummary();
            s.setArrId("ARR001");
            assertThat(s.toString()).contains("ARR001");
        }

        @Test
        @DisplayName("shows null for unset fields")
        void toString_showsNull() {
            assertThat(new dddCaseSummary().toString()).contains("null");
        }
    }
}
