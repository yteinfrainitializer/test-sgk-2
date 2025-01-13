package tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.mapper;

import org.mapstruct.*;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.dto.DepartmentDTO;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {}
