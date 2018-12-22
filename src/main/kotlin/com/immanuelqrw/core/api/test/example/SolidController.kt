package com.immanuelqrw.core.api.test.example

import com.immanuelqrw.core.api.controller.BaseController
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Example controller class
 */
@RestController
@RequestMapping("/solid")
class SolidController
@Autowired constructor(val service: SolidService) : BaseController<Solid>(service)