package com.immanuelqrw.nucleus.core.api.controller

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import com.immanuelqrw.nucleus.core.api.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Abstract write only controller class
 */
abstract class WriteController<T : BaseEntity>
@Autowired constructor(
    private val service: BaseService<T>
): Writable<T> {

    override fun create(entity: T): T {
        return service.create(entity)
    }

    override fun replace(id: Long, entity: T): T {
        return service.replace(id, entity)
    }

    override fun modify(id: Long, patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }
}