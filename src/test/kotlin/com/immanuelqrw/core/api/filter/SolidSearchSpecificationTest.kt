package com.immanuelqrw.core.api.filter

import com.immanuelqrw.core.api.test.example.Solid
import com.immanuelqrw.core.api.test.example.SolidSearchSpecification
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Unit tests for SolidSearchSpecification
 */
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SolidSearchSpecificationTest: SearchSpecificationTest<Solid>() {

    @InjectMocks
    override lateinit var searchSpecification: SolidSearchSpecification
}