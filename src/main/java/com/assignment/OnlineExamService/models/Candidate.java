package com.assignment.OnlineExamService.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends MandatoryFields {
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @ManyToMany(targetEntity = Tests.class,cascade = CascadeType.ALL )
    private List<Tests> tests;
    
    @OneToOne(mappedBy = "candidate")
    private CandidateExams candidateExams;
    
    @OneToMany(mappedBy = "candidate")
    private List<Result> results;
}
