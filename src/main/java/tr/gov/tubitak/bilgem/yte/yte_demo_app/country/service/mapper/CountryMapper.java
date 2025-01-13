package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.mapper;

import org.mapstruct.*;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.dto.CountryDTO;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {}
