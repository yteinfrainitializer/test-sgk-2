package tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;
import lombok.Data;

@Data
public class DepartmentDTO implements Serializable {

    private Long id;

    @NotNull
    private String departmentName;
}
