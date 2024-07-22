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
    implementation("net.devh:grpc-spring-boot-starter:$grpcSpringBootStarterVersion")
    configurations.all {
        resolutionStrategy.force("com.google.errorprone:error_prone_annotations:2.23.0")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.1"
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

//tasks.register<JavaExec>("runGrpcApplication") {
//    group = "application"
//    description = "Run the gRPC application"
//    classpath = sourceSets["main"].runtimeClasspath
//    mainClass.set("com.akra.GrpcApplicationKt")  // 또는 sample.ClientKt 중 하나를 선택
//}