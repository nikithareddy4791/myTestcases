package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CreateCaseRequest Model Tests")
class CreateCaseRequestTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CreateCaseRequest r = new CreateCaseRequest();
        assertThat(r).isNotNull();
        assertThat(r.getArrId()).isNull();
        assertThat(r.getParentId()).isNull();
        assertThat(r.getProactiveFlg()).isNull();
        assertThat(r.getdddOfficeId()).isNull();
        assertThat(r.getRequestDt()).isNull();
        assertThat(r.getDueDt()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void arrId_getterAndSetter() {
            CreateCaseRequest r = new CreateCaseRequest();
            r.setArrId("ARR001");
            assertThat(r.getArrId()).isEqualTo("ARR001");
        }

        @Test void parentId_getterAndSetter() {
            CreateCaseRequest r = new CreateCaseRequest();
            r.setParentId(50);
            assertThat(r.getParentId()).isEqualTo(50);
        }

        @Test void proactiveFlg_active() {
            CreateCaseRequest r = new CreateCaseRequest();
            r.setProactiveFlg(1);
            assertThat(r.getProactiveFlg()).isEqualTo(1);
        }

        @Test void proactiveFlg_inactive() {
            CreateCaseRequest r = new CreateCaseRequest();
            r.setProactiveFlg(0);
            assertThat(r.getProactiveFlg()).isEqualTo(0);
        }

        @Test void dddOfficeId_getterAndSetter() {
            CreateCaseRequest r = new CreateCaseRequest();
            r.setdddOfficeId(3);
            assertThat(r.getdddOfficeId()).isEqualTo(3);
        }

        @Test void requestDt_getterAndSetter() {
            CreateCaseRequest r = new CreateCaseRequest();
            LocalDate date = LocalDate.now();
            r.setRequestDt(date);
            assertThat(r.getRequestDt()).isEqualTo(date);
        }

        @Test void dueDt_getterAndSetter() {
            CreateCaseRequest r = new CreateCaseRequest();
            LocalDate date = LocalDate.now().plusDays(30);
            r.setDueDt(date);
            assertThat(r.getDueDt()).isEqualTo(date);
        }

        @Test void allFields_setIndependently() {
            CreateCaseRequest r = new CreateCaseRequest();
            r.setArrId("ARR001");
            r.setParentId(50);
            r.setProactiveFlg(0);
            r.setdddOfficeId(1);
            assertThat(r.getArrId()).isEqualTo("ARR001");
            assertThat(r.getParentId()).isEqualTo(50);
            assertThat(r.getProactiveFlg()).isEqualTo(0);
            assertThat(r.getdddOfficeId()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_arrId() {
            CreateCaseRequest r = new CreateCaseRequest();
            assertThat(r.arrId("ARR001")).isSameAs(r);
            assertThat(r.getArrId()).isEqualTo("ARR001");
        }

        @Test void fluent_parentId() {
            CreateCaseRequest r = new CreateCaseRequest();
            assertThat(r.parentId(50)).isSameAs(r);
            assertThat(r.getParentId()).isEqualTo(50);
        }

        @Test void fluent_proactiveFlg() {
            CreateCaseRequest r = new CreateCaseRequest();
            assertThat(r.proactiveFlg(1)).isSameAs(r);
            assertThat(r.getProactiveFlg()).isEqualTo(1);
        }

        @Test void fluent_dddOfficeId() {
            CreateCaseRequest r = new CreateCaseRequest();
            assertThat(r.dddOfficeId(1)).isSameAs(r);
            assertThat(r.getdddOfficeId()).isEqualTo(1);
        }

        @Test void fluent_requestDt() {
            CreateCaseRequest r = new CreateCaseRequest();
            LocalDate date = LocalDate.now();
            assertThat(r.requestDt(date)).isSameAs(r);
            assertThat(r.getRequestDt()).isEqualTo(date);
        }

        @Test void fluent_dueDt() {
            CreateCaseRequest r = new CreateCaseRequest();
            LocalDate date = LocalDate.now().plusDays(30);
            assertThat(r.dueDt(date)).isSameAs(r);
            assertThat(r.getDueDt()).isEqualTo(date);
        }

        @Test void fluent_chaining() {
            CreateCaseRequest r = new CreateCaseRequest()
                    .arrId("ARR001").proactiveFlg(0).dddOfficeId(1);
            assertThat(r.getArrId()).isEqualTo("ARR001");
            assertThat(r.getProactiveFlg()).isEqualTo(0);
            assertThat(r.getdddOfficeId()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CreateCaseRequest a = new CreateCaseRequest().arrId("ARR001").proactiveFlg(0).dddOfficeId(1);
            CreateCaseRequest b = new CreateCaseRequest().arrId("ARR001").proactiveFlg(0).dddOfficeId(1);
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentArrId() {
            assertThat(new CreateCaseRequest().arrId("ARR001"))
                    .isNotEqualTo(new CreateCaseRequest().arrId("ARR002"));
        }

        @Test void equals_differentOfficeId() {
            assertThat(new CreateCaseRequest().dddOfficeId(1))
                    .isNotEqualTo(new CreateCaseRequest().dddOfficeId(2));
        }

        @Test void equals_sameInstance() {
            CreateCaseRequest a = new CreateCaseRequest().arrId("ARR001");
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CreateCaseRequest()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CreateCaseRequest()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CreateCaseRequest().arrId("ARR001").proactiveFlg(0).hashCode())
                    .isEqualTo(new CreateCaseRequest().arrId("ARR001").proactiveFlg(0).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CreateCaseRequest().toString()).contains("CreateCaseRequest");
        }

        @Test void toString_containsValues() {
            CreateCaseRequest r = new CreateCaseRequest().arrId("ARR001").dddOfficeId(1);
            assertThat(r.toString()).contains("ARR001").contains("1");
        }

        @Test void toString_nullFields() {
            assertThat(new CreateCaseRequest().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CreateCaseRequest r = new CreateCaseRequest().arrId("line1\nline2");
            assertThat(r.toString()).contains("line1").contains("line2");
        }
    }
}



@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseItem Model Tests")
class CaseItemModelTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseItem i = new CaseItem();
        assertThat(i).isNotNull();
        assertThat(i.getId()).isNull();
        assertThat(i.getCaseId()).isNull();
        assertThat(i.getItemId()).isNull();
        assertThat(i.getItemDesc()).isNull();
        assertThat(i.getStatusDesc()).isNull();
        assertThat(i.getNoteDesc()).isNull();
        assertThat(i.getQuantity()).isNull();
        assertThat(i.getRowUpdtTs()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void id_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setId(1);
            assertThat(i.getId()).isEqualTo(1);
        }

        @Test void caseId_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setCaseId(100);
            assertThat(i.getCaseId()).isEqualTo(100);
        }

        @Test void itemId_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setItemId(5);
            assertThat(i.getItemId()).isEqualTo(5);
        }

        @Test void itemDesc_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setItemDesc("ECMS");
            assertThat(i.getItemDesc()).isEqualTo("ECMS");
        }

        @Test void statusDesc_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setStatusDesc("Active");
            assertThat(i.getStatusDesc()).isEqualTo("Active");
        }

        @Test void noteDesc_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setNoteDesc("Evidence collected");
            assertThat(i.getNoteDesc()).isEqualTo("Evidence collected");
        }

        @Test void quantity_getterAndSetter() {
            CaseItem i = new CaseItem();
            i.setQuantity(3);
            assertThat(i.getQuantity()).isEqualTo(3);
        }

        @Test void rowUpdtTs_getterAndSetter() {
            CaseItem i = new CaseItem();
            OffsetDateTime now = OffsetDateTime.now();
            i.setRowUpdtTs(now);
            assertThat(i.getRowUpdtTs()).isEqualTo(now);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_id() {
            CaseItem i = new CaseItem();
            assertThat(i.id(1)).isSameAs(i);
            assertThat(i.getId()).isEqualTo(1);
        }

        @Test void fluent_caseId() {
            CaseItem i = new CaseItem();
            assertThat(i.caseId(100)).isSameAs(i);
            assertThat(i.getCaseId()).isEqualTo(100);
        }

        @Test void fluent_itemId() {
            CaseItem i = new CaseItem();
            assertThat(i.itemId(5)).isSameAs(i);
            assertThat(i.getItemId()).isEqualTo(5);
        }

        @Test void fluent_itemDesc() {
            CaseItem i = new CaseItem();
            assertThat(i.itemDesc("ECMS")).isSameAs(i);
            assertThat(i.getItemDesc()).isEqualTo("ECMS");
        }

        @Test void fluent_statusDesc() {
            CaseItem i = new CaseItem();
            assertThat(i.statusDesc("Active")).isSameAs(i);
            assertThat(i.getStatusDesc()).isEqualTo("Active");
        }

        @Test void fluent_noteDesc() {
            CaseItem i = new CaseItem();
            assertThat(i.noteDesc("Note")).isSameAs(i);
            assertThat(i.getNoteDesc()).isEqualTo("Note");
        }

        @Test void fluent_quantity() {
            CaseItem i = new CaseItem();
            assertThat(i.quantity(3)).isSameAs(i);
            assertThat(i.getQuantity()).isEqualTo(3);
        }

        @Test void fluent_chaining() {
            CaseItem i = new CaseItem().id(1).caseId(100).itemId(5).itemDesc("ECMS").quantity(2);
            assertThat(i.getId()).isEqualTo(1);
            assertThat(i.getCaseId()).isEqualTo(100);
            assertThat(i.getItemId()).isEqualTo(5);
            assertThat(i.getItemDesc()).isEqualTo("ECMS");
            assertThat(i.getQuantity()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseItem a = new CaseItem().id(1).caseId(100).itemId(5).itemDesc("ECMS").quantity(2);
            CaseItem b = new CaseItem().id(1).caseId(100).itemId(5).itemDesc("ECMS").quantity(2);
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentId() {
            assertThat(new CaseItem().id(1)).isNotEqualTo(new CaseItem().id(2));
        }

        @Test void equals_differentItemId() {
            assertThat(new CaseItem().itemId(1)).isNotEqualTo(new CaseItem().itemId(2));
        }

        @Test void equals_differentQuantity() {
            assertThat(new CaseItem().quantity(1)).isNotEqualTo(new CaseItem().quantity(2));
        }

        @Test void equals_sameInstance() {
            CaseItem a = new CaseItem().id(1);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseItem()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseItem()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseItem().id(1).caseId(100).itemId(5).hashCode())
                    .isEqualTo(new CaseItem().id(1).caseId(100).itemId(5).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseItem().toString()).contains("CaseItem");
        }

        @Test void toString_containsValues() {
            CaseItem i = new CaseItem().id(1).itemDesc("ECMS").quantity(3);
            assertThat(i.toString()).contains("1").contains("ECMS").contains("3");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseItem().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CaseItem i = new CaseItem().noteDesc("line1\nline2");
            assertThat(i.toString()).contains("line1").contains("line2");
        }
    }
}

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseListResponse Model Tests")
class CaseListResponseTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseListResponse r = new CaseListResponse();
        assertThat(r).isNotNull();
        assertThat(r.getTotalElements()).isNull();
        assertThat(r.getTotalPages()).isNull();
        assertThat(r.getPageSize()).isNull();
        assertThat(r.getPageNumber()).isNull();
        assertThat(r.isIsFirst()).isNull();
        assertThat(r.isIsLast()).isNull();
        assertThat(r.isHasNext()).isNull();
        assertThat(r.isHasPrevious()).isNull();
        assertThat(r.getCaseSummaries()).isNull();
        assertThat(r.getCaseStats()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void totalElements_getterAndSetter() {
            CaseListResponse r = new CaseListResponse();
            r.setTotalElements(100L);
            assertThat(r.getTotalElements()).isEqualTo(100L);
        }

        @Test void totalPages_getterAndSetter() {
            CaseListResponse r = new CaseListResponse();
            r.setTotalPages(5);
            assertThat(r.getTotalPages()).isEqualTo(5);
        }

        @Test void pageSize_getterAndSetter() {
            CaseListResponse r = new CaseListResponse();
            r.setPageSize(20);
            assertThat(r.getPageSize()).isEqualTo(20);
        }

        @Test void pageNumber_getterAndSetter() {
            CaseListResponse r = new CaseListResponse();
            r.setPageNumber(0);
            assertThat(r.getPageNumber()).isEqualTo(0);
        }

        @Test void isFirst_true() {
            CaseListResponse r = new CaseListResponse();
            r.setIsFirst(true);
            assertThat(r.isIsFirst()).isTrue();
        }

        @Test void isFirst_false() {
            CaseListResponse r = new CaseListResponse();
            r.setIsFirst(false);
            assertThat(r.isIsFirst()).isFalse();
        }

        @Test void isLast_true() {
            CaseListResponse r = new CaseListResponse();
            r.setIsLast(true);
            assertThat(r.isIsLast()).isTrue();
        }

        @Test void hasNext_true() {
            CaseListResponse r = new CaseListResponse();
            r.setHasNext(true);
            assertThat(r.isHasNext()).isTrue();
        }

        @Test void hasPrevious_false() {
            CaseListResponse r = new CaseListResponse();
            r.setHasPrevious(false);
            assertThat(r.isHasPrevious()).isFalse();
        }

        @Test void caseSummaries_getterAndSetter() {
            CaseListResponse r = new CaseListResponse();
            dddCaseSummary summary = new dddCaseSummary().id(1);
            r.setCaseSummaries(List.of(summary));
            assertThat(r.getCaseSummaries()).hasSize(1);
        }

        @Test void caseStats_getterAndSetter() {
            CaseListResponse r = new CaseListResponse();
            dddCaseStats stats = new dddCaseStats().overdueCount(3).comingDueCount(2);
            r.setCaseStats(stats);
            assertThat(r.getCaseStats().getOverdueCount()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_totalElements() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.totalElements(100L)).isSameAs(r);
            assertThat(r.getTotalElements()).isEqualTo(100L);
        }

        @Test void fluent_totalPages() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.totalPages(5)).isSameAs(r);
            assertThat(r.getTotalPages()).isEqualTo(5);
        }

        @Test void fluent_pageSize() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.pageSize(20)).isSameAs(r);
            assertThat(r.getPageSize()).isEqualTo(20);
        }

        @Test void fluent_pageNumber() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.pageNumber(0)).isSameAs(r);
            assertThat(r.getPageNumber()).isEqualTo(0);
        }

        @Test void fluent_isFirst() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.isFirst(true)).isSameAs(r);
            assertThat(r.isIsFirst()).isTrue();
        }

        @Test void fluent_isLast() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.isLast(false)).isSameAs(r);
            assertThat(r.isIsLast()).isFalse();
        }

        @Test void fluent_hasNext() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.hasNext(true)).isSameAs(r);
            assertThat(r.isHasNext()).isTrue();
        }

        @Test void fluent_hasPrevious() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.hasPrevious(false)).isSameAs(r);
            assertThat(r.isHasPrevious()).isFalse();
        }

        @Test void fluent_chaining() {
            CaseListResponse r = new CaseListResponse()
                    .totalElements(100L).totalPages(5).pageSize(20).pageNumber(0)
                    .isFirst(true).isLast(false).hasNext(true).hasPrevious(false);
            assertThat(r.getTotalElements()).isEqualTo(100L);
            assertThat(r.getTotalPages()).isEqualTo(5);
            assertThat(r.isIsFirst()).isTrue();
            assertThat(r.isHasNext()).isTrue();
        }
    }

    @Nested
    @DisplayName("addCaseSummariesItem")
    class AddItemTests {

        @Test void addCaseSummariesItem_initializesAndAdds() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.getCaseSummaries()).isNull();
            r.addCaseSummariesItem(new dddCaseSummary());
            assertThat(r.getCaseSummaries()).hasSize(1);
        }

        @Test void addCaseSummariesItem_appendsToExisting() {
            CaseListResponse r = new CaseListResponse();
            r.addCaseSummariesItem(new dddCaseSummary());
            r.addCaseSummariesItem(new dddCaseSummary());
            assertThat(r.getCaseSummaries()).hasSize(2);
        }

        @Test void addCaseSummariesItem_returnsSameInstance() {
            CaseListResponse r = new CaseListResponse();
            assertThat(r.addCaseSummariesItem(new dddCaseSummary())).isSameAs(r);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseListResponse a = new CaseListResponse().totalElements(100L).totalPages(5);
            CaseListResponse b = new CaseListResponse().totalElements(100L).totalPages(5);
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentTotalElements() {
            assertThat(new CaseListResponse().totalElements(100L))
                    .isNotEqualTo(new CaseListResponse().totalElements(200L));
        }

        @Test void equals_sameInstance() {
            CaseListResponse a = new CaseListResponse().totalElements(100L);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseListResponse()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseListResponse()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseListResponse().totalElements(100L).totalPages(5).hashCode())
                    .isEqualTo(new CaseListResponse().totalElements(100L).totalPages(5).hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseListResponse().toString()).contains("CaseListResponse");
        }

        @Test void toString_containsValues() {
            CaseListResponse r = new CaseListResponse().totalElements(100L).pageSize(20);
            assertThat(r.toString()).contains("100").contains("20");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseListResponse().toString()).contains("null");
        }
    }
}

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User Model Tests")
class UserTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        User u = new User();
        assertThat(u).isNotNull();
        assertThat(u.getUsername()).isNull();
        assertThat(u.getFirstName()).isNull();
        assertThat(u.getLastName()).isNull();
        assertThat(u.getEmail()).isNull();
        assertThat(u.getRank()).isNull();
        assertThat(u.getTitle()).isNull();
        assertThat(u.getTax()).isNull();
        assertThat(u.getCmdCode()).isNull();
        assertThat(u.getMobile()).isNull();
        assertThat(u.getRoles()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void username_getterAndSetter() {
            User u = new User();
            u.setUsername("jdoe");
            assertThat(u.getUsername()).isEqualTo("jdoe");
        }

        @Test void firstName_getterAndSetter() {
            User u = new User();
            u.setFirstName("John");
            assertThat(u.getFirstName()).isEqualTo("John");
        }

        @Test void lastName_getterAndSetter() {
            User u = new User();
            u.setLastName("Doe");
            assertThat(u.getLastName()).isEqualTo("Doe");
        }

        @Test void email_getterAndSetter() {
            User u = new User();
            u.setEmail("jdoe@nnnn.org");
            assertThat(u.getEmail()).isEqualTo("jdoe@nnnn.org");
        }

        @Test void rank_getterAndSetter() {
            User u = new User();
            u.setRank("Detective");
            assertThat(u.getRank()).isEqualTo("Detective");
        }

        @Test void title_getterAndSetter() {
            User u = new User();
            u.setTitle("Senior Analyst");
            assertThat(u.getTitle()).isEqualTo("Senior Analyst");
        }

        @Test void tax_getterAndSetter() {
            User u = new User();
            u.setTax("123456");
            assertThat(u.getTax()).isEqualTo("123456");
        }

        @Test void cmdCode_getterAndSetter() {
            User u = new User();
            u.setCmdCode("061");
            assertThat(u.getCmdCode()).isEqualTo("061");
        }

        @Test void mobile_getterAndSetter() {
            User u = new User();
            u.setMobile("555-1234");
            assertThat(u.getMobile()).isEqualTo("555-1234");
        }

        @Test void roles_getterAndSetter() {
            User u = new User();
            u.setRoles(List.of("ROLE_ANALYST", "ROLE_SUPERVISOR"));
            assertThat(u.getRoles()).containsExactly("ROLE_ANALYST", "ROLE_SUPERVISOR");
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_username() {
            User u = new User();
            assertThat(u.username("jdoe")).isSameAs(u);
            assertThat(u.getUsername()).isEqualTo("jdoe");
        }

        @Test void fluent_firstName() {
            User u = new User();
            assertThat(u.firstName("John")).isSameAs(u);
            assertThat(u.getFirstName()).isEqualTo("John");
        }

        @Test void fluent_lastName() {
            User u = new User();
            assertThat(u.lastName("Doe")).isSameAs(u);
            assertThat(u.getLastName()).isEqualTo("Doe");
        }

        @Test void fluent_email() {
            User u = new User();
            assertThat(u.email("jdoe@nnnn.org")).isSameAs(u);
            assertThat(u.getEmail()).isEqualTo("jdoe@nnnn.org");
        }

        @Test void fluent_rank() {
            User u = new User();
            assertThat(u.rank("Detective")).isSameAs(u);
            assertThat(u.getRank()).isEqualTo("Detective");
        }

        @Test void fluent_tax() {
            User u = new User();
            assertThat(u.tax("123456")).isSameAs(u);
            assertThat(u.getTax()).isEqualTo("123456");
        }

        @Test void fluent_roles() {
            User u = new User();
            assertThat(u.roles(List.of("ROLE_ANALYST"))).isSameAs(u);
            assertThat(u.getRoles()).containsExactly("ROLE_ANALYST");
        }

        @Test void fluent_chaining() {
            User u = new User().username("jdoe").firstName("John").lastName("Doe").email("jdoe@nnnn.org");
            assertThat(u.getUsername()).isEqualTo("jdoe");
            assertThat(u.getFirstName()).isEqualTo("John");
            assertThat(u.getLastName()).isEqualTo("Doe");
        }
    }

    @Nested
    @DisplayName("addRolesItem")
    class AddRolesItemTests {

        @Test void addRolesItem_initializesAndAdds() {
            User u = new User();
            assertThat(u.getRoles()).isNull();
            u.addRolesItem("ROLE_ANALYST");
            assertThat(u.getRoles()).containsExactly("ROLE_ANALYST");
        }

        @Test void addRolesItem_appendsToExisting() {
            User u = new User();
            u.addRolesItem("ROLE_ANALYST");
            u.addRolesItem("ROLE_SUPERVISOR");
            assertThat(u.getRoles()).hasSize(2);
        }

        @Test void addRolesItem_returnsSameInstance() {
            User u = new User();
            assertThat(u.addRolesItem("ROLE_ANALYST")).isSameAs(u);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            User a = new User().username("jdoe").firstName("John").lastName("Doe");
            User b = new User().username("jdoe").firstName("John").lastName("Doe");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentUsername() {
            assertThat(new User().username("jdoe")).isNotEqualTo(new User().username("asmith"));
        }

        @Test void equals_sameInstance() {
            User a = new User().username("jdoe");
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new User()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new User()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new User().username("jdoe").firstName("John").hashCode())
                    .isEqualTo(new User().username("jdoe").firstName("John").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new User().toString()).contains("User");
        }

        @Test void toString_containsValues() {
            User u = new User().username("jdoe").firstName("John").lastName("Doe");
            assertThat(u.toString()).contains("jdoe").contains("John").contains("Doe");
        }

        @Test void toString_nullFields() {
            assertThat(new User().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            User u = new User().username("line1\nline2");
            assertThat(u.toString()).contains("line1").contains("line2");
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ADA Model Tests")
class ADATest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        ADA a = new ADA();
        assertThat(a).isNotNull();
        assertThat(a.getId()).isNull();
        assertThat(a.getJobTitleDesc()).isNull();
        assertThat(a.getFrstNm()).isNull();
        assertThat(a.getLastNm()).isNull();
        assertThat(a.getEmailAddrDesc()).isNull();
        assertThat(a.getBoroughNm()).isNull();
        assertThat(a.getBusPhoneNum()).isNull();
        assertThat(a.getCellPhoneNum()).isNull();
        assertThat(a.getFaxNum()).isNull();
        assertThat(a.getNoteDesc()).isNull();
        assertThat(a.getArchiveFlg()).isNull();
        assertThat(a.getInactiveFlg()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void id_getterAndSetter() {
            ADA a = new ADA();
            a.setId(1);
            assertThat(a.getId()).isEqualTo(1);
        }

        @Test void jobTitleDesc_getterAndSetter() {
            ADA a = new ADA();
            a.setJobTitleDesc("ADA");
            assertThat(a.getJobTitleDesc()).isEqualTo("ADA");
        }

        @Test void frstNm_getterAndSetter() {
            ADA a = new ADA();
            a.setFrstNm("John");
            assertThat(a.getFrstNm()).isEqualTo("John");
        }

        @Test void lastNm_getterAndSetter() {
            ADA a = new ADA();
            a.setLastNm("Smith");
            assertThat(a.getLastNm()).isEqualTo("Smith");
        }

        @Test void emailAddrDesc_getterAndSetter() {
            ADA a = new ADA();
            a.setEmailAddrDesc("jsmith@da.gov");
            assertThat(a.getEmailAddrDesc()).isEqualTo("jsmith@da.gov");
        }

        @Test void boroughNm_getterAndSetter() {
            ADA a = new ADA();
            a.setBoroughNm("Manhattan");
            assertThat(a.getBoroughNm()).isEqualTo("Manhattan");
        }

        @Test void busPhoneNum_getterAndSetter() {
            ADA a = new ADA();
            a.setBusPhoneNum("212-555-1234");
            assertThat(a.getBusPhoneNum()).isEqualTo("212-555-1234");
        }

        @Test void cellPhoneNum_getterAndSetter() {
            ADA a = new ADA();
            a.setCellPhoneNum("917-555-5678");
            assertThat(a.getCellPhoneNum()).isEqualTo("917-555-5678");
        }

        @Test void faxNum_getterAndSetter() {
            ADA a = new ADA();
            a.setFaxNum("212-555-9999");
            assertThat(a.getFaxNum()).isEqualTo("212-555-9999");
        }

        @Test void noteDesc_getterAndSetter() {
            ADA a = new ADA();
            a.setNoteDesc("Handles felony cases");
            assertThat(a.getNoteDesc()).isEqualTo("Handles felony cases");
        }

        @Test void archiveFlg_getterAndSetter() {
            ADA a = new ADA();
            a.setArchiveFlg(0);
            assertThat(a.getArchiveFlg()).isEqualTo(0);
        }

        @Test void inactiveFlg_getterAndSetter() {
            ADA a = new ADA();
            a.setInactiveFlg(1);
            assertThat(a.getInactiveFlg()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_id() {
            ADA a = new ADA();
            assertThat(a.id(1)).isSameAs(a);
            assertThat(a.getId()).isEqualTo(1);
        }

        @Test void fluent_jobTitleDesc() {
            ADA a = new ADA();
            assertThat(a.jobTitleDesc("ADA")).isSameAs(a);
            assertThat(a.getJobTitleDesc()).isEqualTo("ADA");
        }

        @Test void fluent_frstNm() {
            ADA a = new ADA();
            assertThat(a.frstNm("John")).isSameAs(a);
            assertThat(a.getFrstNm()).isEqualTo("John");
        }

        @Test void fluent_lastNm() {
            ADA a = new ADA();
            assertThat(a.lastNm("Smith")).isSameAs(a);
            assertThat(a.getLastNm()).isEqualTo("Smith");
        }

        @Test void fluent_emailAddrDesc() {
            ADA a = new ADA();
            assertThat(a.emailAddrDesc("jsmith@da.gov")).isSameAs(a);
            assertThat(a.getEmailAddrDesc()).isEqualTo("jsmith@da.gov");
        }

        @Test void fluent_boroughNm() {
            ADA a = new ADA();
            assertThat(a.boroughNm("Brooklyn")).isSameAs(a);
            assertThat(a.getBoroughNm()).isEqualTo("Brooklyn");
        }

        @Test void fluent_archiveFlg() {
            ADA a = new ADA();
            assertThat(a.archiveFlg(0)).isSameAs(a);
            assertThat(a.getArchiveFlg()).isEqualTo(0);
        }

        @Test void fluent_inactiveFlg() {
            ADA a = new ADA();
            assertThat(a.inactiveFlg(0)).isSameAs(a);
            assertThat(a.getInactiveFlg()).isEqualTo(0);
        }

        @Test void fluent_chaining() {
            ADA a = new ADA().id(1).jobTitleDesc("ADA").frstNm("John").lastNm("Smith")
                    .boroughNm("Manhattan").archiveFlg(0).inactiveFlg(0);
            assertThat(a.getId()).isEqualTo(1);
            assertThat(a.getJobTitleDesc()).isEqualTo("ADA");
            assertThat(a.getFrstNm()).isEqualTo("John");
            assertThat(a.getLastNm()).isEqualTo("Smith");
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            ADA a = new ADA().id(1).frstNm("John").lastNm("Smith");
            ADA b = new ADA().id(1).frstNm("John").lastNm("Smith");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentId() {
            assertThat(new ADA().id(1)).isNotEqualTo(new ADA().id(2));
        }

        @Test void equals_differentLastNm() {
            assertThat(new ADA().lastNm("Smith")).isNotEqualTo(new ADA().lastNm("Jones"));
        }

        @Test void equals_sameInstance() {
            ADA a = new ADA().id(1);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new ADA()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new ADA()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new ADA().id(1).frstNm("John").lastNm("Smith").hashCode())
                    .isEqualTo(new ADA().id(1).frstNm("John").lastNm("Smith").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new ADA().toString()).contains("ADA");
        }

        @Test void toString_containsValues() {
            ADA a = new ADA().id(1).frstNm("John").lastNm("Smith").boroughNm("Manhattan");
            assertThat(a.toString()).contains("1").contains("John").contains("Smith").contains("Manhattan");
        }

        @Test void toString_nullFields() {
            assertThat(new ADA().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            ADA a = new ADA().noteDesc("line1\nline2");
            assertThat(a.toString()).contains("line1").contains("line2");
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseFilter Model Tests")
class CaseFilterTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseFilter f = new CaseFilter();
        assertThat(f).isNotNull();
        assertThat(f.getArrId()).isNull();
        assertThat(f.getRequestDtFrom()).isNull();
        assertThat(f.getRequestDtTo()).isNull();
        assertThat(f.getDueDtFrom()).isNull();
        assertThat(f.getDueDtTo()).isNull();
        assertThat(f.getAssignedNm()).isNull();
        assertThat(f.getCategoryIds()).isNull();
        assertThat(f.getdddOfficeIds()).isNull();
        assertThat(f.getStatusIds()).isNull();
        assertThat(f.getTagIds()).isNull();
        assertThat(f.getProactiveFlg()).isNull();
        assertThat(f.getPageSize()).isNull();
        assertThat(f.getPageNumber()).isNull();
        assertThat(f.getSortBy()).isNull();
        assertThat(f.getSortOrder()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void arrId_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setArrId("ARR001");
            assertThat(f.getArrId()).isEqualTo("ARR001");
        }

        @Test void requestDtFrom_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setRequestDtFrom("2024-01-01");
            assertThat(f.getRequestDtFrom()).isEqualTo("2024-01-01");
        }

        @Test void requestDtTo_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setRequestDtTo("2024-12-31");
            assertThat(f.getRequestDtTo()).isEqualTo("2024-12-31");
        }

        @Test void dueDtFrom_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setDueDtFrom("2024-01-01");
            assertThat(f.getDueDtFrom()).isEqualTo("2024-01-01");
        }

        @Test void dueDtTo_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setDueDtTo("2024-12-31");
            assertThat(f.getDueDtTo()).isEqualTo("2024-12-31");
        }

        @Test void assignedNm_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setAssignedNm("jdoe");
            assertThat(f.getAssignedNm()).isEqualTo("jdoe");
        }

        @Test void categoryIds_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setCategoryIds(List.of(1, 2));
            assertThat(f.getCategoryIds()).containsExactly(1, 2);
        }

        @Test void dddOfficeIds_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setdddOfficeIds(List.of(1, 2, 3));
            assertThat(f.getdddOfficeIds()).hasSize(3);
        }

        @Test void statusIds_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setStatusIds(List.of(1, 2));
            assertThat(f.getStatusIds()).containsExactly(1, 2);
        }

        @Test void tagIds_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setTagIds(List.of(5, 6));
            assertThat(f.getTagIds()).containsExactly(5, 6);
        }

        @Test void proactiveFlg_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setProactiveFlg(1);
            assertThat(f.getProactiveFlg()).isEqualTo(1);
        }

        @Test void pageSize_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setPageSize(20);
            assertThat(f.getPageSize()).isEqualTo(20);
        }

        @Test void pageNumber_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setPageNumber(0);
            assertThat(f.getPageNumber()).isEqualTo(0);
        }

        @Test void sortBy_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setSortBy(List.of("dueDt", "requestDt"));
            assertThat(f.getSortBy()).containsExactly("dueDt", "requestDt");
        }

        @Test void sortOrder_getterAndSetter() {
            CaseFilter f = new CaseFilter();
            f.setSortOrder(List.of(1, 0));
            assertThat(f.getSortOrder()).containsExactly(1, 0);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_arrId() {
            CaseFilter f = new CaseFilter();
            assertThat(f.arrId("ARR001")).isSameAs(f);
            assertThat(f.getArrId()).isEqualTo("ARR001");
        }

        @Test void fluent_assignedNm() {
            CaseFilter f = new CaseFilter();
            assertThat(f.assignedNm("jdoe")).isSameAs(f);
            assertThat(f.getAssignedNm()).isEqualTo("jdoe");
        }

        @Test void fluent_proactiveFlg() {
            CaseFilter f = new CaseFilter();
            assertThat(f.proactiveFlg(1)).isSameAs(f);
            assertThat(f.getProactiveFlg()).isEqualTo(1);
        }

        @Test void fluent_pageSize() {
            CaseFilter f = new CaseFilter();
            assertThat(f.pageSize(20)).isSameAs(f);
            assertThat(f.getPageSize()).isEqualTo(20);
        }

        @Test void fluent_pageNumber() {
            CaseFilter f = new CaseFilter();
            assertThat(f.pageNumber(0)).isSameAs(f);
            assertThat(f.getPageNumber()).isEqualTo(0);
        }

        @Test void fluent_chaining() {
            CaseFilter f = new CaseFilter().arrId("ARR001").assignedNm("jdoe").pageSize(20).pageNumber(0);
            assertThat(f.getArrId()).isEqualTo("ARR001");
            assertThat(f.getAssignedNm()).isEqualTo("jdoe");
            assertThat(f.getPageSize()).isEqualTo(20);
        }
    }

    @Nested
    @DisplayName("addXxxItem Methods")
    class AddItemTests {

        @Test void addCategoryIdsItem_initializesAndAdds() {
            CaseFilter f = new CaseFilter();
            assertThat(f.getCategoryIds()).isNull();
            f.addCategoryIdsItem(1);
            assertThat(f.getCategoryIds()).containsExactly(1);
        }

        @Test void adddddOfficeIdsItem_initializesAndAdds() {
            CaseFilter f = new CaseFilter();
            f.adddddOfficeIdsItem(1);
            assertThat(f.getdddOfficeIds()).containsExactly(1);
        }

        @Test void addStatusIdsItem_initializesAndAdds() {
            CaseFilter f = new CaseFilter();
            f.addStatusIdsItem(1);
            assertThat(f.getStatusIds()).containsExactly(1);
        }

        @Test void addTagIdsItem_initializesAndAdds() {
            CaseFilter f = new CaseFilter();
            f.addTagIdsItem(5);
            assertThat(f.getTagIds()).containsExactly(5);
        }

        @Test void addSortByItem_initializesAndAdds() {
            CaseFilter f = new CaseFilter();
            f.addSortByItem("dueDt");
            assertThat(f.getSortBy()).containsExactly("dueDt");
        }

        @Test void addSortOrderItem_initializesAndAdds() {
            CaseFilter f = new CaseFilter();
            f.addSortOrderItem(1);
            assertThat(f.getSortOrder()).containsExactly(1);
        }

        @Test void addCategoryIdsItem_returnsSameInstance() {
            CaseFilter f = new CaseFilter();
            assertThat(f.addCategoryIdsItem(1)).isSameAs(f);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseFilter a = new CaseFilter().arrId("ARR001").assignedNm("jdoe");
            CaseFilter b = new CaseFilter().arrId("ARR001").assignedNm("jdoe");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentArrId() {
            assertThat(new CaseFilter().arrId("ARR001"))
                    .isNotEqualTo(new CaseFilter().arrId("ARR002"));
        }

        @Test void equals_sameInstance() {
            CaseFilter a = new CaseFilter().arrId("ARR001");
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseFilter()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseFilter()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseFilter().arrId("ARR001").assignedNm("jdoe").hashCode())
                    .isEqualTo(new CaseFilter().arrId("ARR001").assignedNm("jdoe").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseFilter().toString()).contains("CaseFilter");
        }

        @Test void toString_containsValues() {
            CaseFilter f = new CaseFilter().arrId("ARR001").assignedNm("jdoe");
            assertThat(f.toString()).contains("ARR001").contains("jdoe");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseFilter().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CaseFilter f = new CaseFilter().arrId("line1\nline2");
            assertThat(f.toString()).contains("line1").contains("line2");
        }
    }
}


