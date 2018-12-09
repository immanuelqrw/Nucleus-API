import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.11"
}

group = "com.immanuelqrw.nucleus.core.api"
version = "0.0.1-Pre-Alpha"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.9.7")
    compile("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.9.0")
    compile("org.springframework", "spring-core", "5.1.3.RELEASE")
    compile("org.hibernate", "hibernate-core", "5.3.7.Final")
    compile( "org.postgresql", "postgresql", "42.2.5")
    testCompile("org.junit.jupiter", "junit-jupiter-api", "5.3.1")
    testCompile("org.junit.jupiter", "junit-jupiter-params", "5.3.1")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.3.1")
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.3.1")
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