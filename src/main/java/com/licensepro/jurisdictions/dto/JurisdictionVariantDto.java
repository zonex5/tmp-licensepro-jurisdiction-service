package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.JurisdictionVariant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JurisdictionVariantDto {
  private String id;
  private String jurisdictionId;
  private String variant;

  public static JurisdictionVariantDto fromEntity(JurisdictionVariant jurisdictionVariant) {
    return JurisdictionVariantDto.builder()
        .id(jurisdictionVariant.getId())
        .jurisdictionId(jurisdictionVariant.getJurisdiction().getId())
        .variant(jurisdictionVariant.getVariant())
        .build();
  }
}
