package com.abstractentity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SONG_GENRE")
public class SongGenre extends AbstractEntidade {

    @EmbeddedId
    private SongGenreEmbeddable id;

    @ManyToOne
    @MapsId("genreId")
    private Genre genre;

    @ManyToOne
    @MapsId("songId")
    @JoinColumn(name = "SONG_ID")
    private Song song;
}
