package com.immanuelqrw.core.api.test.example

import com.immanuelqrw.core.api.controller.BaseController
import org.springframework.web.bind.annotation.*

/**
 * Example controller class
 */
@RestController
@RequestMapping("/solid")
class SolidController : BaseController<Solid>()
