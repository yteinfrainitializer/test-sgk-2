package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CountryDTO implements Serializable {

    private Long id;

    private String countryName;
}
