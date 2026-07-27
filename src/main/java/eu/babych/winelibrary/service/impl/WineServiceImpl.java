package eu.babych.winelibrary.service.impl;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.mapper.WineMapper;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.repository.WineRepository;
import eu.babych.winelibrary.service.WineService;
import eu.babych.winelibrary.service.winefilter.WineSearchSpecificationBuilder;
import java.util.List;
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
    public List<WineResponseDto> search(WineFilterRequestDto requestDto) {

        Specification<Wine> specification =
                specificationBuilder.build(requestDto);

        return repository.findAll(specification).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<WineResponseDto> findAll(Pageable pageable) {
        Page<Wine> wine = repository.findAll(pageable);

        return wine.stream()
                .map(mapper::toDto)
                .toList();
    }
}
