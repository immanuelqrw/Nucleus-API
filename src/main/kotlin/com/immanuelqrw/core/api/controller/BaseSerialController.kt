package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.FullySerialControllable
import com.immanuelqrw.core.api.service.BaseSerialService
import com.immanuelqrw.core.api.utility.Utility.DEFAULT_PAGE_SIZE
import com.immanuelqrw.core.api.utility.Utility.DEFAULT_SORT_FIELD
import com.immanuelqrw.core.entity.SerialEntityable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import javax.persistence.EntityNotFoundException
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

/**
 * Abstract controller class
 */
abstract class BaseSerialController<T : SerialEntityable> : FullySerialControllable<T> {

    @Autowired
    private lateinit var service: BaseSerialService<T>

    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun find(@PathVariable("id") id: Long): T {
        logger.debug { "ID: $id" }

        return service.find(id)
    }

    override fun findAllById(ids: Iterable<Long>): List<T> {
        TODO("not implemented")
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [DEFAULT_SORT_FIELD])
        page: Pageable?,
        @RequestParam("search")
        search: String?
    ): Iterable<T> {
        logger.debug { "Search Query: $search" }

        return service.findAll(page, search)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAllActive(
        @RequestParam("page")
        @PageableDefault(size = DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [DEFAULT_SORT_FIELD])
        page: Pageable?,
        @RequestParam("search")
        search: String?
    ): Iterable<T> {
        logger.debug { "Search Query: $search" }

        return service.findAllActive(page, search)
    }

    @GetMapping(path = ["/count"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun count(
        @RequestParam("search")
        search: String?
    ): Long {
        logger.debug { "Search Query: $search" }

        return service.count(search)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun create(@RequestBody entity: T): T {
        logger.debug { "Entity: $entity" }

        return service.create(entity)
    }

    @Deprecated("PUT doesn't work generically")
    @PutMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun replace(@PathVariable("id") id: Long, @RequestBody entity: T): T {
        logger.debug { "ID: $id" }
        logger.debug { "Entity: $entity" }

        validateId(id)
        return service.replace(id, entity)
    }

    @Deprecated("PATCH doesn't work generically")
    @PatchMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    override fun modify(@PathVariable("id") id: Long, @RequestBody patchedFields: Map<String, Any>): T {
        logger.debug { "ID: $id" }

        return service.modify(id, patchedFields)
    }

    @DeleteMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun remove(@PathVariable("id") id: Long) {
        logger.debug { "ID: $id" }

        return service.remove(id)
    }

    @DeleteMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun removeAll(
        @RequestParam("search")
        search: String?
    ) {
        logger.debug { "Search Query: $search" }

        return service.removeAll(search)
    }

    private fun validateId(id: Long) {
        if (id < 0) {
            logger.error { "ID [$id] is not valid" }

            throw EntityNotFoundException()
        }
    }

}
