package com.immanuelqrw.nucleus.core.api.controller

import com.fasterxml.jackson.module.kotlin.convertValue
import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import com.immanuelqrw.nucleus.core.api.repository.BaseRepository
import com.immanuelqrw.nucleus.core.api.service.BaseService
import com.immanuelqrw.nucleus.core.api.service.SearchService
import com.immanuelqrw.nucleus.core.api.utility.Utility
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

    abstract val service: BaseService<T>

    override fun find(id: Long): T {
        return service.find(id)
    }

    override fun findAll(page: Pageable?, search: String?): Page<T> {
        return service.findAll(page, search)
    }

    override fun create(entity: T): T {
        return service.create(entity)
    }

    override fun replace(id: Long, entity: T): T {
        return service.replace(id, entity)
    }

    override fun modify(id: Long, patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }

    override fun remove(id: Long) {
        return service.remove(id)
    }

    override fun removeAll(page: Pageable?, search: String?) {
        return service.removeAll(page, search)
    }
}