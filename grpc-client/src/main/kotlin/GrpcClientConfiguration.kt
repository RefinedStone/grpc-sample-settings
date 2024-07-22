package com.akra

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GrpcClientConfiguration {
    @Bean
    open fun init(grpcClientService: GrpcClientService) =
        CommandLineRunner {
            grpcClientService.getOneTestEntity()
            grpcClientService.getAllTestEntities()
        }
}
