package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.repository.PagingAndSortingBaseRepository;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country;

@Repository
public interface CountryRepository extends PagingAndSortingBaseRepository<Country, Long>, JpaSpecificationExecutor<Country> {}
