package com.licensepro.jurisdictions.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jurisdiction_variants", schema = "jurisdictions")
@Data
@NoArgsConstructor
public class JurisdictionVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "jurisdiction_id", nullable = false)
  private Jurisdiction jurisdiction;

  private String variant;
}
