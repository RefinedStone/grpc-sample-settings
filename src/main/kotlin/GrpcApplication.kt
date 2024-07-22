package com.akra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GrpcApplication
    fun main(args: Array<String>) {
        runApplication<GrpcApplication>(*args)
    }

