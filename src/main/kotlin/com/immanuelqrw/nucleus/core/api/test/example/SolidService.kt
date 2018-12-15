package com.immanuelqrw.nucleus.core.api.test.example

import com.immanuelqrw.nucleus.core.api.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Solid service class
 */
@Service
class SolidService
@Autowired constructor(
    repository: SolidRepository,
    searchService: SolidSearchService
) : BaseService<Solid>(repository, searchService, Solid::class.java)