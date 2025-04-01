package com.licensepro.jurisdictions.dto;

import com.licensepro.jurisdictions.jpa.entities.ReqsComparisonQuestion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReqsComparisonQuestionDto {
  private String id;
  private String question;
  private Integer position;

  public static ReqsComparisonQuestionDto fromEntity(ReqsComparisonQuestion question) {
    return ReqsComparisonQuestionDto.builder()
        .id(question.getId())
        .question(question.getQuestion())
        .position(question.getPosition())
        .build();
  }
}
