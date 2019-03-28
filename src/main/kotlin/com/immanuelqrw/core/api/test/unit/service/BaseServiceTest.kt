package com.immanuelqrw.core.api.test.unit.service

import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.repository.BaseRepository
import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.core.api.service.SearchService
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.*
import org.mockito.Mockito.`when`
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

/**
 * Unit tests for Service
 */
abstract class BaseServiceTest<T : BaseEntity> : Testable {

    protected abstract val service: BaseService<T>

    protected abstract val repository: BaseRepository<T>
    protected abstract val searchService: SearchService<T>

    protected abstract val validId: Long
    protected abstract val invalidId: Long

    protected abstract val validEntity: T
    protected abstract val invalidEntity: T
    protected abstract val replacedEntity: T

    protected abstract val patchedFields: Map<String, Any>

    protected abstract val validPageable: Pageable
    protected abstract val invalidPageable: Pageable
    protected abstract val validPage: Page<T>

    protected abstract val validSearch: String
    protected abstract val invalidSearch: String

    protected abstract val validSearchSpecification: Specification<T>?
    protected abstract val invalidSearchSpecification: Specification<T>?

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

            `when`(repository.save(replacedEntity)).thenReturn(replacedEntity)

            service.replace(validId, validEntity) shouldEqual replacedEntity
        }

        @Test
        fun `given valid id and fields to replace - when PATCH entity - modifies entity`() {
            `when`(repository.getOne(validId)).thenReturn(validEntity)

            // FIXME Add rest of logic for processing maps
            `when`(repository.save(replacedEntity)).thenReturn(replacedEntity)

            service.modify(validId, patchedFields) shouldEqual replacedEntity
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
            `when`(repository.findAll(validSearchSpecification, validPageable)).thenReturn(validPage)

            `when`(repository.save(validEntity)).thenReturn(validEntity)

            service.removeAll(validPageable, validSearch)

            // FIXME Add rest of logic for iterating through page
        }
    }

    @Nested
    inner class Failure {
        @Nested
        inner class InvalidUriPath {
            @Test
            fun `given invalid id - when GET entity - returns NotFound response`() {
                `when`(repository.getOne(invalidId)).thenThrow(EntityNotFoundException::class.java)

                Assertions.assertThrows(EntityNotFoundException::class.java) {
                    service.find(invalidId)
                }
            }

            @Test
            fun `given invalid id - when PUT entity - returns NotFound response`() {
                `when`(repository.getOne(invalidId)).thenThrow(EntityNotFoundException::class.java)

                Assertions.assertThrows(EntityNotFoundException::class.java) {
                    service.replace(invalidId, validEntity)
                }
            }

            @Test
            fun `given invalid id - when PATCH entity - returns NotFound response`() {
                `when`(repository.getOne(invalidId)).thenThrow(EntityNotFoundException::class.java)

                Assertions.assertThrows(EntityNotFoundException::class.java) {
                    service.modify(invalidId, patchedFields)
                }
            }

            @Test
            fun `given invalid id - when DELETE entity - returns NotFound response`() {
                `when`(repository.getOne(invalidId)).thenThrow(EntityNotFoundException::class.java)

                Assertions.assertThrows(EntityNotFoundException::class.java) {
                    service.remove(invalidId)
                }
            }
        }

        // FIXME Find which error thrown by bad entity
        @Nested
        inner class InvalidBody {
            @Test
            fun `given invalid entity - when POST entity - returns BadRequest response`() {
                `when`(repository.save(invalidEntity)).thenThrow()

                Assertions.assertThrows() {
                    service.create(invalidEntity)
                }
            }

            @Test
            fun `given invalid entity - when PUT entity - returns BadRequest response`() {
                `when`(repository.save(invalidEntity)).thenThrow()

                Assertions.assertThrows() {
                    service.replace(validId, invalidEntity)
                }
            }

            @Test
            fun `given invalid partial entity - when PATCH entity - returns BadRequest response`() {
                `when`(repository.save(invalidEntity)).thenThrow()

                Assertions.assertThrows() {
                    service.modify(validId, patchedFields)
                }
            }
        }

        // FIXME Find which error thrown by bad request
        @Nested
        inner class InvalidQueryParam {
            @Test
            fun `given invalid page parameter - when GET entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                `when`(repository.findAll(validSearchSpecification, invalidPageable)).thenThrow()

                Assertions.assertThrows() {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            fun `given invalid sort parameter - when GET entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                `when`(repository.findAll(validSearchSpecification, invalidPageable)).thenThrow()

                Assertions.assertThrows() {
                    service.findAll(invalidPageable, validSearch)
                }
                assert(false)
            }

            // TODO Consider splitting into invalid search key, operation, value
            @Test
            fun `given invalid search parameters - when GET entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(invalidSearch)).thenReturn((invalidSearchSpecification))
                `when`(repository.findAll(invalidSearchSpecification, invalidPageable)).thenThrow()

                Assertions.assertThrows() {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            fun `given invalid page parameter - when DELETE entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                `when`(repository.findAll(validSearchSpecification, invalidPageable)).thenThrow()

                Assertions.assertThrows() {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            fun `given invalid sort parameter - when DELETE entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(validSearch)).thenReturn((validSearchSpecification))
                `when`(repository.findAll(validSearchSpecification, invalidPageable)).thenThrow()

                Assertions.assertThrows() {
                    service.findAll(invalidPageable, validSearch)
                }
            }

            @Test
            fun `given invalid search parameters - when DELETE entities - returns BadRequest response`() {
                `when`(searchService.generateSpecification(invalidSearch)).thenReturn((invalidSearchSpecification))
                `when`(repository.findAll(invalidSearchSpecification, invalidPageable)).thenThrow()

                Assertions.assertThrows() {
                    service.findAll(invalidPageable, validSearch)
                }
            }
        }
    }
}