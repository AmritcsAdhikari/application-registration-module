package com.appreg.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicantDtlDto {
    private String name;
    private String email;
    private Long phone;
    private String gender;
    private LocalDate dob;
    private Long ssn;
}
