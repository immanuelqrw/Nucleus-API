package com.immanuelqrw.core.api.test.unit.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.immanuelqrw.core.api.repository.BaseUniqueRepository
import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.core.entity.UniqueEntityable
import com.immanuelqrw.core.test.Testable
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.persistence.RollbackException

/**
 * Unit tests for Service
 */
abstract class BaseUniqueServiceTest<T : UniqueEntityable> : Testable {

    protected abstract val classType: Class<T>

    protected abstract val service: BaseUniqueService<T>

    protected abstract val repository: BaseUniqueRepository<T>
    protected abstract val searchService: SearchService<T>

    protected abstract val validId: UUID
    protected abstract val invalidId: UUID

    protected abstract val validEntity: T
    protected abstract val invalidEntity: T
    protected abstract val replacedEntity: T

    protected abstract val validPatchedFields: Map<String, Any>
    protected abstract val invalidPatchedFields: Map<String, Any>
    protected abstract val originalFields: Map<String, Any>
    protected abstract val patchedFields: Map<String, Any>

    protected abstract val validPageable: Pageable
    protected abstract val invalidPageable: Pageable
    protected abstract val validPage: Page<T>

    protected abstract val validSearch: String
    protected abstract val invalidSearch: String

    protected abstract val validSearchSpecification: Specification<T>?
    protected abstract val invalidSearchSpecification: Specification<T>?

    protected abstract val objectMapper: ObjectMapper

    @BeforeAll
    override fun preSetUp() {
        // Subclass implementation
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }

    @AfterEach
    override fun tearDown() {}

    @AfterAll
    override fun postTearDown() {}

    @Nested
    inner class Success {
        @Test
        @DisplayName("given valid id - when GET entity - returns entity")
        fun testGetEntityWithValidId() {
            whenever(repository.getOne(validId)).thenReturn(validEntity)

            service.find(validId) shouldEqual validEntity
        }

        @Test
        @DisplayName("given valid page, sort, and search parameters - when GET entities - returns entities")
        fun testGetEntitiesWithValidQueryParameters() {
            whenever(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
            whenever(repository.findAll(validSearchSpecification, validPageable)).thenReturn(validPage)

            service.findAll(validPageable, validSearch) shouldEqual validPage
        }

        @Test
        @DisplayName("given valid entity - when POST entity - persists entity")
        fun testPostValidEntity() {
            whenever(repository.save(validEntity)).thenReturn(validEntity)

            service.create(validEntity) shouldEqual validEntity
        }

        @Test
        @DisplayName("given valid id and entity to replace - when PUT entity - replaces entity")
        fun testPutValidEntityWithValidId() {
            whenever(repository.getOne(validId)).thenReturn(validEntity)
            whenever(validEntity.id).thenReturn(validId)

            whenever(repository.save(validEntity)).thenReturn(replacedEntity)

            service.replace(validId, validEntity) shouldEqual replacedEntity
        }

        @Disabled
        @Test
        @DisplayName("given valid id and fields to replace - when PATCH entity - modifies entity")
        fun testPatchEntityWithValidIdAndFields() {
            whenever(repository.getOne(validId)).thenReturn(validEntity)

            whenever(objectMapper.convertValue(validEntity, Map::class.java)).thenReturn(originalFields)
            doReturn(patchedFields).whenever(originalFields).plus(validPatchedFields)
            // whenever(originalFields.plus(validPatchedFields)).thenReturn(patchedFields)
            whenever(objectMapper.convertValue(patchedFields, classType)).thenReturn(replacedEntity)

            whenever(repository.save(replacedEntity)).thenReturn(replacedEntity)

            service.modify(validId, validPatchedFields) shouldEqual replacedEntity
        }

        @Test
        @DisplayName("given valid id - when DELETE entity - sets entity removedOn to now")
        fun testDeleteEntityWithValidId() {
            whenever(repository.getOne(validId)).thenReturn(validEntity)
            whenever(validEntity.removedOn).thenReturn(LocalDateTime.now())
            whenever(repository.save(validEntity)).thenReturn(validEntity)

            service.remove(validId)

            validEntity.removedOn.shouldNotBeNull()
        }

        @Test
        @DisplayName("given valid page, sort, and search parameters - when DELETE entities - sets entities' removedOn to now")
        fun testDeleteEntitiesWithValidQueryParameters() {
            whenever(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))

            val validEntities: Page<T> = PageImpl<T>(listOf(validEntity))
            whenever(repository.findAll(validSearchSpecification, validPageable)).thenReturn(validEntities)

            whenever(repository.save(validEntity)).thenReturn(validEntity)

            service.removeAll(validPageable, validSearch)
        }
    }

