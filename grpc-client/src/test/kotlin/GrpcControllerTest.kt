import com.akra.GrpcClientApplication
import com.akra.GrpcClientService
import com.akra.TestEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

@SpringBootTest(classes = [GrpcClientApplication::class])
class GrpcControllerTest(
    @Autowired private val grpcService: GrpcClientService,
) {
    private val webClient: WebClient =
        WebClient
            .builder()
            .baseUrl("http://localhost:8080")
            .exchangeStrategies { builder ->
                builder.codecs { codecs ->
                    codecs.defaultCodecs().maxInMemorySize(100 * 1024 * 1024) // 10MB
                }
            }
            .build()

    @Test
    fun testSetDummys() {
        val size = 100
        val response =
            webClient
                .post()
                .uri("/dummy/{size}", size)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void::class.java)
                .block()
    }

    @Test
    fun testGetOneProto() {
        val before = LocalDateTime.now()
        (0..10000).forEach {
            webClient
                .get()
                .uri("/proto")
                .accept(MediaType.APPLICATION_PROTOBUF)
                .retrieve()
                .bodyToMono(ByteArray::class.java)
                .block()
        }
        val after = LocalDateTime.now()
        println("==========")
        println(before.toString())
        println(after.toString())
        println("==========")
        // 35
    }

    @Test
    fun testGetOneJson() {
        val before = LocalDateTime.now()
        (0..10000).forEach {
            webClient
                .get()
                .uri("/json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TestEntity::class.java)
                .block()
        }
        val after = LocalDateTime.now()
        println("==========")
        println(before.toString())
        println(after.toString())
        println("==========")
        // 34
    }

    @Test
    fun testGetAllProto() {
        val before = LocalDateTime.now()
        (0..10000).forEach {
            webClient
                .get()
                .uri("/proto/all")
                .accept(MediaType.APPLICATION_PROTOBUF)
//                    .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToMono(ByteArray::class.java)
                .block()
        }
        val after = LocalDateTime.now()
        println("==========")
        println(before.toString())
        println(after.toString())
        println("==========")
        // 1 21
        // 47
    }

    @Test
    fun testGetAllJson() {
        val before = LocalDateTime.now()
        (0..10000).forEach {
            webClient
                .get()
                .uri("/json/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Array<TestEntity>::class.java)
                .block()
        }
        val after = LocalDateTime.now()
        println("==========")
        println(before.toString())
        println(after.toString())
        println("==========")
        // 2 58
    }

    @Test
    fun testGetOneGrpc() {
        val before = LocalDateTime.now()
        (0..10000).forEach {
            grpcService.getOneTestEntity()
        }
        val after = LocalDateTime.now()
        println("==========")
        println(before.toString())
        println(after.toString())
        println("==========")
        // 2
    }

    @Test
    fun testGetAllGrpc(){
        val before = LocalDateTime.now()
        (0..10000).forEach {
            grpcService.getAllTestEntities()
        }
        val after = LocalDateTime.now()
        println("==========")
        println(before.toString())
        println(after.toString())
        println("==========")
        // 7
    }
}

