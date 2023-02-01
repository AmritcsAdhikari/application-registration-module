package com.appreg.repository;

import com.appreg.entity.ApplicantDtl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRegRepository extends JpaRepository<ApplicantDtl, Integer> {
    Optional<ApplicantDtl> findBySsn(Long ssn);
}
