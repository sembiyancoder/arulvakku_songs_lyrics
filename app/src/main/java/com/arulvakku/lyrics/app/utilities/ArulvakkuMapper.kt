package com.arulvakku.lyrics.app.utilities

interface ArulvakkuMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
}