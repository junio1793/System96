package com.abstractentity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SongGenreEmbeddable implements Serializable {

    @Column(name = "SONG_ID")
    private Integer songId;

    @Column(name = "GENRE_ID")
    private Integer genreId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(this == obj)) return false;

        SongGenreEmbeddable that = (SongGenreEmbeddable) obj;
        return Objects.equals(songId, that.songId)
                && Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, genreId);
    }
}
