package com.abstractentity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song extends AbstractEntidade {

    @Id
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    @SequenceGenerator(name = "ID_SONG", sequenceName = "GEN_SONG", initialValue = 100000, allocationSize = 1)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SongGenre> genres;

    @ManyToOne
    @JoinColumn(name = "ID_ARTISTA", referencedColumnName = "ID")
    private Artist artist;

    @OneToMany(mappedBy = "song")
    private Set<SongLink> setLinks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ID_ALBUM", referencedColumnName = "ID")
    private Album album;
}
