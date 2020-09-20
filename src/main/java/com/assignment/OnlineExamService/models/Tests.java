package com.assignment.OnlineExamService.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@AllArgsConstructor
@NoArgsConstructor
public class Tests extends MandatoryFields{
    
    private String testName;
    
    @JsonIgnore
    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Questions> questions;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examiner;
    
    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<Result> results;
    
    @JsonIgnore
    @ManyToMany(targetEntity = Candidate.class, mappedBy = "tests", cascade = CascadeType.ALL)
    private List<Candidate> candidates;
    
    @OneToOne(mappedBy = "test")
    private CandidateExams candidateExams;
    
}
