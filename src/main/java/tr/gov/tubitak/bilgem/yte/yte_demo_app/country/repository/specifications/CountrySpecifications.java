package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageRequestDTO;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.SpecificationMapper;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country_;

/**
 * Spring Data JPA specifications for the Country entity.
 */
public class CountrySpecifications {

    public static Specification<Country> byPageRequestDTO(PageRequestDTO pageRequestDTO) {
        return SpecificationMapper.SpecificationBuilder.newBuilder().forCriteriaDTO(pageRequestDTO).build();
    }

    public static Specification<Country> byId(Long id) {
        return (
            (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get(Country_.id), id);
            }
        );
    }
}
