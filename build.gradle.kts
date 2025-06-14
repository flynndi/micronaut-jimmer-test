val jimmerVersion: String by project
val micronautJimmerVersion: String by project
plugins {
    id("io.micronaut.application") version "4.5.3"
    id("com.gradleup.shadow") version "8.3.6"
    id("io.micronaut.aot") version "4.5.3"
}

version = "0.1"
group = "io.micronaut.test"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    implementation("io.github.flynndi:micronaut-jimmer:$micronautJimmerVersion")
    implementation("io.micronaut.data:micronaut-data-model")
    runtimeOnly("org.yaml:snakeyaml")
    runtimeOnly("com.h2database:h2")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation("io.micronaut:micronaut-http-client")
    annotationProcessor("org.babyfish.jimmer:jimmer-apt:$jimmerVersion")
    implementation("io.micronaut:micronaut-inject-java")
    annotationProcessor("io.micronaut:micronaut-inject-java")
    annotationProcessor("io.github.flynndi:micronaut-jimmer-apt:$micronautJimmerVersion")
    annotationProcessor("io.micronaut.sourcegen:micronaut-sourcegen-generator-java")
}


application {
    mainClass = "io.micronaut.test.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("io.micronaut.test.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}



