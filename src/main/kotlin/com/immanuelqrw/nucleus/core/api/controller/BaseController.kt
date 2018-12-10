package com.immanuelqrw.nucleus.core.api.controller

import com.immanuelqrw.nucleus.core.api.filter.SearchSpecificationsBuilder
import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import com.immanuelqrw.nucleus.core.api.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.regex.Pattern


/**
 * Abstract controller class
 */
abstract class BaseController<T : BaseEntity> : Crudable<T> {
    abstract val repository: BaseRepository<T>

    fun parseSearch(search: String): Specification<T>? {

        val builder = SearchSpecificationsBuilder<T>()

        // TODO Move Pattern to configuration file
        val pattern = Pattern.compile("(\\w+?)([~:<>])(\\w+?);")
        val matcher = pattern.matcher("$search;")

        while (matcher.find()) {
            val key: String = matcher.group(1)
            val operation: String = matcher.group(2)
            val value: Any = matcher.group(3)

            builder.with(key, operation, value)
        }

        return builder.build()
    }

    @ResponseBody
    override fun find(id: Long): T {
        return repository.getOne(id)
    }

    // TODO Add specification
    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = 100)
        @SortDefault(sort = arrayOf("id"))
        page: Pageable,
        @RequestParam("search")
        search: String
    ): Page<T> {
        val searchSpecification: Specification<T>? = parseSearch(search)
        return repository.findAll(searchSpecification, page)
    }

    override fun create(@RequestBody entity: T): T {
        return repository.save(entity)
    }

    override fun replace(id: Long, @RequestBody entity: T): T {
        entity.id = id
        return repository.save(entity)
    }

    // TODO implement patch functionality
    /*
    override fun modify(id: Long, @RequestBody updates: Map<String, Any>): T {
        entity.id = id
        return repository.save(entity)
    }
    */

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
        val searchSpecification: Specification<T>? = parseSearch(search)
        val removableEntities: Page<T> = repository.findAll(searchSpecification, page)
        return repository.deleteAll(removableEntities)
    }
}