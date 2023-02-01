package com.appreg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Table(name = "APPLICATION_REGISTRATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDtl {

    @Id
    @GeneratedValue
    private Integer applicationId;
    private String name;
    private String email;
    private Long phone;
    private String gender;
    private LocalDate dob;
    private Long ssn;

    @CreationTimestamp
    private LocalDate createdTimestamp;

    @UpdateTimestamp
    private LocalDate updatedTimestamp;
}
