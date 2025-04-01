package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.ReqsComparisonAnswer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReqsComparisonAnswerDto {
  private String id;
  private String jurisdictionId;
  private String questionId;
  private Boolean answer;

  public static ReqsComparisonAnswerDto fromEntity(ReqsComparisonAnswer answer) {
    return ReqsComparisonAnswerDto.builder()
        .id(answer.getId())
        .jurisdictionId(answer.getJurisdiction().getId())
        .questionId(answer.getQuestion().getId())
        .answer(answer.getAnswer())
        .build();
  }
}
