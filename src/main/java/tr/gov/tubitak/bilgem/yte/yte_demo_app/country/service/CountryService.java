package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageRequestDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.dto.CountryDTO;

public interface CountryService {
    CountryDTO save(CountryDTO countryDTO);

    CountryDTO update(CountryDTO countryDTO);

    Optional<CountryDTO> partialUpdate(CountryDTO countryDTO);

    Page<Country> findAll(PageRequestDTO pageRequestDTO);

    Optional<CountryDTO> findOne(Long id);

    void delete(Long id);
}
