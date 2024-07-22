//package com.akra
//
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/grpc")
//class GrpcController(private val grpcClientService: GrpcClientService) {
//
//    @GetMapping("/one")
//    fun getOneTestEntity(): String {
//        return grpcClientService.getOneTestEntity()
//    }
//
//    @GetMapping("/all")
//    fun getAllTestEntities(): String {
//        return grpcClientService.getAllTestEntities()
//    }
//}
