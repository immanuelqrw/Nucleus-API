package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.Countable
import com.immanuelqrw.core.api.UniqueGetable
import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.core.api.utility.Utility
import com.immanuelqrw.core.entity.UniqueEntityable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.SortDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

/**
 * Abstract read only controller class
 */
abstract class ReadUniqueController<T : UniqueEntityable> : UniqueGetable<T>, Countable {

    @Autowired
    private lateinit var service: BaseUniqueService<T>

    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun find(@PathVariable("id") id: UUID): T {
        return service.find(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun findAll(
        @RequestParam("page")
        @PageableDefault(size = Utility.DEFAULT_PAGE_SIZE)
        @SortDefault(sort = [Utility.DEFAULT_SORT_FIELD])
        page: Pageable?,
        @RequestParam("search")
        search: String?
    ): Iterable<T> {
        return service.findAll(page, search)
    }

    @GetMapping(path = ["/count"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun count(
        @RequestParam("search")
        search: String?
    ): Long {
        return service.count(search)
    }

}
