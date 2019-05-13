package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.Getable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Abstract read only controller class
 */
abstract class ReadController<T : BaseEntity> : Getable<T> {

    @Autowired
    private lateinit var service: BaseService<T>

    override fun find(id: Long): T {
        return service.find(id)
    }

    override fun findAll(page: Pageable, search: String?): Page<T> {
        return service.findAll(page, search)
    }

}
