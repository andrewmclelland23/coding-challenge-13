import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class DistanceCalculatorTest {

    val distanceCalculator = DistanceCalculator

    val loc1 = mockk<Location>() {
        every { longitude } returns 51.449075
        every { latitude } returns -0.157375
    }

    val loc2 = mockk<Location>() {
        every { longitude } returns 51.139290
        every { latitude } returns 0.329195
    }

    @Test
    fun timeBetweenTwoLocations30MilesApartAt30Mph() {
        assertTrue(distanceCalculator.timeBetweenTwoLocations(loc1, loc2, 30) == 3600)
    }
    @Test
    fun timeBetweenTwoLocations30MilesApartAt60Mph() {
        assertTrue(distanceCalculator.timeBetweenTwoLocations(loc1, loc2, 60) == 1800)
    }
}