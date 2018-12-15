package com.immanuelqrw.nucleus.core.api.utility

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.InputStream

// TODO Move into nucleus.core.utility package
/**
 * Configuration
 *
 * Stores configuration values and methods needed to acquire them
 */
object Utility {
    /**
     * Object Mapper used for parsing JSON into data classes
     */
    // TODO Fix Expansion of YAML templates -- or write conversion method
    val OBJECT_MAPPER = ObjectMapper(YAMLFactory()).registerKotlinModule()

    /**
     * Loads resource from filename
     *
     * Using filename finds resource and creates usable stream
     *
     * @param name Name of resource file
     * @return InputStream of resource
     */
    private fun loadResource(name: String): InputStream {
        return object {}.javaClass.getResourceAsStream(name)
    }

    /**
     * Loads resource into data class
     *
     * Parses resource file and generates data class instance
     *
     * @param resourceName Name of resource file
     * @return Data class instance
     */
    private inline fun <reified T> loadResourceFromFile(resourceName: String): T {
        val inputStream: InputStream = loadResource(name = resourceName)
        return parseObjectFromInput(inputStream = inputStream, objectMapper = OBJECT_MAPPER)
    }

    /**
     * Creates data class instance from input
     *
     * Parses inputStream and generates data class instance
     *
     * @param inputStream InputStream of data file
     * @param objectMapper Object Mapper used to parse inputStream
     * @return Data class instance
     */
    private inline fun <reified T> parseObjectFromInput(inputStream: InputStream, objectMapper: ObjectMapper): T {
        return objectMapper.readValue(inputStream)
    }

    const val DEFAULT_PAGE_SIZE: Int = 100
    const val DEFAULT_SORT_FIELD: String = "id"
}
