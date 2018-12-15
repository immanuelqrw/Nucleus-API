package com.immanuelqrw.core.api.service

import com.fasterxml.jackson.module.kotlin.convertValue
import com.immanuelqrw.core.api.controller.FullyControllable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.repository.BaseRepository
import com.immanuelqrw.core.api.utility.Utility
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

/**
 * Abstract base service
 */
abstract class BaseService<T : BaseEntity>
@Autowired constructor(
    private val repository: BaseRepository<T>,
    private val searchService: SearchService<T>,
    private val classType: Class<T>
) : FullyControllable<T> {

    override fun find(id: Long): T {
        return repository.getOne(id)
    }

    override fun findAll(page: Pageable?, search: String?): Page<T> {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        return repository.findAll(searchSpecification, page)
    }

    override fun create(entity: T): T {
        return repository.save(entity)
    }

    override fun replace(id: Long, entity: T): T {
        val originalEntity: T = repository.getOne(id)
        entity.id = originalEntity.id
        return repository.save(entity)
    }

    override fun modify(id: Long, patchedFields: Map<String, Any>): T {
        val originalEntity: T = repository.getOne(id)

        val objectMapper = Utility.OBJECT_MAPPER
        val originalMap: Map<String, Any> = objectMapper.convertValue(originalEntity)
        val patchedMap: Map<String, Any> = originalMap.plus(patchedFields)

        val patchedEntity: T = objectMapper.convertValue(patchedMap, classType)

        return repository.save(patchedEntity)
    }

    private fun removeEntity(removableEntity: T?) {
        removableEntity?.let { _removableEntity ->
            _removableEntity.removedOn = LocalDateTime.now()
            repository.save(_removableEntity)
        }
    }

    override fun remove(id: Long) {
        val removableEntity: T = repository.getOne(id)
        removeEntity(removableEntity)
    }

    override fun removeAll(page: Pageable?, search: String?) {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        val removableEntities: Page<T> = repository.findAll(searchSpecification, page)
        removableEntities.forEach { removableEntity ->
            removeEntity(removableEntity)
        }
    }

}