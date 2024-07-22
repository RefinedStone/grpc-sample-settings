package com.akra.sample.proficiency
import TestEntity
import TestEntity.Companion.generateDummyData
import TestEntity.Companion.protoToKotlin
import com.akra.sample.proficiency.service.Protobuff


import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class TestRepository {
    private val repository: MutableMap<Int, TestEntity> = ConcurrentHashMap()
    fun add(entity: TestEntity) {
        repository[entity.id] = entity
    }

    fun findById(id: Int): TestEntity? {
        return repository[id]
    }

    fun findAll(): Map<Int, TestEntity> {
        return repository.toMap()
    }

    fun findOne(): TestEntity? {
        return repository[0]
    }

    fun setDummy(size: Int) {
        generateDummyData(testEntitySize = size, memberSize = 10, productSize = 10).map {
            add(protoToKotlin(it))
        }
    }
}

@Component
class TestProtobufRepository {
    private val repository: MutableMap<Int, Protobuff.TestEntity> = ConcurrentHashMap()

    fun add(entity: Protobuff.TestEntity) {
        repository[entity.id] = entity
    }

    fun findById(id: Int): Protobuff.TestEntity? {
        return repository[id]
    }

    fun findAll(): Map<Int, Protobuff.TestEntity> {
        return repository.toMap()
    }

    fun findOne(): Protobuff.TestEntity? {
        return repository[0]
    }

    fun setDummy(size: Int) {
        generateDummyData(testEntitySize = size, memberSize = 10, productSize = 10).forEach { add(it) }
    }
}
