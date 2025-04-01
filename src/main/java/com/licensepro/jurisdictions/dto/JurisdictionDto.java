package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.Jurisdiction;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JurisdictionDto {

  private String id;
  private String state;
  private String stateAbr;
  private String stateBoardUrl;
  private String renewalPageUrl;

  private List<JurisdictionVariantDto> variants;

  public static JurisdictionDto fromEntity(Jurisdiction jurisdiction) {
    return JurisdictionDto.builder()
        .id(jurisdiction.getId())
        .state(jurisdiction.getState())
        .stateAbr(jurisdiction.getStateAbr())
        .stateBoardUrl(jurisdiction.getStateBoardUrl())
        .renewalPageUrl(jurisdiction.getRenewalPageUrl())

        .variants(jurisdiction.getVariants().stream().map(JurisdictionVariantDto::fromEntity).collect(Collectors.toList()))
        .build();
  }
}
