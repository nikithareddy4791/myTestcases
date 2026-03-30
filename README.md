
package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CreateCaseRequest
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-02T10:56:04.975-05:00")


public class CreateCaseRequest   {
  @JsonProperty("arrId")
  private String arrId = null;

  @JsonProperty("parentId")
  private Integer parentId = null;

  @JsonProperty("proactiveFlg")
  private Integer proactiveFlg = null;

  @JsonProperty("dddOfficeId")
  private Integer dddOfficeId = null;

  @JsonProperty("requestDt")
  private LocalDate requestDt = null;

  @JsonProperty("dueDt")
  private LocalDate dueDt = null;

  public CreateCaseRequest arrId(String arrId) {
    this.arrId = arrId;
    return this;
  }

  /**
   * Get arrId
   * @return arrId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getArrId() {
    return arrId;
  }

  public void setArrId(String arrId) {
    this.arrId = arrId;
  }

  public CreateCaseRequest parentId(Integer parentId) {
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

  public CreateCaseRequest proactiveFlg(Integer proactiveFlg) {
    this.proactiveFlg = proactiveFlg;
    return this;
  }

  /**
   * Get proactiveFlg
   * @return proactiveFlg
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getProactiveFlg() {
    return proactiveFlg;
  }

  public void setProactiveFlg(Integer proactiveFlg) {
    this.proactiveFlg = proactiveFlg;
  }

  public CreateCaseRequest dddOfficeId(Integer dddOfficeId) {
    this.dddOfficeId = dddOfficeId;
    return this;
  }

  /**
   * Get dddOfficeId
   * @return dddOfficeId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getdddOfficeId() {
    return dddOfficeId;
  }

  public void setdddOfficeId(Integer dddOfficeId) {
    this.dddOfficeId = dddOfficeId;
  }

  public CreateCaseRequest requestDt(LocalDate requestDt) {
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

  public CreateCaseRequest dueDt(LocalDate dueDt) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCaseRequest createCaseRequest = (CreateCaseRequest) o;
    return Objects.equals(this.arrId, createCaseRequest.arrId) &&
        Objects.equals(this.parentId, createCaseRequest.parentId) &&
        Objects.equals(this.proactiveFlg, createCaseRequest.proactiveFlg) &&
        Objects.equals(this.dddOfficeId, createCaseRequest.dddOfficeId) &&
        Objects.equals(this.requestDt, createCaseRequest.requestDt) &&
        Objects.equals(this.dueDt, createCaseRequest.dueDt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arrId, parentId, proactiveFlg, dddOfficeId, requestDt, dueDt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCaseRequest {\n");
    
    sb.append("    arrId: ").append(toIndentedString(arrId)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    proactiveFlg: ").append(toIndentedString(proactiveFlg)).append("\n");
    sb.append("    dddOfficeId: ").append(toIndentedString(dddOfficeId)).append("\n");
    sb.append("    requestDt: ").append(toIndentedString(requestDt)).append("\n");
    sb.append("    dueDt: ").append(toIndentedString(dueDt)).append("\n");
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


===================================




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
 * CaseItem
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-05T10:09:30.346-05:00")


public class CaseItem   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("caseId")
  private Integer caseId = null;

  @JsonProperty("itemId")
  private Integer itemId = null;

  @JsonProperty("itemDesc")
  private String itemDesc = null;

  @JsonProperty("statusDesc")
  private String statusDesc = null;

  @JsonProperty("noteDesc")
  private String noteDesc = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("rowUpdtTs")
  private OffsetDateTime rowUpdtTs = null;

  public CaseItem id(Integer id) {
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

  public CaseItem caseId(Integer caseId) {
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

  public CaseItem itemId(Integer itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Get itemId
   * @return itemId
   **/
  @Schema(description = "")
  
