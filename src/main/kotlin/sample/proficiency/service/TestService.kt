package com.akra.sample.proficiency.service

import com.akra.sample.proficiency.entity.TestEntity
import com.akra.sample.proficiency.repository.TestProtobufRepository
import com.akra.sample.proficiency.repository.TestRepository
import org.springframework.stereotype.Service

@Service
class TestService(
    private val repository: TestRepository,
) {
    fun getOne(): TestEntity? = repository.findOne()

    fun getAll(): Collection<TestEntity> = repository.findAll().values

    fun setDummy(size: Int) {
        repository.setDummy(size)
    }
}

@Service
class TestProtobufService(
    private val repository: TestProtobufRepository,
) {
    fun getOne(): Protobuff.TestEntity? = repository.findOne()

    fun getAll(): Protobuff.TestEntityList? {
        val items = repository.findAll().values
        return Protobuff.TestEntityList
            .newBuilder()
            .addAllEntities(items)
            .build()
    }

    fun setDummy(size: Int) {
        repository.setDummy(size)
    }
}
