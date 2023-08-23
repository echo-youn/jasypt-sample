import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JasyptTest {
    private val message = "It's My Super Secret."

    private val config = SimpleStringPBEConfig().apply {
        password = "ThisisMyPassword"
        algorithm = "PBEWITHHMACSHA512ANDAES_256"
        poolSize = 1
        saltGenerator = RandomSaltGenerator()
        ivGenerator = RandomIvGenerator()
    }

    private val jasypt = PooledPBEStringEncryptor().apply {
        setConfig(config)
    }

    @Test
    fun encryptTest() {
        val e1 = jasypt.encrypt(message)
        val e2 = jasypt.encrypt(message)
        val e3 = jasypt.encrypt(message)
        val d1 = jasypt.decrypt(e1)
        val d2 = jasypt.decrypt(e2)
        val d3 = jasypt.decrypt(e3)
        println("e1: $e1, d1: $d1")
        println("e2: $e2, d2: $d2")
        println("e3: $e3, d3: $d3")
        println("e4: ${jasypt.decrypt("M4eCzVUMLtlAXAhPeOeZQ1MktazYmEx3xsRUUfjDeD0mEENyGn403ctKwhALDqhvMLqL+ijX9kfEpbAEDupWAA==")}")
        assert(e1 != e2 && e2 != e3 && e1 != e3)
        assert(d1 == d2 && d2 == d3 && d1 == d3 && d1 == message)
    }

    @Disabled
    @Test
    fun decryptTest() {
        assertDoesNotThrow {
            println("original: ${jasypt.decrypt("")}")
        }
    }
}