    @Nested
    inner class Failure {
        @Nested
        inner class InvalidUriPath {
            @Test
            @DisplayName("given invalid id - when GET entity - returns NotFound response")
            fun testGetEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.find(invalidId)
                }
            }

            @Test
            @DisplayName("given invalid id - when PUT entity - returns NotFound response")
            fun testPutEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.replace(invalidId, validEntity)
                }
            }

            @Test
            @DisplayName("given invalid id - when PATCH entity - returns NotFound response")
            fun testPatchEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.modify(invalidId, validPatchedFields)
                }
            }

            @Test
            @DisplayName("given invalid id - when DELETE entity - returns NotFound response")
            fun testDeleteEntityWithInvalidId() {
                doThrow(EntityNotFoundException::class).whenever(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.remove(invalidId)
                }
            }
        }

        @Nested
        inner class InvalidBody {
            @Test
            @DisplayName("given invalid entity - when POST entity - returns BadRequest response")
            fun testPostInvalidEntity() {
                doThrow(RollbackException::class).whenever(repository).save(invalidEntity)

                assertThrows<RollbackException> {
                    service.create(invalidEntity)
                }
            }

            @Test
            @DisplayName("given invalid entity - when PUT entity - returns BadRequest response")
            fun testPutInvalidEntity() {
                whenever(repository.getOne(validId)).thenReturn(validEntity)

                whenever(validEntity.id).thenReturn(validId)
                doThrow(RollbackException::class).whenever(repository).save(invalidEntity)

                assertThrows<RollbackException> {
                    service.replace(validId, invalidEntity)
                }
            }

            @Disabled
            @Test
            @DisplayName("given invalid partial entity - when PATCH entity - returns BadRequest response")
            fun testPatchEntityWithInvalidFields() {
                whenever(repository.getOne(validId)).thenReturn(validEntity)

                whenever(objectMapper.convertValue(validEntity, Map::class.java)).thenReturn(originalFields)
                whenever(originalFields.plus(invalidPatchedFields)).thenReturn(patchedFields)
                whenever(objectMapper.convertValue(patchedFields, classType)).thenReturn(invalidEntity)

                doThrow(RollbackException::class).whenever(repository).save(invalidEntity)

                assertThrows<RollbackException> {
                    service.modify(validId, invalidPatchedFields)
                }
            }
        }

        // ! Find which error thrown by bad request
        @Nested
        inner class InvalidQueryParam {
            @Test
            @DisplayName("given invalid page parameter - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithInvalidPageParameter() {
                whenever(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class).whenever(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            // ? Look into if there is a way to make sort parameter fail separately
            @Test
            @DisplayName("given invalid sort parameter - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithInvalidSortParameter() {
                whenever(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class).whenever(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            // - Consider splitting into invalid search key, operation, value
            @Test
            @DisplayName("given invalid search parameters - when GET entities - returns BadRequest response")
            fun testGetEntitiesWithInvalidSearchParameter() {
                whenever(searchService.generateSpecification(invalidSearch)).thenReturn((invalidSearchSpecification))
                doThrow(RuntimeException::class).whenever(repository).findAll(invalidSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            @DisplayName("given invalid page parameter - when DELETE entities - returns BadRequest response")
            fun testDeleteEntitiesWithInvalidPageParameter() {
                whenever(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class).whenever(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            // ? Look into if there is a way to make sort parameter fail separately
            @Test
            @DisplayName("given invalid sort parameter - when DELETE entities - returns BadRequest response")
            fun testDeleteEntitiesWithInvalidSortParameter() {
                whenever(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class).whenever(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            @DisplayName("given invalid search parameters - when DELETE entities - returns BadRequest response")
            fun testDeleteEntitiesWithInvalidSearchParameter() {
                whenever(searchService.generateSpecification(invalidSearch)).thenReturn((invalidSearchSpecification))
                doThrow(RuntimeException::class).whenever(repository).findAll(invalidSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }
        }
    }
}
