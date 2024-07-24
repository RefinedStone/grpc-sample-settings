import com.google.protobuf.gradle.id

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("com.google.protobuf") version "0.9.4"
}

group = "com.akra"
version = "0.0.1-SNAPSHOT"

val grpcVersion = "1.63.0"
val protobufVersion = "3.25.1"
val springBootStarterVersion = "3.3.0"
val grpcSpringBootStarterVersion = "3.1.0.RELEASE"

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.springframework.boot:spring-boot-starter:$springBootStarterVersion")
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootStarterVersion")
    implementation("net.devh:grpc-client-spring-boot-starter:$grpcSpringBootStarterVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    configurations.all {
        resolutionStrategy.force("com.google.errorprone:error_prone_annotations:2.23.0")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
            }
        }
    }
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// 명시적으로 메인 클래스를 지정
tasks.withType<JavaExec> {
    mainClass.set("com.akra.GrpcApplicationKt")
}

// gRPC 클라이언트를 실행하는 작업을 정의
tasks.register<JavaExec>("runGrpcClient") {
    group = "application"
    description = "Run the gRPC client"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.akra.GrpcClientApplicationKt")
}
