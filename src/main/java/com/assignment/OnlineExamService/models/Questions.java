package com.assignment.OnlineExamService.models;

import java.util.HashMap;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

/**
 * @author shubham sharma
 *         <p>
 *         19/09/20
 */
@Data
@Entity
public class Questions extends MandatoryFields {
    
    
    private String question;
    
    private HashMap<Integer, String> options;
    
    private Integer answer;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Tests test;
}
