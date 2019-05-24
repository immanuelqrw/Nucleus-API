package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.SerialGetable
import com.immanuelqrw.core.api.service.BaseSerialService
import com.immanuelqrw.core.entity.SerialEntityable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Abstract read only controller class
 */
abstract class ReadSerialController<T : SerialEntityable> : SerialGetable<T> {

    @Autowired
    private lateinit var service: BaseSerialService<T>

    override fun find(id: Long): T {
        return service.find(id)
    }

    override fun findAll(page: Pageable, search: String?): Page<T> {
        return service.findAll(page, search)
    }

}
