package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * dddOffice
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-23T11:43:01.849-05:00")


public class dddOffice   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("dddOfficeDesc")
  private String dddOfficeDesc = null;

  @JsonProperty("inactiveFlg")
  private Integer inactiveFlg = null;

  public dddOffice(Integer id, String dddOfficeDesc, Integer inactiveFlg) {
    this.id = id;
    this.dddOfficeDesc = dddOfficeDesc;
    this.inactiveFlg = inactiveFlg;
  }

  public dddOffice id(Integer id) {
    this.id = id;
    return this;
  }

  public dddOffice() {}

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

  public dddOffice dddOfficeDesc(String dddOfficeDesc) {
    this.dddOfficeDesc = dddOfficeDesc;
    return this;
  }

  /**
   * Get dddOfficeDesc
   * @return dddOfficeDesc
   **/
  @Schema(example = "Manhattan", description = "")
  
    public String getdddOfficeDesc() {
    return dddOfficeDesc;
  }

  public void setdddOfficeDesc(String dddOfficeDesc) {
    this.dddOfficeDesc = dddOfficeDesc;
  }

  public dddOffice inactiveFlg(Integer inactiveFlg) {
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
    dddOffice dddOffice = (dddOffice) o;
    return Objects.equals(this.id, dddOffice.id) &&
        Objects.equals(this.dddOfficeDesc, dddOffice.dddOfficeDesc) &&
        Objects.equals(this.inactiveFlg, dddOffice.inactiveFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dddOfficeDesc, inactiveFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class dddOffice {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dddOfficeDesc: ").append(toIndentedString(dddOfficeDesc)).append("\n");
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
