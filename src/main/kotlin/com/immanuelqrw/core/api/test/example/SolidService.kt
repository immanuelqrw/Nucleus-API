package com.immanuelqrw.core.api.test.example

import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.nucleus.core.api.test.example.Solid
import com.immanuelqrw.nucleus.core.api.test.example.SolidRepository
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