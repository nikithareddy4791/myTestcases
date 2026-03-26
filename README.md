package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.nnnn.ddd.model.ADA;
import org.nnnn.ddd.model.ArrestInfo;
import org.nnnn.ddd.model.CaseTag;
import org.nnnn.ddd.model.Category;
import org.nnnn.ddd.model.dddOffice;
import org.nnnn.ddd.model.Status;
import org.nnnn.ddd.model.User;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * dddCaseSummary
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-04T09:49:39.555-05:00")


public class dddCaseSummary   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("parentId")
  private Integer parentId = null;

  @JsonProperty("arrId")
  private String arrId = null;

  @JsonProperty("ddd")
  private dddOffice ddd = null;

  @JsonProperty("status")
  private Status status = null;

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

  @JsonProperty("category")
  private Category category = null;

  @JsonProperty("arrest")
  private ArrestInfo arrest = null;

  @JsonProperty("ada")
  private ADA ada = null;

  @JsonProperty("tags")
  @Valid
  private List<CaseTag> tags = null;

  public dddCaseSummary id(Integer id) {
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

  public dddCaseSummary parentId(Integer parentId) {
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

  public dddCaseSummary arrId(String arrId) {
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

  public dddCaseSummary ddd(dddOffice ddd) {
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

  public dddCaseSummary status(Status status) {
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

  public dddCaseSummary assignedNm(String assignedNm) {
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

  public dddCaseSummary assignedNmInfo(User assignedNmInfo) {
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

  public dddCaseSummary requestDt(LocalDate requestDt) {
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

  public dddCaseSummary dueDt(LocalDate dueDt) {
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

  public dddCaseSummary completeDt(LocalDate completeDt) {
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

  public dddCaseSummary proactiveFlg(Integer proactiveFlg) {
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

  public dddCaseSummary activeFlg(Integer activeFlg) {
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

  public dddCaseSummary category(Category category) {
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

  public dddCaseSummary arrest(ArrestInfo arrest) {
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

  public dddCaseSummary ada(ADA ada) {
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

  public dddCaseSummary tags(List<CaseTag> tags) {
    this.tags = tags;
    return this;
  }

  public dddCaseSummary addTagsItem(CaseTag tagsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    dddCaseSummary dddCaseSummary = (dddCaseSummary) o;
    return Objects.equals(this.id, dddCaseSummary.id) &&
        Objects.equals(this.parentId, dddCaseSummary.parentId) &&
        Objects.equals(this.arrId, dddCaseSummary.arrId) &&
        Objects.equals(this.ddd, dddCaseSummary.ddd) &&
        Objects.equals(this.status, dddCaseSummary.status) &&
        Objects.equals(this.assignedNm, dddCaseSummary.assignedNm) &&
        Objects.equals(this.assignedNmInfo, dddCaseSummary.assignedNmInfo) &&
        Objects.equals(this.requestDt, dddCaseSummary.requestDt) &&
        Objects.equals(this.dueDt, dddCaseSummary.dueDt) &&
        Objects.equals(this.completeDt, dddCaseSummary.completeDt) &&
        Objects.equals(this.proactiveFlg, dddCaseSummary.proactiveFlg) &&
        Objects.equals(this.activeFlg, dddCaseSummary.activeFlg) &&
        Objects.equals(this.category, dddCaseSummary.category) &&
        Objects.equals(this.arrest, dddCaseSummary.arrest) &&
        Objects.equals(this.ada, dddCaseSummary.ada) &&
        Objects.equals(this.tags, dddCaseSummary.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, arrId, ddd, status, assignedNm, assignedNmInfo, requestDt, dueDt, completeDt, proactiveFlg, activeFlg, category, arrest, ada, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class dddCaseSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    arrId: ").append(toIndentedString(arrId)).append("\n");
    sb.append("    ddd: ").append(toIndentedString(ddd)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    assignedNm: ").append(toIndentedString(assignedNm)).append("\n");
    sb.append("    assignedNmInfo: ").append(toIndentedString(assignedNmInfo)).append("\n");
    sb.append("    requestDt: ").append(toIndentedString(requestDt)).append("\n");
    sb.append("    dueDt: ").append(toIndentedString(dueDt)).append("\n");
    sb.append("    completeDt: ").append(toIndentedString(completeDt)).append("\n");
    sb.append("    proactiveFlg: ").append(toIndentedString(proactiveFlg)).append("\n");
    sb.append("    activeFlg: ").append(toIndentedString(activeFlg)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    arrest: ").append(toIndentedString(arrest)).append("\n");
    sb.append("    ada: ").append(toIndentedString(ada)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
