package com.licensepro.jurisdictions.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reqs_comparison_questions", schema = "jurisdictions")
@Data
@NoArgsConstructor
public class ReqsComparisonQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private String id;

  private String question;

  private Integer position;
}
