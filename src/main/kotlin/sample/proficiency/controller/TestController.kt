import com.google.protobuf.MessageLite
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream

@RestController
class TestController(
    private val testService: TestService,
    private val testProtobufService: TestProtobufService,
) {
    @PostMapping("/dummy/{size}")
     fun setDummys(
        @PathVariable("size") size: Int,
    ) {
        runBlocking {
            testService.setDummy(size)
            testProtobufService.setDummy(size)
        }
    }

    @GetMapping("/proto")
     fun getOneProto(): ByteArray = testProtobufService.getOne()?.toByteArray() ?: ByteArray(0)

    @GetMapping("/json")
     fun getOneJson(): TestEntity = testService.getOne()!!

    @GetMapping("/proto/all")
     fun getAllProto(): ByteArray = testProtobufService.getAll().toByteArray()

    @GetMapping("/json/all")
     fun getAllJson(): Collection<TestEntity> = testService.getAll()
}

// List 변환을 간단하게 해주는 확장함수
fun <T : MessageLite> Collection<T>.toByteArray(): ByteArray {
    val outputStream = ByteArrayOutputStream()
    this.forEach { it.writeDelimitedTo(outputStream) }
    return outputStream.toByteArray()
}
