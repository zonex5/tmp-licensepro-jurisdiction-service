package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.JurisdictionLink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JurisdictionLinkDto {
  private String id;
  private String jurisdictionId;
  private String url;
  private Integer position;

  public static JurisdictionLinkDto fromEntity(JurisdictionLink jurisdictionLink) {
    return JurisdictionLinkDto.builder()
        .id(jurisdictionLink.getId())
        .jurisdictionId(jurisdictionLink.getJurisdiction().getId())
        .url(jurisdictionLink.getUrl())
        .position(jurisdictionLink.getPosition())
        .build();
  }
}
