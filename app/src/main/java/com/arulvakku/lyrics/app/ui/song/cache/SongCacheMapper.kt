package com.arulvakku.lyrics.app.ui.song.cache

import com.arulvakku.lyrics.app.ui.song.model.Song
import com.arulvakku.lyrics.app.ui.song.cache.entities.SongCacheEntity
import com.arulvakku.lyrics.app.utilities.EntityMapper
import javax.inject.Inject

/**
 *This [SongCacheMapper]  will convert from [SongCacheEntity] to [Song]
 */
class SongCacheMapper
@Inject
constructor() :
    EntityMapper<SongCacheEntity, Song> {

    override fun mapFromEntity(entity: SongCacheEntity): Song {
        return Song(
            songId = entity.songId,
            title = entity.title,
            category = entity.category,
            categoryId = entity.categoryId,
            song = entity.song
        )
    }

    override fun mapToEntity(domainModel: Song): SongCacheEntity {
        return SongCacheEntity(
            songId = domainModel.songId,
            title = domainModel.title,
            category = domainModel.category,
            categoryId = domainModel.categoryId,
            song = domainModel.song
        )
    }

    fun mapFromEntityList(entities: List<SongCacheEntity>): List<Song> {
        return entities.map { mapFromEntity(it) }
    }
}











