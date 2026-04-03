package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * ReferenceCode
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-10T13:32:19.578-05:00")


public class ReferenceCode   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("codeType")
  private String codeType = null;

  @JsonProperty("codeVal")
  private String codeVal = null;

  @JsonProperty("codeDesc")
  private String codeDesc = null;

  public ReferenceCode(Integer id, String codeType, String codeVal, String codeDesc) {
    this.id = id;
    this.codeType = codeType;
    this.codeVal = codeVal;
    this.codeDesc = codeDesc;
  }

  public ReferenceCode id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ReferenceCode codeType(String codeType) {
    this.codeType = codeType;
    return this;
  }

  /**
   * Get codeType
   * @return codeType
   **/
  @Schema(description = "")
  
    public String getCodeType() {
    return codeType;
  }

  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }

  public ReferenceCode codeVal(String codeVal) {
    this.codeVal = codeVal;
    return this;
  }

  /**
   * Get codeVal
   * @return codeVal
   **/
  @Schema(description = "")
  
    public String getCodeVal() {
    return codeVal;
  }

  public void setCodeVal(String codeVal) {
    this.codeVal = codeVal;
  }

  public ReferenceCode codeDesc(String codeDesc) {
    this.codeDesc = codeDesc;
    return this;
  }

  /**
   * Get codeDesc
   * @return codeDesc
   **/
  @Schema(description = "")
  
    public String getCodeDesc() {
    return codeDesc;
  }

  public void setCodeDesc(String codeDesc) {
    this.codeDesc = codeDesc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReferenceCode referenceCode = (ReferenceCode) o;
    return Objects.equals(this.id, referenceCode.id) &&
        Objects.equals(this.codeType, referenceCode.codeType) &&
        Objects.equals(this.codeVal, referenceCode.codeVal) &&
        Objects.equals(this.codeDesc, referenceCode.codeDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codeType, codeVal, codeDesc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReferenceCode {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codeType: ").append(toIndentedString(codeType)).append("\n");
    sb.append("    codeVal: ").append(toIndentedString(codeVal)).append("\n");
    sb.append("    codeDesc: ").append(toIndentedString(codeDesc)).append("\n");
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
