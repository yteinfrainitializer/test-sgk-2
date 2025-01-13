package tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service;

import static tr.gov.tubitak.bilgem.yte.yte_demo_app.department.repository.specifications.DepartmentSpecifications.*;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageRequestDTO;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.configuration.annotation.ReadReplica;
import tr.gov.tubitak.bilgem.yte.infra.ytemessagecontext.message.messagecontext.MessageContextHolder;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department_;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.repository.DepartmentRepository;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.dto.DepartmentDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.mapper.DepartmentMapper;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.messages.DepartmentMessages;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final MessageContextHolder messageContextHolder;

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        log.debug("Request to save Department : {}", departmentDTO);
        Department department = departmentMapper.toEntity(departmentDTO);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        log.debug("Request to update Department : {}", departmentDTO);
        Department department = departmentMapper.toEntity(departmentDTO);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public Optional<DepartmentDTO> partialUpdate(DepartmentDTO departmentDTO) {
        log.debug("Request to partially update Department : {}", departmentDTO);

        Optional<Department> department = departmentRepository.findById(departmentDTO.getId());
        messageContextHolder.addErrorMessageWhenBrFalse(DepartmentMessages.ERROR_DEPARTMENT_NOT_FOUND, department.isPresent(), departmentDTO.getId());
        return department
            .map(existingDepartment -> {
                departmentMapper.partialUpdate(existingDepartment, departmentDTO);

                return existingDepartment;
            })
            .map(departmentRepository::save)
            .map(departmentMapper::toDto);
    }

    @ReadReplica
    @Transactional(readOnly = true)
    public Page<Department> findAll(PageRequestDTO pageRequestDTO) {
        log.debug("Request to get all Departments");
        Specification<Department> byPageRequestDTO = byPageRequestDTO(pageRequestDTO);

        PageRequest pageRequest = PageRequestDTO.PageRequestBuilder.of(pageRequestDTO).withSort(Department_.id).build();

        return departmentRepository.findAll(byPageRequestDTO, pageRequest);
    }

    @ReadReplica
    @Transactional(readOnly = true)
    public Optional<DepartmentDTO> findOne(Long id) {
        log.debug("Request to get Department : {}", id);
        Optional<Department> department = departmentRepository.findOne(byId(id));
        messageContextHolder.addErrorMessageWhenBrFalse(DepartmentMessages.ERROR_DEPARTMENT_NOT_FOUND, department.isPresent(), id);
        return department.map(departmentMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        departmentRepository.deleteById(id);
    }
}
