import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    java
    kotlin("jvm") version "1.3.11"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.11"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.11"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.11"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

group = "com.immanuelqrw.core"
version = "0.0.1-pre-alpha"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.9.7")
    compile("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.9.0")

    compile("org.springframework.data", "spring-data-jpa", "2.1.3.RELEASE")
    compile("org.springframework", "spring-orm", "5.1.3.RELEASE")
    compile("org.springframework", "spring-web", "5.1.3.RELEASE")
    compile("org.springframework", "spring-webmvc", "5.1.3.RELEASE")

    compile( "org.postgresql", "postgresql", "42.2.5")
    compile("org.hibernate", "hibernate-core", "5.3.7.Final")
    compile("org.hibernate.validator", "hibernate-validator", "6.0.13.Final")

    compile("org.junit.jupiter", "junit-jupiter-api", "5.3.2")
    compile("org.junit.jupiter", "junit-jupiter-params", "5.3.2")
    compile("org.junit.jupiter", "junit-jupiter-engine", "5.3.2")


    implementation("org.junit.jupiter", "junit-jupiter-api", "5.3.2")
    implementation("io.mockk", "mockk", "1.8.13")
    implementation("org.amshove.kluent", "kluent", "1.42")


    testCompile("org.hibernate", "hibernate-testing", "5.3.7.Final")

    testCompile("org.junit.jupiter", "junit-jupiter-api", "5.3.2")
    testCompile("org.junit.jupiter", "junit-jupiter-params", "5.3.2")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.3.2")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.3.2")
    testImplementation("io.mockk", "mockk", "1.8.13")
    testImplementation("org.amshove.kluent", "kluent", "1.42")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "4.8"
}