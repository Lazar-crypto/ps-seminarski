package entity.implementation;

import entity.Entity;
import entity.implementation.enums.CaseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class Case implements Entity{
    
    private static final String INSERT_ATTRIBUTES = "name,problem,recordDate,user_id,client_id,case_type_id";
    private static final String TABLE_NAME = "case";
    
    private int id;
    private String name;
    private String problem;
    private LocalDate recordDate;
    private User user;
    private Client client;
    private CaseType caseType;
    
}
