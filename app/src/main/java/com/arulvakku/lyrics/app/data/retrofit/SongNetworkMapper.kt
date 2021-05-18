package com.arulvakku.lyrics.app.data.retrofit

import com.arulvakku.lyrics.app.activities.home.Song
import com.arulvakku.lyrics.app.utilities.EntityMapper
import javax.inject.Inject

class SongNetworkMapper
@Inject
constructor() :
    EntityMapper<SongNetworkEntity, Song> {

    override fun mapFromEntity(entity: SongNetworkEntity): Song {
        return Song(
            songId = entity.songId,
            title = entity.title ?: "",
            category = entity.category ?: "",
            categoryId = entity.categoryId,
            song = entity.song ?: ""
        )
    }

    override fun mapToEntity(domainModel: Song): SongNetworkEntity {
        return SongNetworkEntity(
            songId = domainModel.songId,
            title = domainModel.title,
            category = domainModel.category,
            categoryId = domainModel.categoryId,
            song = domainModel.song
        )
    }


    fun mapFromEntityList(entities: List<SongNetworkEntity>): List<Song> {
        return entities.map { mapFromEntity(it) }
    }

}





















