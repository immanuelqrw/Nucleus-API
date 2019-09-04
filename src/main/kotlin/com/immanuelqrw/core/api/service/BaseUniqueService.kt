package com.immanuelqrw.core.api.service

import com.fasterxml.jackson.module.kotlin.convertValue
import com.immanuelqrw.core.api.FullyUniqueControllable
import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.core.entity.UniqueEntityable
import com.immanuelqrw.core.util.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import java.util.UUID

/**
 * Abstract base service
 */
abstract class BaseUniqueService<T : UniqueEntityable>(private val classType: Class<T>) : FullyUniqueControllable<T> {

    @Autowired
    private lateinit var repository: BaseUniqueRepository<T>

    @Autowired
    private lateinit var searchService: SearchService<T>

    override fun find(id: UUID): T {
        return repository.getOne(id)
    }

    override fun findAllById(ids: Iterable<UUID>): List<T> {
        return repository.findAllById(ids)
    }

    override fun findAll(page: Pageable?, search: String?): Iterable<T> {
        return search?.let { _search ->
            val searchSpecification: Specification<T>? = searchService.generateSpecification(_search)
            page?.let { _page ->
                repository.findAll(searchSpecification, _page)
            } ?: run {
                repository.findAll(searchSpecification)
            }
        } ?: run {
            repository.findAll()
        }
    }

    override fun count(search: String?): Long {
        return search?.let {
            val searchSpecification: Specification<T>? = searchService.generateSpecification(it)
            repository.count(searchSpecification)
        } ?: run {
            repository.count()
        }
    }

    override fun create(entity: T): T {
        return repository.save(entity)
    }

    override fun replace(id: UUID, entity: T): T {
        val originalEntity: T = repository.getOne(id)
        entity.id = originalEntity.id
        return repository.save(entity)
    }

    override fun modify(id: UUID, patchedFields: Map<String, Any>): T {
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

    override fun remove(id: UUID) {
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
