package com.abstractentity.service;

import com.abstractentity.model.AbstractEntidade;
import com.abstractentity.model.dtos.IAbstractDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractServiceImpl<ENTIDADE extends AbstractEntidade, ID extends Serializable, DTO extends IAbstractDTO>
        implements IAbstractIntegracaoService<ENTIDADE, ID, DTO>, BeanNameAware, ApplicationContextAware, BeanClassLoaderAware {

    private final Class<DTO> dtoClass;

    @Autowired
    protected TransactionTemplate transactionTemplate;

    protected AbstractServiceImpl(Class<DTO> dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public Object save(Object data) {
        DTO dto = newInstanceOfDtoFromObject(data);

        ENTIDADE entidade = saveEntidade(dto);

        return entidade;
    }

    @Override
    public ENTIDADE saveEntidade(DTO dto) {
        return null;
    }

    @Override
    public DTO update(ID id, DTO dto) {
        Optional<ENTIDADE> optional = getRepository().findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Entidade não encontrada: " + id);
        }

        ENTIDADE entidadeAtual = optional.get();
        BeanUtils.copyProperties(dto, entidadeAtual);
        beforeSave(dto, entidadeAtual, null);
        ENTIDADE updated = getRepository().save(entidadeAtual);
        afterSave(dto, updated, null);
        return toDto(updated);
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public DTO findById(ID id) {
        return getRepository().findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<DTO> findAll() {
        return getRepository().findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DTO> findAll(Sort sort) {
        return getRepository().findAll(sort).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DTO> findAll(Pageable pageable) {
        return getRepository().findAll(pageable).map(this::toDto);
    }

    public DTO newInstanceOfDtoFromObject(Object obj) {
        Object objOfDTOValues = new Object();

        if (obj instanceof Map) {
            objOfDTOValues = obj;
        }

        if (!(objOfDTOValues instanceof Map)) {
            throw new RuntimeException("DTO Class " + dtoClass.getName() + " invalid constructor for new instance with " + objOfDTOValues.getClass().getName());
        }

        Map mapOfDTOValues = (Map<?, ?>) objOfDTOValues;

        try {
            return dtoClass.getConstructor(Map.class).newInstance(mapOfDTOValues);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ENTIDADE dtoToEntidade(DTO dto, ENTIDADE entidade) {
        entidade = getRepository().findById(dto.getAttr("id")).orElse(null);
        return entidade;
    }
}
