package com.licensepro.jurisdictions.controllers;

import com.licensepro.jurisdictions.dto.JurisdictionDto;
import com.licensepro.jurisdictions.jpa.entities.Jurisdiction;
import com.licensepro.jurisdictions.jpa.repo.JurisdictionDao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("jurisdictions")
public class JurisdictionController {

  @Resource
  private JurisdictionDao jurisdictionDao;

  @GetMapping
  public ResponseEntity<List<JurisdictionDto>> findAll() {
    List<Jurisdiction> jurisdictions = jurisdictionDao.findAllFetchVariants();
    return ResponseEntity.ok(jurisdictions.stream().map(JurisdictionDto::fromEntity).collect(Collectors.toList()));
  }

  @GetMapping("{jurisdictionId}")
  public ResponseEntity<JurisdictionDto> findById(@PathVariable String jurisdictionId) {
    Optional<Jurisdiction> jurisdiction = jurisdictionDao.findByIdFetchVariants(jurisdictionId);

    return jurisdiction.map(value -> ResponseEntity.ok(JurisdictionDto.fromEntity(value)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
