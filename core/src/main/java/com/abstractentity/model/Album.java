package com.abstractentity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALBUM")
public class Album {

    @Id
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    @SequenceGenerator(name = "ID_ALBUM", sequenceName = "GEN_ALBUM", initialValue = 100000, allocationSize = 1)
    private Integer id;

    @Column
    private String title;

    @Column
    private Integer releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ARTIST", referencedColumnName = "ID")
    private Artist artist;

    @Column(name = "song")
    @OneToMany(mappedBy = "album")
    private Set<Song> songs = new HashSet<>();

}
