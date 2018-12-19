val implementation by configurations

val testRuntime by configurations
val testImplementation by configurations

val integrationTestImplementation by configurations.creating {
    extendsFrom(testImplementation)
}

val kotlinVersion: String by extra
val junitVersion: String by extra
val jacksonVersion: String by extra
val springDataVersion : String by extra
val springBootVersion: String by extra
val springVersion: String by extra
val dokkaVersion : String by extra

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin", "kotlin-reflect", kotlinVersion)
    implementation("org.jetbrains.dokka", "dokka-gradle-plugin", dokkaVersion)

    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", jacksonVersion)
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", jacksonVersion)

    implementation("org.springframework.data", "spring-data-jpa", springDataVersion)
    implementation("org.springframework", "spring-orm", springVersion)
    implementation("org.springframework", "spring-web", springVersion)
    implementation("org.springframework", "spring-webmvc", springVersion)

    implementation("org.postgresql", "postgresql", "42.2.5")
    implementation("org.hibernate", "hibernate-core", "5.3.7.Final")
    implementation("org.hibernate.validator", "hibernate-validator", "6.0.13.Final")

    implementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    implementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    implementation("org.junit.jupiter", "junit-jupiter-engine", junitVersion)


    implementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    implementation("io.mockk", "mockk", "1.8.13")
    implementation("org.amshove.kluent", "kluent", "1.42")


    testImplementation("org.hibernate", "hibernate-testing", "5.3.7.Final")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("io.mockk", "mockk", "1.8.13")
    testImplementation("org.amshove.kluent", "kluent", "1.42")
}