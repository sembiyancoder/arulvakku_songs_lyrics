package com.arulvakku.lyrics.app.ui.commonmapper

interface ArulvakkuMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
}