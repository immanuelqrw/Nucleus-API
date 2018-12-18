import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask


plugins {
    java
    kotlin("jvm") version "1.3.11"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.11"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.11"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.11"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.sonarqube") version "2.6"
    id("org.jetbrains.dokka") version "0.9.17"
    idea
}

group = "com.immanuelqrw.core"
version = "0.0.1-pre-alpha"

val kotlinVersion = "1.3.11"
val junitVersion = "5.3.2"
val jacksonVersion = "2.9.7"
val springDataVersion = "2.1.3.RELEASE"
val springBootVersion = "2.1.1.RELEASE"
val springVersion = "5.1.3.RELEASE"
val dokkaVersion = "0.9.17"

repositories {
    mavenCentral()
    jcenter()
}

val integrationTestCompile by configurations.creating {
    extendsFrom(configurations["testCompile"])
}

val integrationTestImplementation by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("org.jetbrains.kotlin", "kotlin-reflect", kotlinVersion)
    compile("org.jetbrains.dokka", "dokka-gradle-plugin", dokkaVersion)

    compile("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    compile("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", jacksonVersion)

    compile("org.springframework.data", "spring-data-jpa", springDataVersion)
    compile("org.springframework", "spring-orm", springVersion)
    compile("org.springframework", "spring-web", springVersion)
    compile("org.springframework", "spring-webmvc", springVersion)

    compile( "org.postgresql", "postgresql", "42.2.5")
    compile("org.hibernate", "hibernate-core", "5.3.7.Final")
    compile("org.hibernate.validator", "hibernate-validator", "6.0.13.Final")

    compile("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    compile("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    compile("org.junit.jupiter", "junit-jupiter-engine", junitVersion)


    implementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    implementation("io.mockk", "mockk", "1.8.13")
    implementation("org.amshove.kluent", "kluent", "1.42")


    testCompile("org.hibernate", "hibernate-testing", "5.3.7.Final")

    testCompile("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testCompile("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
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

sourceSets.create("integrationTest") {
    java.srcDir(file("src/integrationTest/java"))
    java.srcDir(file("src/integrationTest/kotlin"))
    resources.srcDir(file("src/integrationTest/resources"))
    compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
    runtimeClasspath += output + compileClasspath
}

task<Test>("integrationTest") {
    description = "Runs the integration tests."
    group = "verification"
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

tasks["check"].dependsOn("integrationTest")

val sonarHostUrl: String by project
val sonarOrganization: String by project
val sonarLogin: String by project

sonarqube {
    properties {
        property("sonar.host.url", sonarHostUrl)
        property("sonar.organization", sonarOrganization)
        property("sonar.login", sonarLogin)

        property("sonar.projectKey", "immanuelqrw_Nucleus-API")
        property("sonar.projectName", "Nucleus-API")
        property("sonar.projectVersion", version)
    }
}

tasks["check"].dependsOn("sonarqube")


tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/docs/dokka"
}