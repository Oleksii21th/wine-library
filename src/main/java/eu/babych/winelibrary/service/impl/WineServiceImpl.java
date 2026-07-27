package eu.babych.winelibrary.service.impl;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
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
    public Page<WineResponseDto> findAll(WineFilterRequestDto requestDto,
                                         Pageable pageable) {
        Specification<Wine> specification =
                specificationBuilder.build(requestDto);

        Page<Wine> wine = repository.findAll(specification, pageable);

        return wine.map(mapper::toDto);
    }
}
