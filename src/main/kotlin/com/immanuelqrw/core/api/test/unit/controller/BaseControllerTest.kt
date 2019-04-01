package com.immanuelqrw.core.api.test.unit.controller

import com.immanuelqrw.core.api.model.BaseEntity
import com.immanuelqrw.core.api.service.BaseService
import com.immanuelqrw.core.api.test.Testable
import com.immanuelqrw.core.api.utility.Utility.OBJECT_MAPPER
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.*
import org.mockito.Mockito.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import javax.persistence.EntityNotFoundException
import javax.persistence.RollbackException

/**
 * Unit tests for Controller
 */
abstract class BaseControllerTest<T : BaseEntity> : Testable {

    protected abstract val mvc: MockMvc

    protected abstract val service: BaseService<T>

    protected abstract val entityName: String

    protected abstract val validId: Long
    protected abstract val invalidId: Long

    protected abstract val validEntity: T
    protected abstract val invalidEntity: T
    protected abstract val replacedEntity: T

    protected abstract val validPatchedFields: Map<String, Any>
    protected abstract val invalidPatchedFields: Map<String, Any>

    protected abstract val validPageable: Pageable
    protected abstract val invalidPageable: Pageable
    protected abstract val validPage: Page<T>

    protected abstract val validSearch: String
    protected abstract val invalidSearch: String

    private lateinit var baseUri: String
    private lateinit var idUri: String

    @BeforeAll
    override fun prepare() {
        // Subclass implementation

        baseUri = "/$entityName"
        idUri = "$baseUri/{id}"
    }

    @BeforeEach
    override fun setUp() {
        // Subclass implementation
    }


