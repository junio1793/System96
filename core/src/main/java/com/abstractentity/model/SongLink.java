package com.abstractentity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SongLink {

    @Id
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    @SequenceGenerator(name = "ID_SONG_LINK", sequenceName = "GEN_SONG_LINK", initialValue = 100000, allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_SONG")
    private Song song;

    @Column
    private String plataform;

    @Column
    private String url;
}
