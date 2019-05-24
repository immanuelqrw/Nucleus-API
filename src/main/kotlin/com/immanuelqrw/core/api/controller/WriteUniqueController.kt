package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.UniqueWritable
import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.core.entity.UniqueEntityable
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

/**
 * Abstract write only controller class
 */
abstract class WriteUniqueController<T : UniqueEntityable> : UniqueWritable<T> {

    @Autowired
    private lateinit var service: BaseUniqueService<T>

    override fun create(entity: T): T {
        return service.create(entity)
    }

    override fun replace(id: UUID, entity: T): T {
        return service.replace(id, entity)
    }

    override fun modify(id: UUID, patchedFields: Map<String, Any>): T {
        return service.modify(id, patchedFields)
    }

}
