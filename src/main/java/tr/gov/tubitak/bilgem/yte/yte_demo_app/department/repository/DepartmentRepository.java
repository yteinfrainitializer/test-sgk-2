package tr.gov.tubitak.bilgem.yte.yte_demo_app.department.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.repository.PagingAndSortingBaseRepository;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department;

@Repository
public interface DepartmentRepository extends PagingAndSortingBaseRepository<Department, Long>, JpaSpecificationExecutor<Department> {}
