package com.assignment.OnlineExamService.models;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Result extends MandatoryFields{
    
    private BigDecimal score;
    
    private String email;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examiner_id", referencedColumnName = "id")
    private Examiner examiner;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Tests test;
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;
}
