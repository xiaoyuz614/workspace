package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MedicalInfo {
    //选中field可以generate constructor

    private long bfr;

    private long fmi;

    public MedicalInfo() {

    }

    public MedicalInfo(long bfr, long fmi) {
        this.bfr = bfr;
        this.fmi = fmi;
    }

}
