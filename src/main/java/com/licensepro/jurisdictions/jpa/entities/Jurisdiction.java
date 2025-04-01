package com.licensepro.jurisdictions.jpa.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jurisdictions", schema = "jurisdictions")
@Data
@NoArgsConstructor
public class Jurisdiction {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private String id;

  private String state;

  @Column(name = "state_abr")
  private String stateAbr;

  @Column(name = "state_board_url")
  private String stateBoardUrl;

  @Column(name = "renewal_page_url")
  private String renewalPageUrl;

  @OneToMany(mappedBy = "jurisdiction")
  private Set<JurisdictionVariant> variants;
}
