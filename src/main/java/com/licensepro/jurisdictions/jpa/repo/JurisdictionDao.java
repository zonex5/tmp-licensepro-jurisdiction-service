package com.licensepro.jurisdictions.jpa.repo;

import com.licensepro.jurisdictions.jpa.entities.Jurisdiction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JurisdictionDao extends JpaRepository<Jurisdiction, String> {

  @Query("""
          SELECT j FROM Jurisdiction j
          LEFT JOIN FETCH j.variants
          ORDER BY j.state
      """)
  List<Jurisdiction> findAllFetchVariants();

  @Query("""
          SELECT j FROM Jurisdiction j
          LEFT JOIN FETCH j.variants
          WHERE j.id = :jurisdictionId
      """)
  Optional<Jurisdiction> findByIdFetchVariants(String jurisdictionId);
}
