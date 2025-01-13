package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.web.rest;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageRequestDTO;
import tr.gov.tubitak.bilgem.yte.infra.data.jpa.PageResultDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.CountryService;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.dto.CountryDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.mapper.CountryMapper;

@Slf4j
@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/countries")
public class CountryResource {

    private final CountryService countryService;

    private final CountryMapper countryMapper;

    @PostMapping("")
    public CountryDTO createCountry(@RequestBody CountryDTO countryDTO) {
        log.debug("REST request to save Country : {}", countryDTO);
        return countryService.save(countryDTO);
    }

    @PutMapping("/{id}")
    public CountryDTO updateCountry(@PathVariable(value = "id", required = false) final Long id, @RequestBody CountryDTO countryDTO) {
        log.debug("REST request to update Country : {}, {}", id, countryDTO);
        return countryService.update(countryDTO);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public CountryDTO partialUpdateCountry(@PathVariable(value = "id", required = false) final Long id, @RequestBody CountryDTO countryDTO) {
        log.debug("REST request to partial update Country partially : {}, {}", id, countryDTO);

        Optional<CountryDTO> result = countryService.partialUpdate(countryDTO);

        return result.orElse(null);
    }

    @PostMapping("/getAll")
    public PageResultDTO getAllCountries(@RequestBody PageRequestDTO pageRequestDTO) {
        log.debug("REST request to get a page of Countries");
        Page<Country> page = countryService.findAll(pageRequestDTO);
        return new PageResultDTO(page, countryMapper::toDto);
    }

    @GetMapping("/{id}")
    public CountryDTO getCountry(@PathVariable("id") Long id) {
        log.debug("REST request to get Country : {}", id);
        Optional<CountryDTO> countryDTO = countryService.findOne(id);
        return countryDTO.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") Long id) {
        log.debug("REST request to delete Country : {}", id);
        countryService.delete(id);
    }
}
