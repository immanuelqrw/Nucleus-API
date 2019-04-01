package com.immanuelqrw.core.api.test.unit.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.repository.BaseRepository
import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.core.api.service.SearchService
import com.immanuelqrw.core.api.utility.Utility
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.*
import org.mockito.Mockito.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException
import javax.persistence.RollbackException

/**
 * Unit tests for Service
 */
abstract class BaseServiceTest<T : BaseEntity> : Testable {

    protected abstract val classType: Class<T>

    protected abstract val service: BaseService<T>

    protected abstract val repository: BaseRepository<T>
    protected abstract val searchService: SearchService<T>

    protected abstract val validId: Long
    protected abstract val invalidId: Long

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
    override fun prepare() {
        // Subclass implementation
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }

    @Nested
    inner class Success {
        @Test
        fun `given valid id - when GET entity - returns entity`() {
            `when`(repository.getOne(validId)).thenReturn(validEntity)

            service.find(validId) shouldEqual validEntity
        }

        @Test
        fun `given valid page, sort, and search parameters - when GET entities - returns entities`() {
            `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
            `when`(repository.findAll(validSearchSpecification, validPageable)).thenReturn(validPage)

            service.findAll(validPageable, validSearch) shouldEqual validPage
        }

        @Test
        fun `given valid entity - when POST entity - persists entity`() {
            `when`(repository.save(validEntity)).thenReturn(validEntity)

            service.create(validEntity) shouldEqual validEntity
        }

        @Test
        fun `given valid id and entity to replace - when PUT entity - replaces entity`() {
            `when`(repository.getOne(validId)).thenReturn(validEntity)
            `when`(validEntity.id).thenReturn(validId)

            `when`(repository.save(validEntity)).thenReturn(replacedEntity)

            service.replace(validId, validEntity) shouldEqual replacedEntity
        }

        @Test
        fun `given valid id and fields to replace - when PATCH entity - modifies entity`() {
            `when`(repository.getOne(validId)).thenReturn(validEntity)

            `when`(objectMapper.convertValue(validEntity, Map::class.java)).thenReturn(originalFields)
            doReturn(patchedFields).`when`(originalFields).plus(validPatchedFields)
            // `when`(originalFields.plus(validPatchedFields)).thenReturn(patchedFields)
            `when`(objectMapper.convertValue(patchedFields, classType)).thenReturn(replacedEntity)

            `when`(repository.save(replacedEntity)).thenReturn(replacedEntity)

            service.modify(validId, validPatchedFields) shouldEqual replacedEntity
        }

        @Test
        fun `given valid id - when DELETE entity - sets entity removedOn to now`() {
            `when`(repository.getOne(validId)).thenReturn(validEntity)
            `when`(validEntity.removedOn).thenReturn(LocalDateTime.now())
            `when`(repository.save(validEntity)).thenReturn(validEntity)

            service.remove(validId)

            validEntity.removedOn.shouldNotBeNull()
        }

        @Test
        fun `given valid page, sort, and search parameters - when DELETE entities - sets entities' removedOn to now`() {
            `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))

            val validEntities: Page<T> = PageImpl<T>(listOf(validEntity))
            `when`(repository.findAll(validSearchSpecification, validPageable)).thenReturn(validEntities)

            `when`(repository.save(validEntity)).thenReturn(validEntity)

            service.removeAll(validPageable, validSearch)
        }
    }

    @Nested
    inner class Failure {
        @Nested
        inner class InvalidUriPath {
            @Test
            fun `given invalid id - when GET entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.find(invalidId)
                }
            }

            @Test
            fun `given invalid id - when PUT entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.replace(invalidId, validEntity)
                }
            }

            @Test
            fun `given invalid id - when PATCH entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.modify(invalidId, validPatchedFields)
                }
            }

            @Test
            fun `given invalid id - when DELETE entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(repository).getOne(invalidId)

                assertThrows<EntityNotFoundException> {
                    service.remove(invalidId)
                }
            }
        }

        @Nested
        inner class InvalidBody {
            @Test
            fun `given invalid entity - when POST entity - returns BadRequest response`() {
                doThrow(RollbackException::class.java).`when`(repository).save(invalidEntity)

                assertThrows<RollbackException> {
                    service.create(invalidEntity)
                }
            }

            @Test
            fun `given invalid entity - when PUT entity - returns BadRequest response`() {
                `when`(repository.getOne(validId)).thenReturn(validEntity)

                `when`(validEntity.id).thenReturn(validId)
                doThrow(RollbackException::class.java).`when`(repository).save(invalidEntity)

                assertThrows<RollbackException> {
                    service.replace(validId, invalidEntity)
                }
            }

            @Test
            fun `given invalid partial entity - when PATCH entity - returns BadRequest response`() {
                `when`(repository.getOne(validId)).thenReturn(validEntity)

                `when`(objectMapper.convertValue(validEntity, Map::class.java)).thenReturn(originalFields)
                `when`(originalFields.plus(invalidPatchedFields)).thenReturn(patchedFields)
                `when`(objectMapper.convertValue(patchedFields, classType)).thenReturn(invalidEntity)

                doThrow(RollbackException::class.java).`when`(repository).save(invalidEntity)

                assertThrows<RollbackException> {
                    service.modify(validId, invalidPatchedFields)
                }
            }
        }

        // FIXME Find which error thrown by bad request
        @Nested
        inner class InvalidQueryParam {
            @Test
            fun `given invalid page parameter - when GET entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class.java).`when`(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            // FIXME Look into if there is a way to make sort parameter fail separately
            @Test
            fun `given invalid sort parameter - when GET entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class.java).`when`(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            // TODO Consider splitting into invalid search key, operation, value
            @Test
            fun `given invalid search parameters - when GET entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(invalidSearch)).thenReturn((invalidSearchSpecification))
                doThrow(RuntimeException::class.java).`when`(repository).findAll(invalidSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            fun `given invalid page parameter - when DELETE entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class.java).`when`(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            // FIXME Look into if there is a way to make sort parameter fail separately
            @Test
            fun `given invalid sort parameter - when DELETE entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                doThrow(RuntimeException::class.java).`when`(repository).findAll(validSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            fun `given invalid search parameters - when DELETE entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(invalidSearch)).thenReturn((invalidSearchSpecification))
                doThrow(RuntimeException::class.java).`when`(repository).findAll(invalidSearchSpecification, invalidPageable)

                assertThrows<RuntimeException> {
                    service.findAll(invalidPageable, validSearch)
                }
            }
        }
    }
}