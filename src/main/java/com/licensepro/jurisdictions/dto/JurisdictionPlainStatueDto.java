package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.JurisdictionPlainStatue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JurisdictionPlainStatueDto {
  private String id;
  private String jurisdictionId;
  private String content;

  public static JurisdictionPlainStatueDto fromEntity(JurisdictionPlainStatue plainStatue) {
    return JurisdictionPlainStatueDto.builder()
        .id(plainStatue.getId())
        .jurisdictionId(plainStatue.getJurisdiction().getId())
        .content(plainStatue.getContent())
        .build();
  }
}
