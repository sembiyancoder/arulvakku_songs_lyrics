package com.arulvakku.lyrics.app.ui.view.home.song.cache

import androidx.room.*
import com.example.many_to_many.data.room.entities.Playlist
import com.example.many_to_many.data.room.entities.PlaylistSongCrossRef
import com.example.many_to_many.data.room.entities.PlaylistWithSongs
import com.example.many_to_many.data.room.entities.SongWithPlaylists
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongList(entity: List<SongCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(data: Playlist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelation(playlistSongCrossRef: PlaylistSongCrossRef):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongSingleItem(entity: SongCacheEntity)

    @Query("SELECT * FROM songs ORDER BY LOWER(sTitle) ASC")
    suspend fun getSongList(): List<SongCacheEntity>

    @Query("SELECT COUNT(*) from songs")
    suspend fun getSongCount(): Long

    @Transaction
    @Query("SELECT * FROM songs")
    suspend fun getSongsWithPlaylists(): List<SongWithPlaylists>

    @Transaction
    @Query("SELECT * FROM Playlist")
    suspend fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM Playlist WHERE playlistId = 1")
    suspend fun getFavouriteSongs(): PlaylistWithSongs

    @Transaction
    @Query("SELECT * FROM songs WHERE sCategoryId = :id ORDER BY LOWER(sTitle) ASC")
    suspend fun getSongsWithPlaylistId(id: Int): List<SongWithPlaylists>

    @Query("SELECT * FROM songs WHERE sCategoryId = :id ORDER BY LOWER(sTitle) ASC")
    suspend fun getSongListByCategory(id: Int): List<SongCacheEntity>

}