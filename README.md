package org.nnnn.ddd.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * dddCaseStats
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-02-26T10:29:10.117-05:00")


public class dddCaseStats   {
  @JsonProperty("overdueCount")
  private Integer overdueCount = null;

  @JsonProperty("comingDueCount")
  private Integer comingDueCount = null;

  public dddCaseStats overdueCount(Integer overdueCount) {
    this.overdueCount = overdueCount;
    return this;
  }

  /**
   * Get overdueCount
   * @return overdueCount
   **/
  @Schema(example = "1", description = "")
  
    public Integer getOverdueCount() {
    return overdueCount;
  }

  public void setOverdueCount(Integer overdueCount) {
    this.overdueCount = overdueCount;
  }

  public dddCaseStats comingDueCount(Integer comingDueCount) {
    this.comingDueCount = comingDueCount;
    return this;
  }

  /**
   * Get comingDueCount
   * @return comingDueCount
   **/
  @Schema(example = "1", description = "")
  
    public Integer getComingDueCount() {
    return comingDueCount;
  }

  public void setComingDueCount(Integer comingDueCount) {
    this.comingDueCount = comingDueCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    dddCaseStats dddCaseStats = (dddCaseStats) o;
    return Objects.equals(this.overdueCount, dddCaseStats.overdueCount) &&
        Objects.equals(this.comingDueCount, dddCaseStats.comingDueCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(overdueCount, comingDueCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class dddCaseStats {\n");
    
    sb.append("    overdueCount: ").append(toIndentedString(overdueCount)).append("\n");
    sb.append("    comingDueCount: ").append(toIndentedString(comingDueCount)).append("\n");
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
