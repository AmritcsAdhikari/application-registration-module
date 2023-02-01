package com.appreg.service;

import com.appreg.dto.ApplicantDtlDto;
import com.appreg.entity.ApplicantDtl;

public interface ApplicationRegistrationService {
    public String createApplication(ApplicantDtlDto applicantDtl);
    public boolean isApplicantRhodeIslandResident(Long ssn);
}
