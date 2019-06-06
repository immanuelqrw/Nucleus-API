package com.immanuelqrw.core.api.service

import com.fasterxml.jackson.module.kotlin.convertValue
import com.immanuelqrw.core.api.FullySerialControllable
import com.immanuelqrw.core.api.repository.BaseRepository
import com.immanuelqrw.core.api.repository.BaseSerialRepository
import com.immanuelqrw.core.entity.SerialEntityable
import com.immanuelqrw.core.util.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

/**
 * Abstract base service
 */
abstract class BaseSerialService<T : SerialEntityable> : FullySerialControllable<T> {

    @Autowired
    private lateinit var repository: BaseSerialRepository<T>

    @Autowired
    private lateinit var searchService: SearchService<T>

    @Autowired
    private lateinit var classType: Class<T>

    override fun find(id: Long): T {
        return repository.getOne(id)
    }

    override fun findAllById(ids: Iterable<Long>): List<T> {
        return repository.findAllById(ids)
    }

    override fun findAll(): List<T> {
        return repository.findAll()
    }

    override fun findAll(search: String): List<T> {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        return repository.findAll(searchSpecification)
    }

    override fun findAll(page: Pageable, search: String?): Page<T> {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        return repository.findAll(searchSpecification, page)
    }

    override fun count(): Long {
        return repository.count()
    }

    override fun count(search: String): Long {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        return repository.count(searchSpecification)
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

        val objectMapper = Resource.OBJECT_MAPPER
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

    override fun removeAll(search: String?) {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        val removableEntities: Iterable<T> = repository.findAll(searchSpecification)
        removableEntities.forEach { removableEntity ->
            removeEntity(removableEntity)
        }
    }

}
