package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.UniqueGetable
import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.core.entity.UniqueEntityable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

/**
 * Abstract read only controller class
 */
abstract class ReadUniqueController<T : UniqueEntityable> : UniqueGetable<T> {

    @Autowired
    private lateinit var service: BaseUniqueService<T>

    override fun find(id: UUID): T {
        return service.find(id)
    }

    override fun findAll(page: Pageable, search: String?): Page<T> {
        return service.findAll(page, search)
    }

}
