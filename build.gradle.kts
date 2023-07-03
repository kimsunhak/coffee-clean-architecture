plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
}

val projectGroup: String by project

allprojects {
    group = projectGroup
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}


subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-kapt")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencies {
        // Kotlin Standard
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        // Annotation Processing Tool
        compileOnly("org.springframework.boot:spring-boot-configuration-processor")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")

    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }
        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

