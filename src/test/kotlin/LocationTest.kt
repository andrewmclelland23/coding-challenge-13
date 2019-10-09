import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class LocationTest {
    @Test
    fun constructorTakesExpectedArguments() {
        val location = Location("Head Office", "DE72 4EP", 10.0545, 0.0998)
        assertAll(
            Executable { assertEquals("Head Office", location.name) },
            Executable { assertEquals("DE72 4EP", location.postcode)},
            Executable { assertEquals(10.0545, location.longitude)},
            Executable { assertEquals(0.0998, location.latitude)}
        )
    }
}