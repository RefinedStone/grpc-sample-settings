package com.akra


import com.akra.sample.proficiency.service.Protobuff
import com.akra.sample.proficiency.service.TestEntityServiceGrpc
import com.google.protobuf.Empty
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class GrpcClientService {

    @GrpcClient("testEntityService")
    private lateinit var testEntityServiceBlockingStub: TestEntityServiceGrpc.TestEntityServiceBlockingStub

    fun getOneTestEntity(): Protobuff.TestEntityResponse? {
        val response = testEntityServiceBlockingStub.getOneTestEntity(Empty.getDefaultInstance())
        return response
    }

    fun getAllTestEntities(): Protobuff.TestEntityList? {
        val response = testEntityServiceBlockingStub.getAllTestEntities(Empty.getDefaultInstance())
        return response
    }
}
