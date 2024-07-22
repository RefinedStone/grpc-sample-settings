package com.akra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GrpcClientApplication
fun main(args: Array<String>) {
    runApplication<GrpcClientApplication>(*args)
}
