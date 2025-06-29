package com.abstractentity.service;

import com.abstractentity.model.AbstractEntidade;
import com.abstractentity.model.dtos.IAbstractDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface IAbstractIntegracaoService<ENTIDADE extends AbstractEntidade, ID extends Serializable, DTO extends IAbstractDTO> extends IAbstractServiceEntryPoint<ENTIDADE, DTO> {

    Class<ENTIDADE> getEntityClass();

    JpaRepository<ENTIDADE, ID> getRepository();

    List<DTO> findAll();

    List<DTO> findAll(Sort sort);

    Page<DTO> findAll(Pageable pageable);

    List<ENTIDADE> findAllEntidade();

    List<ENTIDADE> findAllEntidade(Sort sort);

    Page<ENTIDADE> findAllEntidade(Pageable pageable);

    DTO findByID(ID id);

    Object save(Object data);

    Object saveEntidade(DTO dto);
}
