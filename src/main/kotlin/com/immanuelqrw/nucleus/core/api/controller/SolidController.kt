//package com.immanuelqrw.nucleus.core.api.controller
//
//import com.immanuelqrw.nucleus.core.api.model.BaseEntity
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.domain.Page
//import org.springframework.data.domain.Pageable
//import org.springframework.data.web.PageableDefault
//import org.springframework.data.web.SortDefault
//import org.springframework.core.GenericTypeResolver
//import org.springframework.http.MediaType
//import org.springframework.web.bind.annotation.*
//
///**
// * Example controller class
// */
//@RestController
//@RequestMapping("/solid")
//class SolidController<T : BaseEntity> : BaseController<T>(), FullyControllable<T> {
//
//    private val classType: Class<T> = GenericTypeResolver.resolveTypeArgument(javaClass, BaseEntity::class.java) as Class<T>
//
//    @Autowired
//    val service: SolidService
//
//    @GetMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
//    override fun find(@PathVariable("id") id: Long): T {
//        return super.find(id)
//    }
//
//    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
//    override fun findAll(
//        @RequestParam("page")
//        @PageableDefault(size = 100)
//        @SortDefault(sort = ["id"])
//        page: Pageable?,
//        @RequestParam("search")
//        search: String?
//    ): Page<T> {
//        return service.findAll(page, search)
//    }
//
//    @PostMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    override fun create(@RequestBody entity: T): T {
//        return service.create(entity)
//    }
//
//    @PutMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    override fun replace(@PathVariable("id")id: Long, @RequestBody entity: T): T {
//        return service.replace(id, entity)
//    }
//
//    @PatchMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
//    override fun modify(@PathVariable("id")id: Long, @RequestBody patchedFields: Map<String, Any>): T {
//        return service.modify(id, patchedFields)
//    }
//
//    @DeleteMapping(name = "/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
//    override fun remove(@PathVariable("id")id: Long) {
//        return service.remove(id)
//    }
//
//    @DeleteMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
//    override fun removeAll(
//        @RequestParam("page")
//        @PageableDefault(size = 100)
//        @SortDefault(sort = ["id"])
//        page: Pageable?,
//        @RequestParam("search")
//        search: String?
//    ) {
//        return service.removeAll(page, search)
//    }
//}