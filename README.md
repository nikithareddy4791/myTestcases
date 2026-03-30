package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Item
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class Item   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("itemDesc")
  private String itemDesc = null;

  @JsonProperty("inactiveFlg")
  private Integer inactiveFlg = null;

  public Item(Integer id, String itemDesc, Integer inactiveFlg) {
    this.id = id;
    this.itemDesc = itemDesc;
    this.inactiveFlg = inactiveFlg;
  }

  public Item id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "10", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Item itemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
    return this;
  }

  /**
   * Get itemDesc
   * @return itemDesc
   **/
  @Schema(example = "ECMS", description = "")
  
    public String getItemDesc() {
    return itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  public Item inactiveFlg(Integer inactiveFlg) {
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
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.itemDesc, item.itemDesc) &&
        Objects.equals(this.inactiveFlg, item.inactiveFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemDesc, inactiveFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    itemDesc: ").append(toIndentedString(itemDesc)).append("\n");
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


==========================

package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Category
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class Category   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("categoryDesc")
  private String categoryDesc = null;

  @JsonProperty("inactiveFlg")
  private Integer inactiveFlg = null;

  public Category(Integer id, String categoryDesc, Integer inactiveFlg) {
    this.id = id;
    this.categoryDesc = categoryDesc;
    this.inactiveFlg = inactiveFlg;
  }

  public Category() {}

  public Category id(Integer id) {
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

  public Category categoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
    return this;
  }

  /**
   * Get categoryDesc
   * @return categoryDesc
   **/
  @Schema(example = "Shootings", description = "")
  
    public String getCategoryDesc() {
    return categoryDesc;
  }

  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
  }

  public Category inactiveFlg(Integer inactiveFlg) {
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
    Category category = (Category) o;
    return Objects.equals(this.id, category.id) &&
        Objects.equals(this.categoryDesc, category.categoryDesc) &&
        Objects.equals(this.inactiveFlg, category.inactiveFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryDesc, inactiveFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Category {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoryDesc: ").append(toIndentedString(categoryDesc)).append("\n");
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


============================
package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Status
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-23T11:53:54.160-05:00")


public class Status   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("statusDesc")
  private String statusDesc = null;

  @JsonProperty("inactiveFlg")
  private Integer inactiveFlg = null;

  public Status(Integer id, String statusDesc, Integer inactiveFlg) {
    this.id = id;
    this.statusDesc = statusDesc;
    this.inactiveFlg = inactiveFlg;
  }

  public Status id(Integer id) {
    this.id = id;
    return this;
  }

  public Status() {}

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

  public Status statusDesc(String statusDesc) {
    this.statusDesc = statusDesc;
    return this;
  }

  /**
   * Get statusDesc
   * @return statusDesc
   **/
  @Schema(example = "Completed", description = "")
  
    public String getStatusDesc() {
    return statusDesc;
  }

  public void setStatusDesc(String statusDesc) {
    this.statusDesc = statusDesc;
  }

  public Status inactiveFlg(Integer inactiveFlg) {
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
    Status status = (Status) o;
    return Objects.equals(this.id, status.id) &&
        Objects.equals(this.statusDesc, status.statusDesc) &&
        Objects.equals(this.inactiveFlg, status.inactiveFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, statusDesc, inactiveFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Status {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    statusDesc: ").append(toIndentedString(statusDesc)).append("\n");
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

=======================

package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * Tag
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class Tag   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("tagDesc")
  private String tagDesc = null;

  @JsonProperty("inactiveFlg")
  private Integer inactiveFlg = null;

  public Tag(Integer id, String tagDesc, Integer inactiveFlg) {
    this.id = id;
    this.tagDesc = tagDesc;
    this.inactiveFlg = inactiveFlg;
  }

  public Tag id(Integer id) {
    this.id = id;
    return this;
  }

  public Tag() {}
  
  /**
   * Get id
   * @return id
   **/
  @Schema(example = "10", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Tag tagDesc(String tagDesc) {
    this.tagDesc = tagDesc;
    return this;
  }

  /**
   * Get tagDesc
   * @return tagDesc
   **/
  @Schema(example = "Gun", description = "")
  
    public String getTagDesc() {
    return tagDesc;
  }

  public void setTagDesc(String tagDesc) {
    this.tagDesc = tagDesc;
  }

  public Tag inactiveFlg(Integer inactiveFlg) {
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
    Tag tag = (Tag) o;
    return Objects.equals(this.id, tag.id) &&
        Objects.equals(this.tagDesc, tag.tagDesc) &&
        Objects.equals(this.inactiveFlg, tag.inactiveFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tagDesc, inactiveFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tag {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tagDesc: ").append(toIndentedString(tagDesc)).append("\n");
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

