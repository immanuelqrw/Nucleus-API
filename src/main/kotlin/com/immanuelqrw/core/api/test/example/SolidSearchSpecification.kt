package com.immanuelqrw.core.api.test.example

import com.immanuelqrw.core.api.filter.SearchCriterion
import com.immanuelqrw.core.api.filter.SearchSpecification

// - Look into DI for non-Spring class
class SolidSearchSpecification(searchCriterion: SearchCriterion) : SearchSpecification<Solid>(searchCriterion)
