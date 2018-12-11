package com.immanuelqrw.nucleus.core.api.controller

import com.fasterxml.jackson.module.kotlin.convertValue
import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import com.immanuelqrw.nucleus.core.api.repository.BaseRepository
import com.immanuelqrw.nucleus.core.api.service.SearchService
import com.immanuelqrw.nucleus.core.api.utility.Utility
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.core.GenericTypeResolver

/**
 * Abstract controller class
 */
abstract class BaseController<T : BaseEntity> : FullyControllable<T> {

    var classType: Class<T> = GenericTypeResolver.resolveTypeArgument(javaClass, BaseEntity::class.java) as Class<T>

    abstract val repository: BaseRepository<T>

    abstract val searchService: SearchService<T>

    @ResponseBody
    override fun find(id: Long): T {
        return repository.getOne(id)
    }

    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = 100)
        @SortDefault(sort = arrayOf("id"))
        page: Pageable,
        @RequestParam("search")
        search: String
    ): Page<T> {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        return repository.findAll(searchSpecification, page)
    }

    override fun create(@RequestBody entity: T): T {
        return repository.save(entity)
    }

    override fun replace(id: Long, @RequestBody entity: T): T {
        entity.id = id
        return repository.save(entity)
    }

    override fun modify(id: Long, @RequestBody patches: Map<String, Any>): T {
        val originalEntity: T = repository.getOne(id)

        val mapper = Utility.MAPPER
        val originalMap: Map<String, Any> = mapper.convertValue(originalEntity)
        val patchedMap: Map<String, Any> = originalMap.plus(patches)

        val patchedEntity: T = mapper.convertValue(patchedMap, classType)

        return repository.save(patchedEntity)
    }


    override fun remove(id: Long) {
        return repository.deleteById(id)
    }

    override fun removeAll(
        @RequestParam("page")
        @PageableDefault(size = 100)
        @SortDefault(sort = ["id"])
        page: Pageable,
        @RequestParam("search")
        search: String
    ) {
        val searchSpecification: Specification<T>? = searchService.generateSpecification(search)
        val removableEntities: Page<T> = repository.findAll(searchSpecification, page)
        return repository.deleteAll(removableEntities)
    }
}