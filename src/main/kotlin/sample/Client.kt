package sample

import com.akra.sample.proficiency.service.TestEntityServiceGrpc
import io.grpc.ManagedChannelBuilder

    fun main() {
        val channel = ManagedChannelBuilder.forAddress("localhost", 9090)
            .usePlaintext()
            .build()

        val stub = TestEntityServiceGrpc.newBlockingStub(channel)


        val responseOne = stub.getOneTestEntity(com.google.protobuf.Empty.getDefaultInstance())
        println("Received: ${responseOne.entity}")

        val responseAll = stub.getAllTestEntities(com.google.protobuf.Empty.getDefaultInstance())
        println("Received: ")
        responseAll.entitiesList.forEach { println(it) }

        channel.shutdown()
    }
