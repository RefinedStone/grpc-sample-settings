package com.akra
import com.google.protobuf.Empty
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class GrpcClientService {

    @GrpcClient("testEntityService")
    private lateinit var testEntityServiceBlockingStub: TestEntityServiceGrpc.TestEntityServiceBlockingStub

    fun getOneTestEntity() {
        val response = testEntityServiceBlockingStub.getOneTestEntity(Empty.getDefaultInstance())
        println("Received: ${response.entity}")
    }

    fun getAllTestEntities() {
        val response = testEntityServiceBlockingStub.getAllTestEntities(Empty.getDefaultInstance())
        println("Received: ")
        response.entitiesList.forEach { println(it) }
    }
}
