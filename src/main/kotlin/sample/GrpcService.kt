package com.akra
import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class TestEntityServiceImpl : TestEntityServiceGrpc.TestEntityServiceImplBase() {

    override fun getOneTestEntity(request: Empty, responseObserver: StreamObserver<Protobuff.TestEntityResponse>) {
        val testEntity = Protobuff.TestEntity.newBuilder()
            .setId(1)
            .setAddress(
                Protobuff.TestEntity.Address.newBuilder().setStreet(
                    "123 Main St",
                ).setCity("Anytown").setState("CA").setZipCode("90210").setCountry("USA").build(),
            )
            .addMembers(Protobuff.TestEntity.Member.newBuilder().setName("John Doe").setMemberId(1).build())
            .addProducts(
                Protobuff.TestEntity.Product.newBuilder().setProductId(
                    1,
                ).setName("Sample Product").setPrice(19.99).setQuantity(10).build(),
            )
            .build()

        val response = Protobuff.TestEntityResponse.newBuilder().setEntity(testEntity).build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun getAllTestEntities(request: Empty, responseObserver: StreamObserver<Protobuff.TestEntityList>) {
        val testEntity1 = Protobuff.TestEntity.newBuilder()
            .setId(2)
            .setAddress(
                Protobuff.TestEntity.Address.newBuilder().setStreet(
                    "456 Side St",
                ).setCity("Othertown").setState("TX").setZipCode("90876").setCountry("USA").build(),
            )
            .addMembers(Protobuff.TestEntity.Member.newBuilder().setName("Jane Smith").setMemberId(2).build())
            .addProducts(
                Protobuff.TestEntity.Product.newBuilder().setProductId(
                    2,
                ).setName("Another Product").setPrice(29.99).setQuantity(5).build(),
            )
            .build()

        val testEntity2 = Protobuff.TestEntity.newBuilder()
            .setId(3)
            .setAddress(
                Protobuff.TestEntity.Address.newBuilder().setStreet(
                    "789 Circle Rd",
                ).setCity("Roundtown").setState("FL").setZipCode("12345").setCountry("USA").build(),
            )
            .addMembers(Protobuff.TestEntity.Member.newBuilder().setName("Alice Johnson").setMemberId(3).build())
            .addProducts(
                Protobuff.TestEntity.Product.newBuilder().setProductId(
                    3,
                ).setName("More Product").setPrice(39.99).setQuantity(20).build(),
            )
            .build()

        val response = Protobuff.TestEntityList.newBuilder().addAllEntities(listOf(testEntity1, testEntity2)).build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
