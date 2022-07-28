package entity.implementation.enums;

import java.io.Serializable;

public enum CaseType implements Serializable{
   
    FIRST("Parnicni"),
    SECOND("Vanparnicni"),
    THIRD("Izvrsni"),
    FOURTH("Prekrsajni"),
    FIFFTH("Krivicni"),
    ;
    private final String value;
    
    CaseType(String value){
        this.value = value; 
    }

    @Override
    public String toString() {
        return value;
    }

}
