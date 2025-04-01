package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.JurisdictionNotificationChecklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JurisdictionNotificationChecklistDto {
  private String id;
  private String jurisdictionId;
  private String text;

  public static JurisdictionNotificationChecklistDto fromEntity(JurisdictionNotificationChecklist checklist) {
    return JurisdictionNotificationChecklistDto.builder()
        .id(checklist.getId())
        .jurisdictionId(checklist.getJurisdiction().getId())
        .text(checklist.getText())
        .build();
  }
}
