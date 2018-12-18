package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.FullyControllable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.core.api.utility.Utility.DEFAULT_PAGE_SIZE
import com.immanuelqrw.core.api.utility.Utility.DEFAULT_SORT_FIELD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * Abstract controller class
 */
abstract class BaseController<T : BaseEntity>
@Autowired constructor(
    private val service: BaseService<T>
) : FullyControllable<T> {

    @GetMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun find(@PathVariable("id") id: Long): T {
        return service.find(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [DEFAULT_SORT_FIELD])
        page: Pageable,
        @RequestParam("search")
        search: String?
    ): Page<T> {
        return service.findAll(page, search)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun create(@RequestBody entity: T): T {
        return service.create(entity)
    }

    @PutMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun replace(@PathVariable("id") id: Long, @RequestBody entity: T): T {
        return service.replace(id, entity)
    }

    @PatchMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun modify(@PathVariable("id") id: Long, @RequestBody patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }

    @DeleteMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun remove(@PathVariable("id") id: Long) {
        return service.remove(id)
    }

    @DeleteMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun removeAll(
        @RequestParam("page")
        @PageableDefault(size = DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [DEFAULT_SORT_FIELD])
        page: Pageable,
        @RequestParam("search")
        search: String?
    ) {
        return service.removeAll(page, search)
    }
}