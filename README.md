package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.nnnn.ddd.model.ADA;
import org.nnnn.ddd.model.ArrestInfo;
import org.nnnn.ddd.model.CaseItem;
import org.nnnn.ddd.model.CaseNote;
import org.nnnn.ddd.model.CaseTag;
import org.nnnn.ddd.model.CaseUpload;
import org.nnnn.ddd.model.Category;
import org.nnnn.ddd.model.dddCase;
import org.nnnn.ddd.model.dddOffice;
import org.nnnn.ddd.model.Status;
import org.nnnn.ddd.model.User;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * dddCase
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-17T11:34:35.864-04:00")


public class dddCase   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("parentId")
  private Integer parentId = null;

  @JsonProperty("arrId")
  private String arrId = null;

  @JsonProperty("assignedNm")
  private String assignedNm = null;

  @JsonProperty("assignedNmInfo")
  private User assignedNmInfo = null;

  @JsonProperty("requestDt")
  private LocalDate requestDt = null;

  @JsonProperty("dueDt")
  private LocalDate dueDt = null;

  @JsonProperty("completeDt")
  private LocalDate completeDt = null;

  @JsonProperty("proactiveFlg")
  private Integer proactiveFlg = null;

  @JsonProperty("activeFlg")
  private Integer activeFlg = null;

  @JsonProperty("relatedCases")
  @Valid
  private List<dddCase> relatedCases = null;

  @JsonProperty("category")
  private Category category = null;

  @JsonProperty("ddd")
  private dddOffice ddd = null;

  @JsonProperty("status")
  private Status status = null;

  @JsonProperty("arrest")
  private ArrestInfo arrest = null;

  @JsonProperty("ada")
  private ADA ada = null;

  @JsonProperty("tags")
  @Valid
  private List<CaseTag> tags = null;

  @JsonProperty("items")
  @Valid
  private List<CaseItem> items = null;

  @JsonProperty("notes")
  @Valid
  private List<CaseNote> notes = null;

  @JsonProperty("uploads")
  @Valid
  private List<CaseUpload> uploads = null;

  @JsonProperty("rowInsertTs")
  private OffsetDateTime rowInsertTs = null;

  @JsonProperty("rowUpdtTs")
  private OffsetDateTime rowUpdtTs = null;

  @JsonProperty("version")
  private Long version = null;

  private boolean isNew;

  @JsonIgnore
  public boolean isNew() {
    return this.isNew;
  }

  public void setNew(final boolean isNew) {
    this.isNew = isNew;
  }

  public dddCase id(Integer id) {
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

  public dddCase parentId(Integer parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * Get parentId
   * @return parentId
   **/
  @Schema(description = "")
  
    public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public dddCase arrId(String arrId) {
    this.arrId = arrId;
    return this;
  }

  /**
   * Get arrId
   * @return arrId
   **/
  @Schema(description = "")
  
    public String getArrId() {
    return arrId;
  }

  public void setArrId(String arrId) {
    this.arrId = arrId;
  }

  public dddCase assignedNm(String assignedNm) {
    this.assignedNm = assignedNm;
    return this;
  }

  /**
   * Get assignedNm
   * @return assignedNm
   **/
  @Schema(description = "")
  
    public String getAssignedNm() {
    return assignedNm;
  }

  public void setAssignedNm(String assignedNm) {
    this.assignedNm = assignedNm;
  }

  public dddCase assignedNmInfo(User assignedNmInfo) {
    this.assignedNmInfo = assignedNmInfo;
    return this;
  }

  /**
   * Get assignedNmInfo
   * @return assignedNmInfo
   **/
  @Schema(description = "")
  
    @Valid
    public User getAssignedNmInfo() {
    return assignedNmInfo;
  }

  public void setAssignedNmInfo(User assignedNmInfo) {
    this.assignedNmInfo = assignedNmInfo;
  }

  public dddCase requestDt(LocalDate requestDt) {
    this.requestDt = requestDt;
    return this;
  }

  /**
   * Get requestDt
   * @return requestDt
   **/
  @Schema(description = "")
  
    @Valid
    public LocalDate getRequestDt() {
    return requestDt;
  }

  public void setRequestDt(LocalDate requestDt) {
    this.requestDt = requestDt;
  }

  public dddCase dueDt(LocalDate dueDt) {
    this.dueDt = dueDt;
    return this;
  }

  /**
   * Get dueDt
   * @return dueDt
   **/
  @Schema(description = "")
  
    @Valid
    public LocalDate getDueDt() {
    return dueDt;
  }

  public void setDueDt(LocalDate dueDt) {
    this.dueDt = dueDt;
  }

  public dddCase completeDt(LocalDate completeDt) {
    this.completeDt = completeDt;
    return this;
  }

  /**
   * Get completeDt
   * @return completeDt
   **/
  @Schema(description = "")
  
    @Valid
    public LocalDate getCompleteDt() {
    return completeDt;
  }

  public void setCompleteDt(LocalDate completeDt) {
    this.completeDt = completeDt;
  }

  public dddCase proactiveFlg(Integer proactiveFlg) {
    this.proactiveFlg = proactiveFlg;
    return this;
  }

  /**
   * Get proactiveFlg
   * @return proactiveFlg
   **/
  @Schema(description = "")
  
    public Integer getProactiveFlg() {
    return proactiveFlg;
  }

  public void setProactiveFlg(Integer proactiveFlg) {
    this.proactiveFlg = proactiveFlg;
  }

  public dddCase activeFlg(Integer activeFlg) {
    this.activeFlg = activeFlg;
    return this;
  }

  /**
   * Get activeFlg
   * @return activeFlg
   **/
  @Schema(description = "")
  
    public Integer getActiveFlg() {
    return activeFlg;
  }

  public void setActiveFlg(Integer activeFlg) {
    this.activeFlg = activeFlg;
  }

  public dddCase relatedCases(List<dddCase> relatedCases) {
    this.relatedCases = relatedCases;
    return this;
  }

  public dddCase addRelatedCasesItem(dddCase relatedCasesItem) {
    if (this.relatedCases == null) {
      this.relatedCases = new ArrayList<dddCase>();
    }
    this.relatedCases.add(relatedCasesItem);
    return this;
  }

  /**
   * Get relatedCases
   * @return relatedCases
   **/
  @Schema(description = "")
      @Valid
    public List<dddCase> getRelatedCases() {
    return relatedCases;
  }

  public void setRelatedCases(List<dddCase> relatedCases) {
    this.relatedCases = relatedCases;
  }

  public dddCase category(Category category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
   **/
  @Schema(description = "")
  
    @Valid
    public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public dddCase ddd(dddOffice ddd) {
    this.ddd = ddd;
    return this;
  }

  /**
   * Get ddd
   * @return ddd
   **/
  @Schema(description = "")
  
    @Valid
    public dddOffice getddd() {
    return ddd;
  }

  public void setddd(dddOffice ddd) {
    this.ddd = ddd;
  }

  public dddCase status(Status status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(description = "")
  
    @Valid
    public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public dddCase arrest(ArrestInfo arrest) {
    this.arrest = arrest;
    return this;
  }

  /**
   * Get arrest
   * @return arrest
   **/
  @Schema(description = "")
  
    @Valid
    public ArrestInfo getArrest() {
    return arrest;
  }

  public void setArrest(ArrestInfo arrest) {
    this.arrest = arrest;
  }

  public dddCase ada(ADA ada) {
    this.ada = ada;
    return this;
  }

  /**
   * Get ada
   * @return ada
   **/
  @Schema(description = "")
  
    @Valid
    public ADA getAda() {
    return ada;
  }

  public void setAda(ADA ada) {
    this.ada = ada;
  }

  public dddCase tags(List<CaseTag> tags) {
    this.tags = tags;
    return this;
  }

  public dddCase addTagsItem(CaseTag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<CaseTag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   **/
  @Schema(description = "")
      @Valid
    public List<CaseTag> getTags() {
    return tags;
  }

  public void setTags(List<CaseTag> tags) {
    this.tags = tags;
  }

  public dddCase items(List<CaseItem> items) {
    this.items = items;
    return this;
  }

  public dddCase addItemsItem(CaseItem itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<CaseItem>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   **/
  @Schema(description = "")
      @Valid
    public List<CaseItem> getItems() {
    return items;
  }

  public void setItems(List<CaseItem> items) {
    this.items = items;
  }

  public dddCase notes(List<CaseNote> notes) {
    this.notes = notes;
    return this;
  }

  public dddCase addNotesItem(CaseNote notesItem) {
    if (this.notes == null) {
      this.notes = new ArrayList<CaseNote>();
    }
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
   **/
  @Schema(description = "")
      @Valid
    public List<CaseNote> getNotes() {
    return notes;
  }

  public void setNotes(List<CaseNote> notes) {
    this.notes = notes;
  }

  public dddCase uploads(List<CaseUpload> uploads) {
    this.uploads = uploads;
    return this;
  }

  public dddCase addUploadsItem(CaseUpload uploadsItem) {
    if (this.uploads == null) {
      this.uploads = new ArrayList<CaseUpload>();
    }
    this.uploads.add(uploadsItem);
    return this;
  }

  /**
   * Get uploads
   * @return uploads
   **/
  @Schema(description = "")
      @Valid
    public List<CaseUpload> getUploads() {
    return uploads;
  }

  public void setUploads(List<CaseUpload> uploads) {
    this.uploads = uploads;
  }

  public dddCase rowInsertTs(OffsetDateTime rowInsertTs) {
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

  public dddCase rowUpdtTs(OffsetDateTime rowUpdtTs) {
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

  public dddCase version(Long version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
   **/
  @Schema(description = "")
  
    public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    dddCase dddCase = (dddCase) o;
    return Objects.equals(this.id, dddCase.id) &&
        Objects.equals(this.parentId, dddCase.parentId) &&
        Objects.equals(this.arrId, dddCase.arrId) &&
        Objects.equals(this.assignedNm, dddCase.assignedNm) &&
        Objects.equals(this.assignedNmInfo, dddCase.assignedNmInfo) &&
        Objects.equals(this.requestDt, dddCase.requestDt) &&
        Objects.equals(this.dueDt, dddCase.dueDt) &&
        Objects.equals(this.completeDt, dddCase.completeDt) &&
        Objects.equals(this.proactiveFlg, dddCase.proactiveFlg) &&
        Objects.equals(this.activeFlg, dddCase.activeFlg) &&
        Objects.equals(this.relatedCases, dddCase.relatedCases) &&
        Objects.equals(this.category, dddCase.category) &&
        Objects.equals(this.ddd, dddCase.ddd) &&
        Objects.equals(this.status, dddCase.status) &&
        Objects.equals(this.arrest, dddCase.arrest) &&
        Objects.equals(this.ada, dddCase.ada) &&
        Objects.equals(this.tags, dddCase.tags) &&
        Objects.equals(this.items, dddCase.items) &&
        Objects.equals(this.notes, dddCase.notes) &&
        Objects.equals(this.uploads, dddCase.uploads) &&
        Objects.equals(this.rowInsertTs, dddCase.rowInsertTs) &&
        Objects.equals(this.rowUpdtTs, dddCase.rowUpdtTs) &&
        Objects.equals(this.version, dddCase.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, arrId, assignedNm, assignedNmInfo, requestDt, dueDt, completeDt, proactiveFlg, activeFlg, relatedCases, category, ddd, status, arrest, ada, tags, items, notes, uploads, rowInsertTs, rowUpdtTs, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class dddCase {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    arrId: ").append(toIndentedString(arrId)).append("\n");
    sb.append("    assignedNm: ").append(toIndentedString(assignedNm)).append("\n");
    sb.append("    assignedNmInfo: ").append(toIndentedString(assignedNmInfo)).append("\n");
    sb.append("    requestDt: ").append(toIndentedString(requestDt)).append("\n");
    sb.append("    dueDt: ").append(toIndentedString(dueDt)).append("\n");
    sb.append("    completeDt: ").append(toIndentedString(completeDt)).append("\n");
    sb.append("    proactiveFlg: ").append(toIndentedString(proactiveFlg)).append("\n");
    sb.append("    activeFlg: ").append(toIndentedString(activeFlg)).append("\n");
    sb.append("    relatedCases: ").append(toIndentedString(relatedCases)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    ddd: ").append(toIndentedString(ddd)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    arrest: ").append(toIndentedString(arrest)).append("\n");
    sb.append("    ada: ").append(toIndentedString(ada)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("    uploads: ").append(toIndentedString(uploads)).append("\n");
    sb.append("    rowInsertTs: ").append(toIndentedString(rowInsertTs)).append("\n");
    sb.append("    rowUpdtTs: ").append(toIndentedString(rowUpdtTs)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
