package entity.implementation;

import entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CaseItem implements Entity{
    
    private static final String INSERT_ATTRIBUTES = "internal_number,official_number,case_id";
    private static final String TABLE_NAME = "case_item";
    
    int id;
    int internalNumber;
    int officialNumber;
    Case case1;
}
