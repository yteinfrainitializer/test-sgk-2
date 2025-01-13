package tr.gov.tubitak.bilgem.yte.yte_demo_app.department.web.rest;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageRequestDTO;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageResultDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.domain.Department;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.DepartmentService;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.dto.DepartmentDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.department.service.mapper.DepartmentMapper;

@Slf4j
@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/departments")
public class DepartmentResource {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    @PostMapping("")
    public DepartmentDTO createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        log.debug("REST request to save Department : {}", departmentDTO);
        return departmentService.save(departmentDTO);
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody DepartmentDTO departmentDTO) {
        log.debug("REST request to update Department : {}, {}", id, departmentDTO);
        return departmentService.update(departmentDTO);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public DepartmentDTO partialUpdateDepartment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DepartmentDTO departmentDTO
    ) {
        log.debug("REST request to partial update Department partially : {}, {}", id, departmentDTO);

        Optional<DepartmentDTO> result = departmentService.partialUpdate(departmentDTO);

        return result.orElse(null);
    }

    @PostMapping("/getAll")
    public PageResultDTO getAllDepartments(@RequestBody PageRequestDTO pageRequestDTO) {
        log.debug("REST request to get a page of Departments");
        Page<Department> page = departmentService.findAll(pageRequestDTO);
        return new PageResultDTO(page, departmentMapper::toDto);
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartment(@PathVariable("id") Long id) {
        log.debug("REST request to get Department : {}", id);
        Optional<DepartmentDTO> departmentDTO = departmentService.findOne(id);
        return departmentDTO.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        log.debug("REST request to delete Department : {}", id);
        departmentService.delete(id);
    }
}
