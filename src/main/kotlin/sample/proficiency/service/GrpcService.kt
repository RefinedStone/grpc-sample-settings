package com.akra.sample.proficiency.service



import com.akra.sample.proficiency.TestProtobufRepository
import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Autowired

@GrpcService
class TestEntityServiceImpl(
    @Autowired private val repository: TestProtobufRepository,
) : TestEntityServiceGrpc.TestEntityServiceImplBase() {
    override fun getOneTestEntity(
        request: Empty,
        responseObserver: StreamObserver<Protobuff.TestEntityResponse>,
    ) {
        val entity = repository.findOne() ?: Protobuff.TestEntity.getDefaultInstance()

        val response =
            Protobuff.TestEntityResponse.newBuilder()
                .setEntity(entity)
                .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun getAllTestEntities(
        request: Empty,
        responseObserver: StreamObserver<Protobuff.TestEntityList>,
    ) {
        val entities = repository.findAll().values
        val response =
            Protobuff.TestEntityList.newBuilder()
                .addAllEntities(entities)
                .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
