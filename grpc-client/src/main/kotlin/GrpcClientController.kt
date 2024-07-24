package com.akra

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grpc")
class GrpcController(private val grpcClientService: GrpcClientService) {

}
