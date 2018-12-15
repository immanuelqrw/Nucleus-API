package com.immanuelqrw.nucleus.core.api.controller

import com.immanuelqrw.nucleus.core.api.model.BaseEntity
import com.immanuelqrw.nucleus.core.api.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Example controller class
 */
@RestController
@RequestMapping("/solid")
class SolidController<T : BaseEntity>
@Autowired constructor(val service: BaseService<T>): BaseController<T>(service)