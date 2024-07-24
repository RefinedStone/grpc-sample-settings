package com.akra.sample.proficiency.repository

import com.akra.sample.proficiency.entity.TestEntity
import com.akra.sample.proficiency.entity.TestEntity.Companion.generateDummyData
import com.akra.sample.proficiency.entity.TestEntity.Companion.protoToKotlin
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
        return repository.values.firstOrNull()
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
        return repository.values.firstOrNull()
    }

    fun setDummy(size: Int) {
        generateDummyData(testEntitySize = size, memberSize = 10, productSize = 10).forEach { add(it) }
    }
}
