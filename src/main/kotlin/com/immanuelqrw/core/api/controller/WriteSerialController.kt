package com.immanuelqrw.core.api.controller

import com.immanuelqrw.core.api.SerialWritable
import com.immanuelqrw.core.api.service.BaseSerialService
import com.immanuelqrw.core.entity.SerialEntityable
import org.springframework.beans.factory.annotation.Autowired

/**
 * Abstract write only controller class
 */
abstract class WriteSerialController<T : SerialEntityable> : SerialWritable<T> {

    @Autowired
    private lateinit var service: BaseSerialService<T>

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
