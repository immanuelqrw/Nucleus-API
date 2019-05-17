package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.Writable
import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.core.entity.BaseEntity
import org.springframework.beans.factory.annotation.Autowired

/**
 * Abstract write only controller class
 */
abstract class WriteController<T : BaseEntity> : Writable<T> {

    @Autowired
    private lateinit var service: BaseService<T>

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
