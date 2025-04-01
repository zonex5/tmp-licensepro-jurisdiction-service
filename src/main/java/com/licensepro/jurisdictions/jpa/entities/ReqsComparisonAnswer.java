package com.licensepro.jurisdictions.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reqs_comparison_answers", schema = "jurisdictions")
@Data
@NoArgsConstructor
public class ReqsComparisonAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "jurisdiction_id", nullable = false)
  private Jurisdiction jurisdiction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", nullable = false)
  private ReqsComparisonQuestion question;

  private Boolean answer;
}