    // FIXME Replace /solid with /{entityName} and make overriden variable
    @Nested
    inner class Success {
        // FIXME Deserialize mock output
        @Test
        fun `given valid id - when GET entity - returns entity`() {
            `when`(service.find(validId)).thenReturn(validEntity)

            val mvcResult: MvcResult = mvc.perform(
                get(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk)
                .andReturn()

            // FIXME
            mvcResult.response

            // val actualEntity: T = OBJECT_MAPPER.readValue(mvcResult.response.contentAsString, T::class.java)
            // actualEntity.id shouldEqual validEntity.id
        }

        @Test
        fun `given valid page, sort, and search parameters - when GET entities - returns entities`() {
            `when`(service.findAll(validPageable, validSearch)).thenReturn(validPage)

            val mvcResult: MvcResult = mvc.perform(
                get(baseUri)
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk)
                .andReturn()

            // FIXME
            mvcResult.response
        }

        @Test
        fun `given valid entity - when POST entity - persists entity`() {
            `when`(service.create(validEntity)).thenReturn(validEntity)

            val mvcResult: MvcResult = mvc.perform(
                post(baseUri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(OBJECT_MAPPER.writeValueAsBytes(validEntity))
            )
                .andExpect(status().isNoContent)
                .andReturn()

            // FIXME
            mvcResult.response
        }

        @Test
        fun `given valid id and entity to replace - when PUT entity - replaces entity`() {
            `when`(service.replace(validId, validEntity)).thenReturn(replacedEntity)

            val mvcResult: MvcResult = mvc.perform(
                put(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(OBJECT_MAPPER.writeValueAsBytes(validEntity))
            )
                .andExpect(status().isNoContent)
                .andReturn()

            // FIXME
            mvcResult.response
        }

        @Test
        fun `given valid id and fields to replace - when PATCH entity - modifies entity`() {
            `when`(service.modify(validId, validPatchedFields)).thenReturn(replacedEntity)

            val mvcResult: MvcResult = mvc.perform(
                patch(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(OBJECT_MAPPER.writeValueAsBytes(validPatchedFields))
            )
                .andExpect(status().isNoContent)
                .andReturn()

            // FIXME
            mvcResult.response
        }

        @Test
        fun `given valid id - when DELETE entity - sets entity removedOn to now`() {
            doNothing().`when`(service).remove(validId)

            val mvcResult: MvcResult = mvc.perform(
                delete(idUri, validId)
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk)
                .andReturn()

            // FIXME
            mvcResult.response
        }

        @Test
        fun `given valid page, sort, and search parameters - when DELETE entities - sets entities' removedOn to now`() {
            doNothing().`when`(service).removeAll(validPageable, validSearch)

            val mvcResult: MvcResult = mvc.perform(
                delete(baseUri)
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk)
                .andReturn()

            // FIXME
            mvcResult.response
            // FIXME Add rest of logic for iterating through page
        }
    }

    @Nested
    inner class Failure {
        @Nested
        inner class InvalidUriPath {
            @Test
            fun `given invalid id - when GET entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(service).find(invalidId)

                mvc.perform(
                    get(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            fun `given invalid id - when PUT entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(service).replace(invalidId, validEntity)

                mvc.perform(
                    put(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(validEntity))
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            fun `given invalid id - when PATCH entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(service).modify(invalidId, validPatchedFields)

                mvc.perform(
                    patch(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(validPatchedFields))
                )
                    .andExpect(status().isNotFound)
            }

            @Test
            fun `given invalid id - when DELETE entity - returns NotFound response`() {
                doThrow(EntityNotFoundException::class.java).`when`(service).remove(invalidId)

                mvc.perform(
                    delete(idUri, invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isNotFound)
            }
        }

        @Nested
        inner class InvalidBody {
            @Test
            fun `given invalid entity - when POST entity - returns BadRequest response`() {
                doThrow(RollbackException::class.java).`when`(service).create(invalidEntity)

                mvc.perform(
                    post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(invalidEntity))
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            fun `given invalid entity - when PUT entity - returns BadRequest response`() {
                doThrow(RollbackException::class.java).`when`(service).replace(validId, invalidEntity)

                mvc.perform(
                    put(idUri, validId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(invalidEntity))
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            fun `given invalid partial entity - when PATCH entity - returns BadRequest response`() {
                doThrow(RollbackException::class.java).`when`(service).modify(validId, invalidPatchedFields)

                mvc.perform(
                    put(idUri, validId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsBytes(invalidPatchedFields))
                )
                    .andExpect(status().isBadRequest)
            }
        }

        @Nested
        inner class InvalidQueryParam {
            @Test
            fun `given invalid page parameter - when GET entities - returns BadRequest response`() {
                doThrow(RuntimeException::class.java).`when`(service).findAll(invalidPageable, validSearch)

                mvc.perform(
                    get(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isBadRequest)
            }

            // FIXME Look into if there is a way to make sort parameter fail separately
            @Test
            fun `given invalid sort parameter - when GET entities - returns BadRequest response`() {
                doThrow(RuntimeException::class.java).`when`(service).findAll(invalidPageable, validSearch)

                mvc.perform(
                    get(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            fun `given invalid search parameters - when GET entities - returns BadRequest response`() {
                doThrow(RuntimeException::class.java).`when`(service).findAll(validPageable, invalidSearch)

                mvc.perform(
                    get(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            fun `given invalid page parameter - when DELETE entities - returns BadRequest response`() {
                doThrow(RuntimeException::class.java).`when`(service).findAll(invalidPageable, validSearch)

                mvc.perform(
                    delete(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isBadRequest)
            }

            // FIXME Look into if there is a way to make sort parameter fail separately
            @Test
            fun `given invalid sort parameter - when DELETE entities - returns BadRequest response`() {
                doThrow(RuntimeException::class.java).`when`(service).findAll(invalidPageable, validSearch)

                mvc.perform(
                    delete(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isBadRequest)
            }

            @Test
            fun `given invalid search parameters - when DELETE entities - returns BadRequest response`() {
                doThrow(RuntimeException::class.java).`when`(service).removeAll(validPageable, invalidSearch)

                mvc.perform(
                    delete(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                    .andExpect(status().isBadRequest)
            }
        }
    }
}