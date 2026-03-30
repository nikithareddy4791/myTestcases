package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ModelApiResponse Model Tests")
class ModelApiResponseTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        ModelApiResponse r = new ModelApiResponse();
        assertThat(r).isNotNull();
        assertThat(r.getCode()).isNull();
        assertThat(r.getType()).isNull();
        assertThat(r.getMessage()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void code_getterAndSetter() {
            ModelApiResponse r = new ModelApiResponse();
            r.setCode(200);
            assertThat(r.getCode()).isEqualTo(200);
        }

        @Test void type_getterAndSetter() {
            ModelApiResponse r = new ModelApiResponse();
            r.setType("success");
            assertThat(r.getType()).isEqualTo("success");
        }

        @Test void message_getterAndSetter() {
            ModelApiResponse r = new ModelApiResponse();
            r.setMessage("Operation successful");
            assertThat(r.getMessage()).isEqualTo("Operation successful");
        }

        @Test void allFields_setIndependently() {
            ModelApiResponse r = new ModelApiResponse();
            r.setCode(404);
            r.setType("error");
            r.setMessage("Not found");
            assertThat(r.getCode()).isEqualTo(404);
            assertThat(r.getType()).isEqualTo("error");
            assertThat(r.getMessage()).isEqualTo("Not found");
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_code() {
            ModelApiResponse r = new ModelApiResponse();
            assertThat(r.code(200)).isSameAs(r);
            assertThat(r.getCode()).isEqualTo(200);
        }

        @Test void fluent_type() {
            ModelApiResponse r = new ModelApiResponse();
            assertThat(r.type("success")).isSameAs(r);
            assertThat(r.getType()).isEqualTo("success");
        }

        @Test void fluent_message() {
            ModelApiResponse r = new ModelApiResponse();
            assertThat(r.message("OK")).isSameAs(r);
            assertThat(r.getMessage()).isEqualTo("OK");
        }

        @Test void fluent_chaining() {
            ModelApiResponse r = new ModelApiResponse().code(200).type("success").message("OK");
            assertThat(r.getCode()).isEqualTo(200);
            assertThat(r.getType()).isEqualTo("success");
            assertThat(r.getMessage()).isEqualTo("OK");
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            ModelApiResponse a = new ModelApiResponse().code(200).type("success").message("OK");
            ModelApiResponse b = new ModelApiResponse().code(200).type("success").message("OK");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentCode() {
            assertThat(new ModelApiResponse().code(200)).isNotEqualTo(new ModelApiResponse().code(404));
        }

        @Test void equals_differentType() {
            assertThat(new ModelApiResponse().type("success")).isNotEqualTo(new ModelApiResponse().type("error"));
        }

        @Test void equals_differentMessage() {
            assertThat(new ModelApiResponse().message("OK")).isNotEqualTo(new ModelApiResponse().message("Fail"));
        }

        @Test void equals_sameInstance() {
            ModelApiResponse a = new ModelApiResponse().code(200);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new ModelApiResponse()).isNotEqualTo(null);
        }

        @Test void equals_differentType_returns_false() {
            assertThat(new ModelApiResponse()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new ModelApiResponse().code(200).type("success").message("OK").hashCode())
                    .isEqualTo(new ModelApiResponse().code(200).type("success").message("OK").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new ModelApiResponse().toString()).contains("ModelApiResponse");
        }

        @Test void toString_containsValues() {
            ModelApiResponse r = new ModelApiResponse().code(200).type("success").message("OK");
            assertThat(r.toString()).contains("200").contains("success").contains("OK");
        }

        @Test void toString_nullFields() {
            assertThat(new ModelApiResponse().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            ModelApiResponse r = new ModelApiResponse().message("line1\nline2");
            assertThat(r.toString()).contains("line1").contains("line2");
        }
    }
}





@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@




package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseIdNoteBody Model Tests")
class CaseIdNoteBodyTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseIdNoteBody b = new CaseIdNoteBody();
        assertThat(b).isNotNull();
        assertThat(b.getNoteDesc()).isNull();
        assertThat(b.getFileNm()).isNull();
        assertThat(b.getFileContent()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void noteDesc_getterAndSetter() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            b.setNoteDesc("Test note");
            assertThat(b.getNoteDesc()).isEqualTo("Test note");
        }

        @Test void fileNm_getterAndSetter() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            b.setFileNm("evidence.pdf");
            assertThat(b.getFileNm()).isEqualTo("evidence.pdf");
        }

        @Test void fileContent_getterAndSetter() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            Resource resource = new ByteArrayResource("content".getBytes());
            b.setFileContent(resource);
            assertThat(b.getFileContent()).isEqualTo(resource);
        }

        @Test void allFields_setIndependently() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            Resource resource = new ByteArrayResource("data".getBytes());
            b.setNoteDesc("Note");
            b.setFileNm("doc.pdf");
            b.setFileContent(resource);
            assertThat(b.getNoteDesc()).isEqualTo("Note");
            assertThat(b.getFileNm()).isEqualTo("doc.pdf");
            assertThat(b.getFileContent()).isEqualTo(resource);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_noteDesc() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            assertThat(b.noteDesc("Note")).isSameAs(b);
            assertThat(b.getNoteDesc()).isEqualTo("Note");
        }

        @Test void fluent_fileNm() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            assertThat(b.fileNm("doc.pdf")).isSameAs(b);
            assertThat(b.getFileNm()).isEqualTo("doc.pdf");
        }

        @Test void fluent_fileContent() {
            CaseIdNoteBody b = new CaseIdNoteBody();
            Resource resource = new ByteArrayResource("data".getBytes());
            assertThat(b.fileContent(resource)).isSameAs(b);
            assertThat(b.getFileContent()).isEqualTo(resource);
        }

        @Test void fluent_chaining() {
            CaseIdNoteBody b = new CaseIdNoteBody().noteDesc("Note").fileNm("doc.pdf");
            assertThat(b.getNoteDesc()).isEqualTo("Note");
            assertThat(b.getFileNm()).isEqualTo("doc.pdf");
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseIdNoteBody a = new CaseIdNoteBody().noteDesc("Note").fileNm("doc.pdf");
            CaseIdNoteBody b = new CaseIdNoteBody().noteDesc("Note").fileNm("doc.pdf");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentNoteDesc() {
            assertThat(new CaseIdNoteBody().noteDesc("Note1"))
                    .isNotEqualTo(new CaseIdNoteBody().noteDesc("Note2"));
        }

        @Test void equals_differentFileNm() {
            assertThat(new CaseIdNoteBody().fileNm("a.pdf"))
                    .isNotEqualTo(new CaseIdNoteBody().fileNm("b.pdf"));
        }

        @Test void equals_sameInstance() {
            CaseIdNoteBody a = new CaseIdNoteBody().noteDesc("Note");
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseIdNoteBody()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseIdNoteBody()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseIdNoteBody().noteDesc("Note").fileNm("doc.pdf").hashCode())
                    .isEqualTo(new CaseIdNoteBody().noteDesc("Note").fileNm("doc.pdf").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseIdNoteBody().toString()).contains("CaseIdNoteBody");
        }

        @Test void toString_containsValues() {
            CaseIdNoteBody b = new CaseIdNoteBody().noteDesc("Note").fileNm("doc.pdf");
            assertThat(b.toString()).contains("Note").contains("doc.pdf");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseIdNoteBody().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CaseIdNoteBody b = new CaseIdNoteBody().noteDesc("line1\nline2");
            assertThat(b.toString()).contains("line1").contains("line2");
        }
    }
}

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseTag Model Tests")
class CaseTagModelTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseTag t = new CaseTag();
        assertThat(t).isNotNull();
        assertThat(t.getId()).isNull();
        assertThat(t.getCaseId()).isNull();
        assertThat(t.getTagId()).isNull();
        assertThat(t.getTagDesc()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void id_getterAndSetter() {
            CaseTag t = new CaseTag();
            t.setId(1);
            assertThat(t.getId()).isEqualTo(1);
        }

        @Test void caseId_getterAndSetter() {
            CaseTag t = new CaseTag();
            t.setCaseId(100);
            assertThat(t.getCaseId()).isEqualTo(100);
        }

        @Test void tagId_getterAndSetter() {
            CaseTag t = new CaseTag();
            t.setTagId(5);
            assertThat(t.getTagId()).isEqualTo(5);
        }

        @Test void tagDesc_getterAndSetter() {
            CaseTag t = new CaseTag();
            t.setTagDesc("Gun");
            assertThat(t.getTagDesc()).isEqualTo("Gun");
        }

        @Test void allFields_setIndependently() {
            CaseTag t = new CaseTag();
            t.setId(1);
            t.setCaseId(100);
            t.setTagId(5);
            t.setTagDesc("Gang");
            assertThat(t.getId()).isEqualTo(1);
            assertThat(t.getCaseId()).isEqualTo(100);
            assertThat(t.getTagId()).isEqualTo(5);
            assertThat(t.getTagDesc()).isEqualTo("Gang");
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_id() {
            CaseTag t = new CaseTag();
            assertThat(t.id(1)).isSameAs(t);
            assertThat(t.getId()).isEqualTo(1);
        }

        @Test void fluent_caseId() {
            CaseTag t = new CaseTag();
            assertThat(t.caseId(100)).isSameAs(t);
            assertThat(t.getCaseId()).isEqualTo(100);
        }

        @Test void fluent_tagId() {
            CaseTag t = new CaseTag();
            assertThat(t.tagId(5)).isSameAs(t);
            assertThat(t.getTagId()).isEqualTo(5);
        }

        @Test void fluent_tagDesc() {
            CaseTag t = new CaseTag();
            assertThat(t.tagDesc("Gun")).isSameAs(t);
            assertThat(t.getTagDesc()).isEqualTo("Gun");
        }

        @Test void fluent_chaining() {
            CaseTag t = new CaseTag().id(1).caseId(100).tagId(5).tagDesc("Gun");
            assertThat(t.getId()).isEqualTo(1);
            assertThat(t.getCaseId()).isEqualTo(100);
            assertThat(t.getTagId()).isEqualTo(5);
            assertThat(t.getTagDesc()).isEqualTo("Gun");
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseTag a = new CaseTag().id(1).caseId(100).tagId(5).tagDesc("Gun");
            CaseTag b = new CaseTag().id(1).caseId(100).tagId(5).tagDesc("Gun");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentId() {
            assertThat(new CaseTag().id(1)).isNotEqualTo(new CaseTag().id(2));
        }

        @Test void equals_differentCaseId() {
            assertThat(new CaseTag().caseId(100)).isNotEqualTo(new CaseTag().caseId(200));
        }

        @Test void equals_differentTagId() {
            assertThat(new CaseTag().tagId(1)).isNotEqualTo(new CaseTag().tagId(2));
        }

        @Test void equals_differentTagDesc() {
            assertThat(new CaseTag().tagDesc("Gun")).isNotEqualTo(new CaseTag().tagDesc("Gang"));
        }

        @Test void equals_sameInstance() {
            CaseTag a = new CaseTag().id(1);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseTag()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseTag()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseTag().id(1).caseId(100).tagId(5).tagDesc("Gun").hashCode())
                    .isEqualTo(new CaseTag().id(1).caseId(100).tagId(5).tagDesc("Gun").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseTag().toString()).contains("CaseTag");
        }

        @Test void toString_containsValues() {
            CaseTag t = new CaseTag().id(1).caseId(100).tagId(5).tagDesc("Gun");
            assertThat(t.toString()).contains("1").contains("100").contains("5").contains("Gun");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseTag().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CaseTag t = new CaseTag().tagDesc("line1\nline2");
            assertThat(t.toString()).contains("line1").contains("line2");
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseNote Model Tests")
class CaseNoteModelTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseNote n = new CaseNote();
        assertThat(n).isNotNull();
        assertThat(n.getId()).isNull();
        assertThat(n.getCaseId()).isNull();
        assertThat(n.getUserNm()).isNull();
        assertThat(n.getNoteDesc()).isNull();
        assertThat(n.getUpload()).isNull();
        assertThat(n.getRowInsertTs()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void id_getterAndSetter() {
            CaseNote n = new CaseNote();
            n.setId(1);
            assertThat(n.getId()).isEqualTo(1);
        }

        @Test void caseId_getterAndSetter() {
            CaseNote n = new CaseNote();
            n.setCaseId(100);
            assertThat(n.getCaseId()).isEqualTo(100);
        }

        @Test void userNm_getterAndSetter() {
            CaseNote n = new CaseNote();
            n.setUserNm("jdoe");
            assertThat(n.getUserNm()).isEqualTo("jdoe");
        }

        @Test void noteDesc_getterAndSetter() {
            CaseNote n = new CaseNote();
            n.setNoteDesc("Test note");
            assertThat(n.getNoteDesc()).isEqualTo("Test note");
        }

        @Test void upload_getterAndSetter() {
            CaseNote n = new CaseNote();
            CaseUpload upload = new CaseUpload().id(1).fileNm("doc.pdf");
            n.setUpload(upload);
            assertThat(n.getUpload().getId()).isEqualTo(1);
            assertThat(n.getUpload().getFileNm()).isEqualTo("doc.pdf");
        }

        @Test void rowInsertTs_getterAndSetter() {
            CaseNote n = new CaseNote();
            OffsetDateTime now = OffsetDateTime.now();
            n.setRowInsertTs(now);
            assertThat(n.getRowInsertTs()).isEqualTo(now);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_id() {
            CaseNote n = new CaseNote();
            assertThat(n.id(1)).isSameAs(n);
            assertThat(n.getId()).isEqualTo(1);
        }

        @Test void fluent_caseId() {
            CaseNote n = new CaseNote();
            assertThat(n.caseId(100)).isSameAs(n);
            assertThat(n.getCaseId()).isEqualTo(100);
        }

        @Test void fluent_userNm() {
            CaseNote n = new CaseNote();
            assertThat(n.userNm("jdoe")).isSameAs(n);
            assertThat(n.getUserNm()).isEqualTo("jdoe");
        }

        @Test void fluent_noteDesc() {
            CaseNote n = new CaseNote();
            assertThat(n.noteDesc("Note")).isSameAs(n);
            assertThat(n.getNoteDesc()).isEqualTo("Note");
        }

        @Test void fluent_upload() {
            CaseNote n = new CaseNote();
            CaseUpload upload = new CaseUpload();
            assertThat(n.upload(upload)).isSameAs(n);
            assertThat(n.getUpload()).isEqualTo(upload);
        }

        @Test void fluent_rowInsertTs() {
            CaseNote n = new CaseNote();
            OffsetDateTime now = OffsetDateTime.now();
            assertThat(n.rowInsertTs(now)).isSameAs(n);
            assertThat(n.getRowInsertTs()).isEqualTo(now);
        }

        @Test void fluent_chaining() {
            CaseNote n = new CaseNote().id(1).caseId(100).userNm("jdoe").noteDesc("Note");
            assertThat(n.getId()).isEqualTo(1);
            assertThat(n.getCaseId()).isEqualTo(100);
            assertThat(n.getUserNm()).isEqualTo("jdoe");
            assertThat(n.getNoteDesc()).isEqualTo("Note");
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseNote a = new CaseNote().id(1).caseId(100).userNm("jdoe").noteDesc("Note");
            CaseNote b = new CaseNote().id(1).caseId(100).userNm("jdoe").noteDesc("Note");
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentId() {
            assertThat(new CaseNote().id(1)).isNotEqualTo(new CaseNote().id(2));
        }

        @Test void equals_differentUserNm() {
            assertThat(new CaseNote().userNm("jdoe")).isNotEqualTo(new CaseNote().userNm("asmith"));
        }

        @Test void equals_differentNoteDesc() {
            assertThat(new CaseNote().noteDesc("Note1")).isNotEqualTo(new CaseNote().noteDesc("Note2"));
        }

        @Test void equals_sameInstance() {
            CaseNote a = new CaseNote().id(1);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseNote()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseNote()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseNote().id(1).caseId(100).userNm("jdoe").noteDesc("Note").hashCode())
                    .isEqualTo(new CaseNote().id(1).caseId(100).userNm("jdoe").noteDesc("Note").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseNote().toString()).contains("CaseNote");
        }

        @Test void toString_containsValues() {
            CaseNote n = new CaseNote().id(1).caseId(100).userNm("jdoe").noteDesc("Note");
            assertThat(n.toString()).contains("1").contains("100").contains("jdoe").contains("Note");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseNote().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CaseNote n = new CaseNote().noteDesc("line1\nline2");
            assertThat(n.toString()).contains("line1").contains("line2");
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CaseUpload Model Tests")
class CaseUploadModelTest {

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_nullFields() {
        CaseUpload u = new CaseUpload();
        assertThat(u).isNotNull();
        assertThat(u.getId()).isNull();
        assertThat(u.getCaseId()).isNull();
        assertThat(u.getFileNm()).isNull();
        assertThat(u.getUserNm()).isNull();
        assertThat(u.getDeletedFlg()).isNull();
        assertThat(u.getRowInsertTs()).isNull();
        assertThat(u.getRowUpdtTs()).isNull();
    }

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test void id_getterAndSetter() {
            CaseUpload u = new CaseUpload();
            u.setId(1);
            assertThat(u.getId()).isEqualTo(1);
        }

        @Test void caseId_getterAndSetter() {
            CaseUpload u = new CaseUpload();
            u.setCaseId(100);
            assertThat(u.getCaseId()).isEqualTo(100);
        }

        @Test void fileNm_getterAndSetter() {
            CaseUpload u = new CaseUpload();
            u.setFileNm("evidence.pdf");
            assertThat(u.getFileNm()).isEqualTo("evidence.pdf");
        }

        @Test void userNm_getterAndSetter() {
            CaseUpload u = new CaseUpload();
            u.setUserNm("jdoe");
            assertThat(u.getUserNm()).isEqualTo("jdoe");
        }

        @Test void deletedFlg_active() {
            CaseUpload u = new CaseUpload();
            u.setDeletedFlg(0);
            assertThat(u.getDeletedFlg()).isEqualTo(0);
        }

        @Test void deletedFlg_deleted() {
            CaseUpload u = new CaseUpload();
            u.setDeletedFlg(1);
            assertThat(u.getDeletedFlg()).isEqualTo(1);
        }

        @Test void rowInsertTs_getterAndSetter() {
            CaseUpload u = new CaseUpload();
            OffsetDateTime now = OffsetDateTime.now();
            u.setRowInsertTs(now);
            assertThat(u.getRowInsertTs()).isEqualTo(now);
        }

        @Test void rowUpdtTs_getterAndSetter() {
            CaseUpload u = new CaseUpload();
            OffsetDateTime now = OffsetDateTime.now();
            u.setRowUpdtTs(now);
            assertThat(u.getRowUpdtTs()).isEqualTo(now);
        }

        @Test void allFields_setIndependently() {
            CaseUpload u = new CaseUpload();
            u.setId(1);
            u.setCaseId(100);
            u.setFileNm("doc.pdf");
            u.setUserNm("jdoe");
            u.setDeletedFlg(0);
            assertThat(u.getId()).isEqualTo(1);
            assertThat(u.getCaseId()).isEqualTo(100);
            assertThat(u.getFileNm()).isEqualTo("doc.pdf");
            assertThat(u.getUserNm()).isEqualTo("jdoe");
            assertThat(u.getDeletedFlg()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test void fluent_id() {
            CaseUpload u = new CaseUpload();
            assertThat(u.id(1)).isSameAs(u);
            assertThat(u.getId()).isEqualTo(1);
        }

        @Test void fluent_caseId() {
            CaseUpload u = new CaseUpload();
            assertThat(u.caseId(100)).isSameAs(u);
            assertThat(u.getCaseId()).isEqualTo(100);
        }

        @Test void fluent_fileNm() {
            CaseUpload u = new CaseUpload();
            assertThat(u.fileNm("doc.pdf")).isSameAs(u);
            assertThat(u.getFileNm()).isEqualTo("doc.pdf");
        }

        @Test void fluent_userNm() {
            CaseUpload u = new CaseUpload();
            assertThat(u.userNm("jdoe")).isSameAs(u);
            assertThat(u.getUserNm()).isEqualTo("jdoe");
        }

        @Test void fluent_deletedFlg() {
            CaseUpload u = new CaseUpload();
            assertThat(u.deletedFlg(0)).isSameAs(u);
            assertThat(u.getDeletedFlg()).isEqualTo(0);
        }

        @Test void fluent_rowInsertTs() {
            CaseUpload u = new CaseUpload();
            OffsetDateTime now = OffsetDateTime.now();
            assertThat(u.rowInsertTs(now)).isSameAs(u);
            assertThat(u.getRowInsertTs()).isEqualTo(now);
        }

        @Test void fluent_rowUpdtTs() {
            CaseUpload u = new CaseUpload();
            OffsetDateTime now = OffsetDateTime.now();
            assertThat(u.rowUpdtTs(now)).isSameAs(u);
            assertThat(u.getRowUpdtTs()).isEqualTo(now);
        }

        @Test void fluent_chaining() {
            CaseUpload u = new CaseUpload().id(1).caseId(100).fileNm("doc.pdf").userNm("jdoe").deletedFlg(0);
            assertThat(u.getId()).isEqualTo(1);
            assertThat(u.getCaseId()).isEqualTo(100);
            assertThat(u.getFileNm()).isEqualTo("doc.pdf");
            assertThat(u.getUserNm()).isEqualTo("jdoe");
            assertThat(u.getDeletedFlg()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test void equals_sameFields() {
            CaseUpload a = new CaseUpload().id(1).caseId(100).fileNm("doc.pdf").userNm("jdoe").deletedFlg(0);
            CaseUpload b = new CaseUpload().id(1).caseId(100).fileNm("doc.pdf").userNm("jdoe").deletedFlg(0);
            assertThat(a).isEqualTo(b);
        }

        @Test void equals_differentId() {
            assertThat(new CaseUpload().id(1)).isNotEqualTo(new CaseUpload().id(2));
        }

        @Test void equals_differentFileNm() {
            assertThat(new CaseUpload().fileNm("a.pdf")).isNotEqualTo(new CaseUpload().fileNm("b.pdf"));
        }

        @Test void equals_differentDeletedFlg() {
            assertThat(new CaseUpload().deletedFlg(0)).isNotEqualTo(new CaseUpload().deletedFlg(1));
        }

        @Test void equals_sameInstance() {
            CaseUpload a = new CaseUpload().id(1);
            assertThat(a).isEqualTo(a);
        }

        @Test void equals_null() {
            assertThat(new CaseUpload()).isNotEqualTo(null);
        }

        @Test void equals_differentType() {
            assertThat(new CaseUpload()).isNotEqualTo("string");
        }

        @Test void hashCode_equalInstances() {
            assertThat(new CaseUpload().id(1).caseId(100).fileNm("doc.pdf").hashCode())
                    .isEqualTo(new CaseUpload().id(1).caseId(100).fileNm("doc.pdf").hashCode());
        }
    }

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test void toString_containsClassName() {
            assertThat(new CaseUpload().toString()).contains("CaseUpload");
        }

        @Test void toString_containsValues() {
            CaseUpload u = new CaseUpload().id(1).fileNm("doc.pdf").userNm("jdoe");
            assertThat(u.toString()).contains("1").contains("doc.pdf").contains("jdoe");
        }

        @Test void toString_nullFields() {
            assertThat(new CaseUpload().toString()).contains("null");
        }

        @Test void toString_multilineValue() {
            CaseUpload u = new CaseUpload().fileNm("line1\nline2");
            assertThat(u.toString()).contains("line1").contains("line2");
        }
    }
}






