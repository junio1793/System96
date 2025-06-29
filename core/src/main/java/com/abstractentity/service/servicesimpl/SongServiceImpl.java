package com.abstractentity.service.servicesimpl;

import com.abstractentity.model.Song;
import com.abstractentity.model.dtos.EntidadeDtos;
import com.abstractentity.service.AbstractServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("songService")
public class SongServiceImpl extends AbstractServiceImpl<Song, Long, EntidadeDtos> {

    private ClassLoader beanClassLoader;
    private String beanName;
    private ApplicationContext applicationContext;

    public SongServiceImpl() {
        super();
    }

    @Override
    public Class<Song> getEntityClass() {
        return null;
    }

    @Override
    public JpaRepository<Song, Long> getRepository() {
        return null;
    }

    @Override
    public EntidadeDtos toDto(Song entidade) {
        return null;
    }

    @Override
    public Song toEntity(EntidadeDtos dto) {
        return null;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
