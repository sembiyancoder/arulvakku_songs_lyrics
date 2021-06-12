package com.arulvakku.lyrics.app.ui.view.home.song.cache

import androidx.room.*
import com.arulvakku.lyrics.app.ui.view.favourite.cache.Playlist
import com.arulvakku.lyrics.app.ui.view.favourite.cache.PlaylistSongCrossRef
import com.arulvakku.lyrics.app.ui.view.favourite.cache.PlaylistWithSongs
import com.arulvakku.lyrics.app.ui.view.favourite.cache.SongWithPlaylists


@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongList(entity: List<SongCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(data: Playlist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavouriteSong(playlistSongCrossRef: PlaylistSongCrossRef): Long

    @Delete
    suspend fun removeFavouriteSong(playlistSongCrossRef: PlaylistSongCrossRef): Int

    @Query("SELECT EXISTS (SELECT * FROM playlist_song_cross_ref WHERE playlist_id=1 AND song_id =:songId)")
    suspend fun isFavouriteSong(songId: Int):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongSingleItem(entity: SongCacheEntity)

    @Query("SELECT * FROM songs ORDER BY LOWER(title) ASC")
    suspend fun getSongList(): List<SongCacheEntity>

    @Query("SELECT COUNT(*) from songs")
    suspend fun getSongCount(): Long

  /*  @Transaction
    @Query("SELECT * FROM songs")
    suspend fun getSongsWithPlaylists(): List<SongWithPlaylists>

    @Transaction
    @Query("SELECT * FROM Playlist")
    suspend fun getPlaylistsWithSongs(): List<PlaylistWithSongs>*/

    @Transaction
    @Query("SELECT * FROM Playlist WHERE playlist_id = 1")
    suspend fun getFavouriteSongs(): PlaylistWithSongs

    @Transaction
    @Query("SELECT * FROM songs WHERE category_id = :id ORDER BY LOWER(title) ASC")
    suspend fun getSongsWithPlaylistId(id: Int): List<SongWithPlaylists>

    @Query("SELECT * FROM songs WHERE category_id = :id ORDER BY LOWER(title) ASC")
    suspend fun getSongListByCategory(id: Int): List<SongCacheEntity>

}