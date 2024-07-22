
import com.akra.sample.proficiency.TestProtobufRepository
import com.akra.sample.proficiency.TestRepository
import com.akra.sample.proficiency.service.Protobuff
import org.springframework.stereotype.Service

@Service
class TestService(
    private val repository: TestRepository,
) {
     fun getOne(): TestEntity? {
        return repository.findOne()
    }

     fun getAll(): Collection<TestEntity> {
        return repository.findAll().values
    }

     fun setDummy(size: Int) {
        repository.setDummy(size)
    }
}

@Service
class TestProtobufService(
    private val repository: TestProtobufRepository,
) {
     fun getOne(): Protobuff.TestEntity? {
        return repository.findOne()
    }

     fun getAll(): Collection<Protobuff.TestEntity> {
        return repository.findAll().values
    }

     fun setDummy(size: Int) {
        repository.setDummy(size)
    }
}
