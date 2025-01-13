package tr.gov.tubitak.bilgem.yte.yte_demo_app.department.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageRequestDTO;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.SpecificationMapper;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department_;

/**
 * Spring Data JPA specifications for the Department entity.
 */
public class DepartmentSpecifications {

    public static Specification<Department> byPageRequestDTO(PageRequestDTO pageRequestDTO) {
        return SpecificationMapper.SpecificationBuilder.newBuilder().forCriteriaDTO(pageRequestDTO).build();
    }

    public static Specification<Department> byId(Long id) {
        return (
            (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get(Department_.id), id);
            }
        );
    }
}
