package tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.impl;

import static tr.gov.tubitak.bilgem.yte.yte_demo_app.country.repository.specifications.CountrySpecifications.*;

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
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.domain.Country_;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.repository.CountryRepository;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.CountryService;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.dto.CountryDTO;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.mapper.CountryMapper;
import tr.gov.tubitak.bilgem.yte.yte_demo_app.country.service.messages.CountryMessages;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final MessageContextHolder messageContextHolder;

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        log.debug("Request to save Country : {}", countryDTO);
        Country country = countryMapper.toEntity(countryDTO);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    @Override
    public CountryDTO update(CountryDTO countryDTO) {
        log.debug("Request to update Country : {}", countryDTO);
        Country country = countryMapper.toEntity(countryDTO);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    @Override
    public Optional<CountryDTO> partialUpdate(CountryDTO countryDTO) {
        log.debug("Request to partially update Country : {}", countryDTO);

        Optional<Country> country = countryRepository.findById(countryDTO.getId());
        messageContextHolder.addErrorMessageWhenBrFalse(CountryMessages.ERROR_COUNTRY_NOT_FOUND, country.isPresent(), countryDTO.getId());
        return country
            .map(existingCountry -> {
                countryMapper.partialUpdate(existingCountry, countryDTO);

                return existingCountry;
            })
            .map(countryRepository::save)
            .map(countryMapper::toDto);
    }

    @Override
    @ReadReplica
    @Transactional(readOnly = true)
    public Page<Country> findAll(PageRequestDTO pageRequestDTO) {
        log.debug("Request to get all Countries");
        Specification<Country> byPageRequestDTO = byPageRequestDTO(pageRequestDTO);

        PageRequest pageRequest = PageRequestDTO.PageRequestBuilder.of(pageRequestDTO).withSort(Country_.id).build();

        return countryRepository.findAll(byPageRequestDTO, pageRequest);
    }

    @Override
    @ReadReplica
    @Transactional(readOnly = true)
    public Optional<CountryDTO> findOne(Long id) {
        log.debug("Request to get Country : {}", id);
        Optional<Country> country = countryRepository.findOne(byId(id));
        messageContextHolder.addErrorMessageWhenBrFalse(CountryMessages.ERROR_COUNTRY_NOT_FOUND, country.isPresent(), id);
        return country.map(countryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Country : {}", id);
        countryRepository.deleteById(id);
    }
}
