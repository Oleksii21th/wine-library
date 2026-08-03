package eu.babych.winelibrary.service.impl;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import eu.babych.winelibrary.exception.notfound.WineNotFoundException;
import eu.babych.winelibrary.mapper.WineMapper;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.repository.WineRepository;
import eu.babych.winelibrary.service.WineService;
import eu.babych.winelibrary.service.winefilter.WineSearchSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class WineServiceImpl implements WineService {
    private final WineMapper mapper;
    private final WineRepository repository;
    private final WineSearchSpecificationBuilder<Wine> specificationBuilder;

    public WineServiceImpl(
            WineMapper mapper,
            WineRepository repository,
            WineSearchSpecificationBuilder<Wine> specificationBuilder) {

        this.mapper = mapper;
        this.repository = repository;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public WineResponseDto findById(Long id) {
        Wine wine = repository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));

        return mapper.toDto(wine);
    }

    @Override
    public Page<WineResponseDto> findAll(WineFilterRequestDto requestDto,
                                         Pageable pageable) {
        Specification<Wine> specification =
                specificationBuilder.buildFilter(requestDto);

        return findAllBySpecification(specification, pageable);
    }

    @Override
    public Page<WineResponseDto> search(WineSearchRequestDto searchDto, Pageable pageable) {
        Specification<Wine> specification =
                specificationBuilder.buildSearch(searchDto);

        return findAllBySpecification(specification, pageable);
    }

    private Page<WineResponseDto> findAllBySpecification(Specification<Wine> specification,
                                                         Pageable pageable) {
        return repository.findAll(specification, pageable)
                .map(mapper::toDto);
    }
}
