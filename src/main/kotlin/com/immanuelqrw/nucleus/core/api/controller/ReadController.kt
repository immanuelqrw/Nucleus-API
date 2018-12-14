package com.immanuelqrw.nucleus.core.api.controller

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import com.immanuelqrw.nucleus.core.api.service.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Abstract read only controller class
 */
abstract class ReadController<T : BaseEntity> : Getable<T> {

    abstract val service: BaseService<T>

    override fun find(id: Long): T {
        return service.find(id)
    }

    override fun findAll(page: Pageable?, search: String?): Page<T> {
        return service.findAll(page, search)
    }
}