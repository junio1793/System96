package com.abstractentity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GENRE")
@Entity
public class Genre extends AbstractEntidade{

    @Id
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    @SequenceGenerator(name = "ID_GENRE", sequenceName = "GEN_GENRE", initialValue = 100000, allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SongGenre> songGenre;
}
