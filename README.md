package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * ModelApiResponse
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class ModelApiResponse   {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("message")
  private String message = null;

  public ModelApiResponse code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   **/
  @Schema(description = "")
  
    public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public ModelApiResponse type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(description = "")
  
    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ModelApiResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(description = "")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelApiResponse _apiResponse = (ModelApiResponse) o;
    return Objects.equals(this.code, _apiResponse.code) &&
        Objects.equals(this.type, _apiResponse.type) &&
        Objects.equals(this.message, _apiResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, type, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelApiResponse {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


=================


package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CaseIdNoteBody
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-16T09:33:26.070-04:00")


public class CaseIdNoteBody   {
  @JsonProperty("noteDesc")
  private String noteDesc = null;

  @JsonProperty("fileNm")
  private String fileNm = null;

  @JsonProperty("fileContent")
  private Resource fileContent = null;

  public CaseIdNoteBody noteDesc(String noteDesc) {
    this.noteDesc = noteDesc;
    return this;
  }

  /**
   * Get noteDesc
   * @return noteDesc
   **/
  @Schema(description = "")
  
    public String getNoteDesc() {
    return noteDesc;
  }

  public void setNoteDesc(String noteDesc) {
    this.noteDesc = noteDesc;
  }

  public CaseIdNoteBody fileNm(String fileNm) {
    this.fileNm = fileNm;
    return this;
  }

  /**
   * Get fileNm
   * @return fileNm
   **/
  @Schema(description = "")
  
    public String getFileNm() {
    return fileNm;
  }

  public void setFileNm(String fileNm) {
    this.fileNm = fileNm;
  }

  public CaseIdNoteBody fileContent(Resource fileContent) {
    this.fileContent = fileContent;
    return this;
  }

  /**
   * Get fileContent
   * @return fileContent
   **/
  @Schema(description = "")
  
    @Valid
    public Resource getFileContent() {
    return fileContent;
  }

  public void setFileContent(Resource fileContent) {
    this.fileContent = fileContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseIdNoteBody caseIdNoteBody = (CaseIdNoteBody) o;
    return Objects.equals(this.noteDesc, caseIdNoteBody.noteDesc) &&
        Objects.equals(this.fileNm, caseIdNoteBody.fileNm) &&
        Objects.equals(this.fileContent, caseIdNoteBody.fileContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(noteDesc, fileNm, fileContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseIdNoteBody {\n");
    
    sb.append("    noteDesc: ").append(toIndentedString(noteDesc)).append("\n");
    sb.append("    fileNm: ").append(toIndentedString(fileNm)).append("\n");
    sb.append("    fileContent: ").append(toIndentedString(fileContent)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

===============================package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CaseTag
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-24T16:05:11.523-05:00")


public class CaseTag   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("caseId")
  private Integer caseId = null;

  @JsonProperty("tagId")
  private Integer tagId = null;

  @JsonProperty("tagDesc")
  private String tagDesc = null;

  public CaseTag id(Integer id) {
    this.id = id;
    return this;
  }

  public CaseTag() {}

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "1", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CaseTag caseId(Integer caseId) {
    this.caseId = caseId;
    return this;
  }

  /**
   * Get caseId
   * @return caseId
   **/
  @Schema(description = "")
  
    public Integer getCaseId() {
    return caseId;
  }

  public void setCaseId(Integer caseId) {
    this.caseId = caseId;
  }

  public CaseTag tagId(Integer tagId) {
    this.tagId = tagId;
    return this;
  }

  /**
   * Get tagId
   * @return tagId
   **/
  @Schema(description = "")
  
    public Integer getTagId() {
    return tagId;
  }

  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }

  public CaseTag tagDesc(String tagDesc) {
    this.tagDesc = tagDesc;
    return this;
  }

  /**
   * Get tagDesc
   * @return tagDesc
   **/
  @Schema(description = "")
  
    public String getTagDesc() {
    return tagDesc;
  }

  public void setTagDesc(String tagDesc) {
    this.tagDesc = tagDesc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseTag caseTag = (CaseTag) o;
    return Objects.equals(this.id, caseTag.id) &&
        Objects.equals(this.caseId, caseTag.caseId) &&
        Objects.equals(this.tagId, caseTag.tagId) &&
        Objects.equals(this.tagDesc, caseTag.tagDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, caseId, tagId, tagDesc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseTag {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    tagId: ").append(toIndentedString(tagId)).append("\n");
    sb.append("    tagDesc: ").append(toIndentedString(tagDesc)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
========================================


package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.nnnn.ddd.model.CaseUpload;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CaseNote
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class CaseNote   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("caseId")
  private Integer caseId = null;

  @JsonProperty("userNm")
  private String userNm = null;

  @JsonProperty("noteDesc")
  private String noteDesc = null;

  @JsonProperty("upload")
  private CaseUpload upload = null;

  @JsonProperty("rowInsertTs")
  private OffsetDateTime rowInsertTs = null;

  public CaseNote id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "1", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CaseNote caseId(Integer caseId) {
    this.caseId = caseId;
    return this;
  }

  /**
   * Get caseId
   * @return caseId
   **/
  @Schema(description = "")
  
    public Integer getCaseId() {
    return caseId;
  }

  public void setCaseId(Integer caseId) {
    this.caseId = caseId;
  }

  public CaseNote userNm(String userNm) {
    this.userNm = userNm;
    return this;
  }

  /**
   * Get userNm
   * @return userNm
   **/
  @Schema(description = "")
  
    public String getUserNm() {
    return userNm;
  }

  public void setUserNm(String userNm) {
    this.userNm = userNm;
  }

  public CaseNote noteDesc(String noteDesc) {
    this.noteDesc = noteDesc;
    return this;
  }

  /**
   * Get noteDesc
   * @return noteDesc
   **/
  @Schema(description = "")
  
    public String getNoteDesc() {
    return noteDesc;
  }

  public void setNoteDesc(String noteDesc) {
    this.noteDesc = noteDesc;
  }

  public CaseNote upload(CaseUpload upload) {
    this.upload = upload;
    return this;
  }

  /**
   * Get upload
   * @return upload
   **/
  @Schema(description = "")
  
    @Valid
    public CaseUpload getUpload() {
    return upload;
  }

  public void setUpload(CaseUpload upload) {
    this.upload = upload;
  }

  public CaseNote rowInsertTs(OffsetDateTime rowInsertTs) {
    this.rowInsertTs = rowInsertTs;
    return this;
  }

  /**
   * Get rowInsertTs
   * @return rowInsertTs
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getRowInsertTs() {
    return rowInsertTs;
  }

  public void setRowInsertTs(OffsetDateTime rowInsertTs) {
    this.rowInsertTs = rowInsertTs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseNote caseNote = (CaseNote) o;
    return Objects.equals(this.id, caseNote.id) &&
        Objects.equals(this.caseId, caseNote.caseId) &&
        Objects.equals(this.userNm, caseNote.userNm) &&
        Objects.equals(this.noteDesc, caseNote.noteDesc) &&
        Objects.equals(this.upload, caseNote.upload) &&
        Objects.equals(this.rowInsertTs, caseNote.rowInsertTs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, caseId, userNm, noteDesc, upload, rowInsertTs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseNote {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    userNm: ").append(toIndentedString(userNm)).append("\n");
    sb.append("    noteDesc: ").append(toIndentedString(noteDesc)).append("\n");
    sb.append("    upload: ").append(toIndentedString(upload)).append("\n");
    sb.append("    rowInsertTs: ").append(toIndentedString(rowInsertTs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


===============================

package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CaseUpload
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-16T10:35:06.591-04:00")


public class CaseUpload   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("caseId")
  private Integer caseId = null;

  @JsonProperty("fileNm")
  private String fileNm = null;

  @JsonProperty("userNm")
  private String userNm = null;

  @JsonProperty("deletedFlg")
  private Integer deletedFlg = null;

  @JsonProperty("rowInsertTs")
  private OffsetDateTime rowInsertTs = null;

  @JsonProperty("rowUpdtTs")
  private OffsetDateTime rowUpdtTs = null;

  public CaseUpload id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "1", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CaseUpload caseId(Integer caseId) {
    this.caseId = caseId;
    return this;
  }

  /**
   * Get caseId
   * @return caseId
   **/
  @Schema(description = "")
  
    public Integer getCaseId() {
    return caseId;
  }

  public void setCaseId(Integer caseId) {
    this.caseId = caseId;
  }

  public CaseUpload fileNm(String fileNm) {
    this.fileNm = fileNm;
    return this;
  }

  /**
   * Get fileNm
   * @return fileNm
   **/
  @Schema(description = "")
  
    public String getFileNm() {
    return fileNm;
  }

  public void setFileNm(String fileNm) {
    this.fileNm = fileNm;
  }

  public CaseUpload userNm(String userNm) {
    this.userNm = userNm;
    return this;
  }

  /**
   * Get userNm
   * @return userNm
   **/
  @Schema(description = "")
  
    public String getUserNm() {
    return userNm;
  }

  public void setUserNm(String userNm) {
    this.userNm = userNm;
  }

  public CaseUpload deletedFlg(Integer deletedFlg) {
    this.deletedFlg = deletedFlg;
    return this;
  }

  /**
   * Get deletedFlg
   * @return deletedFlg
   **/
  @Schema(description = "")
  
    public Integer getDeletedFlg() {
    return deletedFlg;
  }

  public void setDeletedFlg(Integer deletedFlg) {
    this.deletedFlg = deletedFlg;
  }

  public CaseUpload rowInsertTs(OffsetDateTime rowInsertTs) {
    this.rowInsertTs = rowInsertTs;
    return this;
  }

  /**
   * Get rowInsertTs
   * @return rowInsertTs
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getRowInsertTs() {
    return rowInsertTs;
  }

  public void setRowInsertTs(OffsetDateTime rowInsertTs) {
    this.rowInsertTs = rowInsertTs;
  }

  public CaseUpload rowUpdtTs(OffsetDateTime rowUpdtTs) {
    this.rowUpdtTs = rowUpdtTs;
    return this;
  }

  /**
   * Get rowUpdtTs
   * @return rowUpdtTs
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getRowUpdtTs() {
    return rowUpdtTs;
  }

  public void setRowUpdtTs(OffsetDateTime rowUpdtTs) {
    this.rowUpdtTs = rowUpdtTs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseUpload caseUpload = (CaseUpload) o;
    return Objects.equals(this.id, caseUpload.id) &&
        Objects.equals(this.caseId, caseUpload.caseId) &&
        Objects.equals(this.fileNm, caseUpload.fileNm) &&
        Objects.equals(this.userNm, caseUpload.userNm) &&
        Objects.equals(this.deletedFlg, caseUpload.deletedFlg) &&
        Objects.equals(this.rowInsertTs, caseUpload.rowInsertTs) &&
        Objects.equals(this.rowUpdtTs, caseUpload.rowUpdtTs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, caseId, fileNm, userNm, deletedFlg, rowInsertTs, rowUpdtTs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseUpload {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    fileNm: ").append(toIndentedString(fileNm)).append("\n");
    sb.append("    userNm: ").append(toIndentedString(userNm)).append("\n");
    sb.append("    deletedFlg: ").append(toIndentedString(deletedFlg)).append("\n");
    sb.append("    rowInsertTs: ").append(toIndentedString(rowInsertTs)).append("\n");
    sb.append("    rowUpdtTs: ").append(toIndentedString(rowUpdtTs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
