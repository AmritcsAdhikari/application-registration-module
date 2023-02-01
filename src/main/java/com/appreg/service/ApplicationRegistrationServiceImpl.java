package com.appreg.service;

import com.appreg.dto.ApplicantDtlDto;
import com.appreg.entity.ApplicantDtl;
import com.appreg.repository.ApplicantRegRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.util.Optional;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService{

    private static final String baseUri = "http://ssawebapi-env.eba-k88bsahp.ap-south-1.elasticbeanstalk.com/{ssn}";
    @Autowired
    private ApplicantRegRepository repo;
    @Override
    public String createApplication(ApplicantDtlDto applicantDtlDto) {

        /*System.out.println("Data received from the client application: ");
        System.out.println(applicantDtlDto.getName() + "\n" +
                applicantDtlDto.getEmail() + "\n" +
                applicantDtlDto.getGender() + "\n" +
                applicantDtlDto.getDob() + "\n" +
                applicantDtlDto.getPhone() + "\n" +
                applicantDtlDto.getSsn() + "\n"

        );
        System.out.println("::::::::::::::::::::::::::::::::::::::::::: ");*/

        Optional<ApplicantDtl> application = repo.findBySsn(applicantDtlDto.getSsn());

        if (application.isPresent()) {
            return "Application has been already submitted. Application Id - " + application.get().getApplicationId();
        } else {
            boolean result = isApplicantRhodeIslandResident(applicantDtlDto.getSsn());

            // Register application only if the applicant is Rhode Island Resident
            if (result) {
                //copy dto properties to entity and then perform save operation
                ApplicantDtl entity = new ApplicantDtl();
                BeanUtils.copyProperties(applicantDtlDto,entity);
                repo.save(entity);
                return "Application Submitted. Application Id - " + entity.getApplicationId();
            }
            return "Application Registration Failed. Applicant does not belong to the state of Rhode Island";
        }

    }
    @Override
    public boolean isApplicantRhodeIslandResident(Long ssn) {
        WebClient webClient = WebClient.create();
        String response = null;

        try{
           response = webClient.get().uri(baseUri,ssn).retrieve().bodyToMono(String.class).block();
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("EXCEPTION OCCURED** - ** COULD NOT MAKE EXTERNAL API CALL AS IT MAY NOT BE AVAILABLE"+ exception.getMessage());
        }

        return "Rhode Island".equals(response);
    }
}