    public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public CaseItem itemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
    return this;
  }

  /**
   * Get itemDesc
   * @return itemDesc
   **/
  @Schema(description = "")
  
    public String getItemDesc() {
    return itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  public CaseItem statusDesc(String statusDesc) {
    this.statusDesc = statusDesc;
    return this;
  }

  /**
   * Get statusDesc
   * @return statusDesc
   **/
  @Schema(description = "")
  
    public String getStatusDesc() {
    return statusDesc;
  }

  public void setStatusDesc(String statusDesc) {
    this.statusDesc = statusDesc;
  }

  public CaseItem noteDesc(String noteDesc) {
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

  public CaseItem quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   **/
  @Schema(description = "")
  
    public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public CaseItem rowUpdtTs(OffsetDateTime rowUpdtTs) {
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
    CaseItem caseItem = (CaseItem) o;
    return Objects.equals(this.id, caseItem.id) &&
        Objects.equals(this.caseId, caseItem.caseId) &&
        Objects.equals(this.itemId, caseItem.itemId) &&
        Objects.equals(this.itemDesc, caseItem.itemDesc) &&
        Objects.equals(this.statusDesc, caseItem.statusDesc) &&
        Objects.equals(this.noteDesc, caseItem.noteDesc) &&
        Objects.equals(this.quantity, caseItem.quantity) &&
        Objects.equals(this.rowUpdtTs, caseItem.rowUpdtTs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, caseId, itemId, itemDesc, statusDesc, noteDesc, quantity, rowUpdtTs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    itemDesc: ").append(toIndentedString(itemDesc)).append("\n");
    sb.append("    statusDesc: ").append(toIndentedString(statusDesc)).append("\n");
    sb.append("    noteDesc: ").append(toIndentedString(noteDesc)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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



==============================================
package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.nnnn.ddd.model.dddCaseStats;
import org.nnnn.ddd.model.dddCaseSummary;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CaseListResponse
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-02T10:16:07.568-05:00")


public class CaseListResponse   {
  @JsonProperty("totalElements")
  private Long totalElements = null;

  @JsonProperty("totalPages")
  private Integer totalPages = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("pageNumber")
  private Integer pageNumber = null;

  @JsonProperty("isFirst")
  private Boolean isFirst = null;

  @JsonProperty("isLast")
  private Boolean isLast = null;

  @JsonProperty("hasNext")
  private Boolean hasNext = null;

  @JsonProperty("hasPrevious")
  private Boolean hasPrevious = null;

  @JsonProperty("caseSummaries")
  @Valid
  private List<dddCaseSummary> caseSummaries = null;

  @JsonProperty("caseStats")
  private dddCaseStats caseStats = null;

  public CaseListResponse totalElements(Long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
   **/
  @Schema(description = "")
  
    public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  public CaseListResponse totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
   **/
  @Schema(description = "")
  
    public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public CaseListResponse pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
   **/
  @Schema(description = "")
  
    public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public CaseListResponse pageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

  /**
   * Get pageNumber
   * @return pageNumber
   **/
  @Schema(description = "")
  
    public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public CaseListResponse isFirst(Boolean isFirst) {
    this.isFirst = isFirst;
    return this;
  }

  /**
   * Get isFirst
   * @return isFirst
   **/
  @Schema(description = "")
  
    public Boolean isIsFirst() {
    return isFirst;
  }

  public void setIsFirst(Boolean isFirst) {
    this.isFirst = isFirst;
  }

  public CaseListResponse isLast(Boolean isLast) {
    this.isLast = isLast;
    return this;
  }

  /**
   * Get isLast
   * @return isLast
   **/
  @Schema(description = "")
  
    public Boolean isIsLast() {
    return isLast;
  }

  public void setIsLast(Boolean isLast) {
    this.isLast = isLast;
  }

  public CaseListResponse hasNext(Boolean hasNext) {
    this.hasNext = hasNext;
    return this;
  }

  /**
   * Get hasNext
   * @return hasNext
   **/
  @Schema(description = "")
  
    public Boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(Boolean hasNext) {
    this.hasNext = hasNext;
  }

  public CaseListResponse hasPrevious(Boolean hasPrevious) {
    this.hasPrevious = hasPrevious;
    return this;
  }

  /**
   * Get hasPrevious
   * @return hasPrevious
   **/
  @Schema(description = "")
  
    public Boolean isHasPrevious() {
    return hasPrevious;
  }

  public void setHasPrevious(Boolean hasPrevious) {
    this.hasPrevious = hasPrevious;
  }

  public CaseListResponse caseSummaries(List<dddCaseSummary> caseSummaries) {
    this.caseSummaries = caseSummaries;
    return this;
  }

  public CaseListResponse addCaseSummariesItem(dddCaseSummary caseSummariesItem) {
    if (this.caseSummaries == null) {
      this.caseSummaries = new ArrayList<dddCaseSummary>();
    }
    this.caseSummaries.add(caseSummariesItem);
    return this;
  }

  /**
   * Get caseSummaries
   * @return caseSummaries
   **/
  @Schema(description = "")
      @Valid
    public List<dddCaseSummary> getCaseSummaries() {
    return caseSummaries;
  }

  public void setCaseSummaries(List<dddCaseSummary> caseSummaries) {
    this.caseSummaries = caseSummaries;
  }

  public CaseListResponse caseStats(dddCaseStats caseStats) {
    this.caseStats = caseStats;
    return this;
  }

  /**
   * Get caseStats
   * @return caseStats
   **/
  @Schema(description = "")
  
    @Valid
    public dddCaseStats getCaseStats() {
    return caseStats;
  }

  public void setCaseStats(dddCaseStats caseStats) {
    this.caseStats = caseStats;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseListResponse caseListResponse = (CaseListResponse) o;
    return Objects.equals(this.totalElements, caseListResponse.totalElements) &&
        Objects.equals(this.totalPages, caseListResponse.totalPages) &&
        Objects.equals(this.pageSize, caseListResponse.pageSize) &&
        Objects.equals(this.pageNumber, caseListResponse.pageNumber) &&
        Objects.equals(this.isFirst, caseListResponse.isFirst) &&
        Objects.equals(this.isLast, caseListResponse.isLast) &&
        Objects.equals(this.hasNext, caseListResponse.hasNext) &&
        Objects.equals(this.hasPrevious, caseListResponse.hasPrevious) &&
        Objects.equals(this.caseSummaries, caseListResponse.caseSummaries) &&
        Objects.equals(this.caseStats, caseListResponse.caseStats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalElements, totalPages, pageSize, pageNumber, isFirst, isLast, hasNext, hasPrevious, caseSummaries, caseStats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseListResponse {\n");
    
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
    sb.append("    isFirst: ").append(toIndentedString(isFirst)).append("\n");
    sb.append("    isLast: ").append(toIndentedString(isLast)).append("\n");
    sb.append("    hasNext: ").append(toIndentedString(hasNext)).append("\n");
    sb.append("    hasPrevious: ").append(toIndentedString(hasPrevious)).append("\n");
    sb.append("    caseSummaries: ").append(toIndentedString(caseSummaries)).append("\n");
    sb.append("    caseStats: ").append(toIndentedString(caseStats)).append("\n");
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

====================


package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * User
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class User   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("rank")
  private String rank = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("tax")
  private String tax = null;

  @JsonProperty("cmdCode")
  private String cmdCode = null;

  @JsonProperty("mobile")
  private String mobile = null;

  @JsonProperty("roles")
  @Valid
  private List<String> roles = null;

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(example = "theUser", description = "")
  
    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  @Schema(example = "John", description = "")
  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  @Schema(example = "James", description = "")
  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(example = "john@email.com", description = "")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User rank(String rank) {
    this.rank = rank;
    return this;
  }

  /**
   * Get rank
   * @return rank
   **/
  @Schema(description = "")
  
    public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public User title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @Schema(description = "")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User tax(String tax) {
    this.tax = tax;
    return this;
  }

  /**
   * Get tax
   * @return tax
   **/
  @Schema(example = "123456", description = "")
  
    public String getTax() {
    return tax;
  }

  public void setTax(String tax) {
    this.tax = tax;
  }

  public User cmdCode(String cmdCode) {
    this.cmdCode = cmdCode;
    return this;
  }

  /**
   * Get cmdCode
   * @return cmdCode
   **/
  @Schema(description = "")
  
    public String getCmdCode() {
    return cmdCode;
  }

  public void setCmdCode(String cmdCode) {
    this.cmdCode = cmdCode;
  }

  public User mobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  /**
   * Get mobile
   * @return mobile
   **/
  @Schema(description = "")
  
    public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public User roles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public User addRolesItem(String rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<String>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
   * Get roles
   * @return roles
   **/
  @Schema(description = "")
  
    public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.username, user.username) &&
        Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.lastName, user.lastName) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.rank, user.rank) &&
        Objects.equals(this.title, user.title) &&
        Objects.equals(this.tax, user.tax) &&
        Objects.equals(this.cmdCode, user.cmdCode) &&
        Objects.equals(this.mobile, user.mobile) &&
        Objects.equals(this.roles, user.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, firstName, lastName, email, rank, title, tax, cmdCode, mobile, roles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
    sb.append("    cmdCode: ").append(toIndentedString(cmdCode)).append("\n");
    sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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


=============================================


package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * ADA
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-19T11:39:45.031-05:00")


public class ADA   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("jobTitleDesc")
  private String jobTitleDesc = null;

  @JsonProperty("frstNm")
  private String frstNm = null;

  @JsonProperty("lastNm")
  private String lastNm = null;

  @JsonProperty("emailAddrDesc")
  private String emailAddrDesc = null;

  @JsonProperty("boroughNm")
  private String boroughNm = null;

  @JsonProperty("busPhoneNum")
  private String busPhoneNum = null;

  @JsonProperty("cellPhoneNum")
  private String cellPhoneNum = null;

  @JsonProperty("faxNum")
  private String faxNum = null;

  @JsonProperty("noteDesc")
  private String noteDesc = null;

  @JsonProperty("archiveFlg")
  private Integer archiveFlg = null;

  @JsonProperty("inactiveFlg")
  private Integer inactiveFlg = null;

  public ADA id(Integer id) {
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

  public ADA jobTitleDesc(String jobTitleDesc) {
    this.jobTitleDesc = jobTitleDesc;
    return this;
  }

  /**
   * Get jobTitleDesc
   * @return jobTitleDesc
   **/
  @Schema(description = "")
  
    public String getJobTitleDesc() {
    return jobTitleDesc;
  }

  public void setJobTitleDesc(String jobTitleDesc) {
    this.jobTitleDesc = jobTitleDesc;
  }

  public ADA frstNm(String frstNm) {
    this.frstNm = frstNm;
    return this;
  }

  /**
   * Get frstNm
   * @return frstNm
   **/
  @Schema(description = "")
  
    public String getFrstNm() {
    return frstNm;
  }

  public void setFrstNm(String frstNm) {
    this.frstNm = frstNm;
  }

  public ADA lastNm(String lastNm) {
    this.lastNm = lastNm;
    return this;
  }

  /**
   * Get lastNm
   * @return lastNm
   **/
  @Schema(description = "")
  
    public String getLastNm() {
    return lastNm;
  }

  public void setLastNm(String lastNm) {
    this.lastNm = lastNm;
  }

  public ADA emailAddrDesc(String emailAddrDesc) {
    this.emailAddrDesc = emailAddrDesc;
    return this;
  }

  /**
   * Get emailAddrDesc
   * @return emailAddrDesc
   **/
  @Schema(description = "")
  
    public String getEmailAddrDesc() {
    return emailAddrDesc;
  }

  public void setEmailAddrDesc(String emailAddrDesc) {
    this.emailAddrDesc = emailAddrDesc;
  }

  public ADA boroughNm(String boroughNm) {
    this.boroughNm = boroughNm;
    return this;
  }

  /**
   * Get boroughNm
   * @return boroughNm
   **/
  @Schema(description = "")
  
    public String getBoroughNm() {
    return boroughNm;
  }

  public void setBoroughNm(String boroughNm) {
    this.boroughNm = boroughNm;
  }

  public ADA busPhoneNum(String busPhoneNum) {
    this.busPhoneNum = busPhoneNum;
    return this;
  }

  /**
   * Get busPhoneNum
   * @return busPhoneNum
   **/
  @Schema(description = "")
  
    public String getBusPhoneNum() {
    return busPhoneNum;
  }

  public void setBusPhoneNum(String busPhoneNum) {
    this.busPhoneNum = busPhoneNum;
  }

  public ADA cellPhoneNum(String cellPhoneNum) {
    this.cellPhoneNum = cellPhoneNum;
    return this;
  }

  /**
   * Get cellPhoneNum
   * @return cellPhoneNum
   **/
  @Schema(description = "")
  
    public String getCellPhoneNum() {
    return cellPhoneNum;
  }

  public void setCellPhoneNum(String cellPhoneNum) {
    this.cellPhoneNum = cellPhoneNum;
  }

  public ADA faxNum(String faxNum) {
    this.faxNum = faxNum;
    return this;
  }

  /**
   * Get faxNum
   * @return faxNum
   **/
  @Schema(description = "")
  
    public String getFaxNum() {
    return faxNum;
  }

  public void setFaxNum(String faxNum) {
    this.faxNum = faxNum;
  }

  public ADA noteDesc(String noteDesc) {
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

  public ADA archiveFlg(Integer archiveFlg) {
    this.archiveFlg = archiveFlg;
    return this;
  }

  /**
   * Get archiveFlg
   * @return archiveFlg
   **/
  @Schema(description = "")
  
    public Integer getArchiveFlg() {
    return archiveFlg;
  }

  public void setArchiveFlg(Integer archiveFlg) {
    this.archiveFlg = archiveFlg;
  }

  public ADA inactiveFlg(Integer inactiveFlg) {
    this.inactiveFlg = inactiveFlg;
    return this;
  }

  /**
   * Get inactiveFlg
   * @return inactiveFlg
   **/
  @Schema(description = "")
  
    public Integer getInactiveFlg() {
    return inactiveFlg;
  }

  public void setInactiveFlg(Integer inactiveFlg) {
    this.inactiveFlg = inactiveFlg;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ADA ADA = (ADA) o;
    return Objects.equals(this.id, ADA.id) &&
        Objects.equals(this.jobTitleDesc, ADA.jobTitleDesc) &&
        Objects.equals(this.frstNm, ADA.frstNm) &&
        Objects.equals(this.lastNm, ADA.lastNm) &&
        Objects.equals(this.emailAddrDesc, ADA.emailAddrDesc) &&
        Objects.equals(this.boroughNm, ADA.boroughNm) &&
        Objects.equals(this.busPhoneNum, ADA.busPhoneNum) &&
        Objects.equals(this.cellPhoneNum, ADA.cellPhoneNum) &&
        Objects.equals(this.faxNum, ADA.faxNum) &&
        Objects.equals(this.noteDesc, ADA.noteDesc) &&
        Objects.equals(this.archiveFlg, ADA.archiveFlg) &&
        Objects.equals(this.inactiveFlg, ADA.inactiveFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, jobTitleDesc, frstNm, lastNm, emailAddrDesc, boroughNm, busPhoneNum, cellPhoneNum, faxNum, noteDesc, archiveFlg, inactiveFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ADA {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    jobTitleDesc: ").append(toIndentedString(jobTitleDesc)).append("\n");
    sb.append("    frstNm: ").append(toIndentedString(frstNm)).append("\n");
    sb.append("    lastNm: ").append(toIndentedString(lastNm)).append("\n");
    sb.append("    emailAddrDesc: ").append(toIndentedString(emailAddrDesc)).append("\n");
    sb.append("    boroughNm: ").append(toIndentedString(boroughNm)).append("\n");
    sb.append("    busPhoneNum: ").append(toIndentedString(busPhoneNum)).append("\n");
    sb.append("    cellPhoneNum: ").append(toIndentedString(cellPhoneNum)).append("\n");
    sb.append("    faxNum: ").append(toIndentedString(faxNum)).append("\n");
    sb.append("    noteDesc: ").append(toIndentedString(noteDesc)).append("\n");
    sb.append("    archiveFlg: ").append(toIndentedString(archiveFlg)).append("\n");
    sb.append("    inactiveFlg: ").append(toIndentedString(inactiveFlg)).append("\n");
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


=============================

package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CaseFilter
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-03-19T10:45:48.904-04:00")


public class CaseFilter   {
  @JsonProperty("arrId")
  private String arrId = null;

  @JsonProperty("requestDtFrom")
  private String requestDtFrom = null;

  @JsonProperty("requestDtTo")
  private String requestDtTo = null;

  @JsonProperty("dueDtFrom")
  private String dueDtFrom = null;

  @JsonProperty("dueDtTo")
  private String dueDtTo = null;

  @JsonProperty("assignedNm")
  private String assignedNm = null;

  @JsonProperty("categoryIds")
  @Valid
  private List<Integer> categoryIds = null;

  @JsonProperty("dddOfficeIds")
  @Valid
  private List<Integer> dddOfficeIds = null;

  @JsonProperty("statusIds")
  @Valid
  private List<Integer> statusIds = null;

  @JsonProperty("tagIds")
  @Valid
  private List<Integer> tagIds = null;

  @JsonProperty("proactiveFlg")
  private Integer proactiveFlg = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("pageNumber")
  private Integer pageNumber = null;

  @JsonProperty("sortBy")
  @Valid
  private List<String> sortBy = null;

  @JsonProperty("sortOrder")
  @Valid
  private List<Integer> sortOrder = null;

  public CaseFilter arrId(String arrId) {
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

  public CaseFilter requestDtFrom(String requestDtFrom) {
    this.requestDtFrom = requestDtFrom;
    return this;
  }

  /**
   * Get requestDtFrom
   * @return requestDtFrom
   **/
  @Schema(description = "")
  
    public String getRequestDtFrom() {
    return requestDtFrom;
  }

  public void setRequestDtFrom(String requestDtFrom) {
    this.requestDtFrom = requestDtFrom;
  }

  public CaseFilter requestDtTo(String requestDtTo) {
    this.requestDtTo = requestDtTo;
    return this;
  }

  /**
   * Get requestDtTo
   * @return requestDtTo
   **/
  @Schema(description = "")
  
    public String getRequestDtTo() {
    return requestDtTo;
  }

  public void setRequestDtTo(String requestDtTo) {
    this.requestDtTo = requestDtTo;
  }

  public CaseFilter dueDtFrom(String dueDtFrom) {
    this.dueDtFrom = dueDtFrom;
    return this;
  }

  /**
   * Get dueDtFrom
   * @return dueDtFrom
   **/
  @Schema(description = "")
  
    public String getDueDtFrom() {
    return dueDtFrom;
  }

  public void setDueDtFrom(String dueDtFrom) {
    this.dueDtFrom = dueDtFrom;
  }

  public CaseFilter dueDtTo(String dueDtTo) {
    this.dueDtTo = dueDtTo;
    return this;
  }

  /**
   * Get dueDtTo
   * @return dueDtTo
   **/
  @Schema(description = "")
  
    public String getDueDtTo() {
    return dueDtTo;
  }

  public void setDueDtTo(String dueDtTo) {
    this.dueDtTo = dueDtTo;
  }

  public CaseFilter assignedNm(String assignedNm) {
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

  public CaseFilter categoryIds(List<Integer> categoryIds) {
    this.categoryIds = categoryIds;
    return this;
  }

  public CaseFilter addCategoryIdsItem(Integer categoryIdsItem) {
    if (this.categoryIds == null) {
      this.categoryIds = new ArrayList<Integer>();
    }
    this.categoryIds.add(categoryIdsItem);
    return this;
  }

  /**
   * Get categoryIds
   * @return categoryIds
   **/
  @Schema(description = "")
  
    public List<Integer> getCategoryIds() {
    return categoryIds;
  }

  public void setCategoryIds(List<Integer> categoryIds) {
    this.categoryIds = categoryIds;
  }

  public CaseFilter dddOfficeIds(List<Integer> dddOfficeIds) {
    this.dddOfficeIds = dddOfficeIds;
    return this;
  }

  public CaseFilter adddddOfficeIdsItem(Integer dddOfficeIdsItem) {
    if (this.dddOfficeIds == null) {
      this.dddOfficeIds = new ArrayList<Integer>();
    }
    this.dddOfficeIds.add(dddOfficeIdsItem);
    return this;
  }

  /**
   * Get dddOfficeIds
   * @return dddOfficeIds
   **/
  @Schema(description = "")
  
    public List<Integer> getdddOfficeIds() {
    return dddOfficeIds;
  }

  public void setdddOfficeIds(List<Integer> dddOfficeIds) {
    this.dddOfficeIds = dddOfficeIds;
  }

  public CaseFilter statusIds(List<Integer> statusIds) {
    this.statusIds = statusIds;
    return this;
  }

  public CaseFilter addStatusIdsItem(Integer statusIdsItem) {
    if (this.statusIds == null) {
      this.statusIds = new ArrayList<Integer>();
    }
    this.statusIds.add(statusIdsItem);
    return this;
  }

  /**
   * Get statusIds
   * @return statusIds
   **/
  @Schema(description = "")
  
    public List<Integer> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<Integer> statusIds) {
    this.statusIds = statusIds;
  }

  public CaseFilter tagIds(List<Integer> tagIds) {
    this.tagIds = tagIds;
    return this;
  }

  public CaseFilter addTagIdsItem(Integer tagIdsItem) {
    if (this.tagIds == null) {
      this.tagIds = new ArrayList<Integer>();
    }
    this.tagIds.add(tagIdsItem);
    return this;
  }

  /**
   * Get tagIds
   * @return tagIds
   **/
  @Schema(description = "")
  
    public List<Integer> getTagIds() {
    return tagIds;
  }

  public void setTagIds(List<Integer> tagIds) {
    this.tagIds = tagIds;
  }

  public CaseFilter proactiveFlg(Integer proactiveFlg) {
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

  public CaseFilter pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
   **/
  @Schema(description = "")
  
    public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public CaseFilter pageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

  /**
   * Get pageNumber
   * @return pageNumber
   **/
  @Schema(description = "")
  
    public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public CaseFilter sortBy(List<String> sortBy) {
    this.sortBy = sortBy;
    return this;
  }

  public CaseFilter addSortByItem(String sortByItem) {
    if (this.sortBy == null) {
      this.sortBy = new ArrayList<String>();
    }
    this.sortBy.add(sortByItem);
    return this;
  }

  /**
   * Get sortBy
   * @return sortBy
   **/
  @Schema(description = "")
  
    public List<String> getSortBy() {
    return sortBy;
  }

  public void setSortBy(List<String> sortBy) {
    this.sortBy = sortBy;
  }

  public CaseFilter sortOrder(List<Integer> sortOrder) {
    this.sortOrder = sortOrder;
    return this;
  }

  public CaseFilter addSortOrderItem(Integer sortOrderItem) {
    if (this.sortOrder == null) {
      this.sortOrder = new ArrayList<Integer>();
    }
    this.sortOrder.add(sortOrderItem);
    return this;
  }

  /**
   * Get sortOrder
   * @return sortOrder
   **/
  @Schema(description = "")
  
    public List<Integer> getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(List<Integer> sortOrder) {
    this.sortOrder = sortOrder;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseFilter caseFilter = (CaseFilter) o;
    return Objects.equals(this.arrId, caseFilter.arrId) &&
        Objects.equals(this.requestDtFrom, caseFilter.requestDtFrom) &&
        Objects.equals(this.requestDtTo, caseFilter.requestDtTo) &&
        Objects.equals(this.dueDtFrom, caseFilter.dueDtFrom) &&
        Objects.equals(this.dueDtTo, caseFilter.dueDtTo) &&
        Objects.equals(this.assignedNm, caseFilter.assignedNm) &&
        Objects.equals(this.categoryIds, caseFilter.categoryIds) &&
        Objects.equals(this.dddOfficeIds, caseFilter.dddOfficeIds) &&
        Objects.equals(this.statusIds, caseFilter.statusIds) &&
        Objects.equals(this.tagIds, caseFilter.tagIds) &&
        Objects.equals(this.proactiveFlg, caseFilter.proactiveFlg) &&
        Objects.equals(this.pageSize, caseFilter.pageSize) &&
        Objects.equals(this.pageNumber, caseFilter.pageNumber) &&
        Objects.equals(this.sortBy, caseFilter.sortBy) &&
        Objects.equals(this.sortOrder, caseFilter.sortOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arrId, requestDtFrom, requestDtTo, dueDtFrom, dueDtTo, assignedNm, categoryIds, dddOfficeIds, statusIds, tagIds, proactiveFlg, pageSize, pageNumber, sortBy, sortOrder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseFilter {\n");
    
    sb.append("    arrId: ").append(toIndentedString(arrId)).append("\n");
    sb.append("    requestDtFrom: ").append(toIndentedString(requestDtFrom)).append("\n");
    sb.append("    requestDtTo: ").append(toIndentedString(requestDtTo)).append("\n");
    sb.append("    dueDtFrom: ").append(toIndentedString(dueDtFrom)).append("\n");
    sb.append("    dueDtTo: ").append(toIndentedString(dueDtTo)).append("\n");
    sb.append("    assignedNm: ").append(toIndentedString(assignedNm)).append("\n");
    sb.append("    categoryIds: ").append(toIndentedString(categoryIds)).append("\n");
    sb.append("    dddOfficeIds: ").append(toIndentedString(dddOfficeIds)).append("\n");
    sb.append("    statusIds: ").append(toIndentedString(statusIds)).append("\n");
    sb.append("    tagIds: ").append(toIndentedString(tagIds)).append("\n");
    sb.append("    proactiveFlg: ").append(toIndentedString(proactiveFlg)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
    sb.append("    sortBy: ").append(toIndentedString(sortBy)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
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



