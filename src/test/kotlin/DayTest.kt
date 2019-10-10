import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.sql.Time

internal class DayTest {

    @Test
    fun dayInitialisedWithCountValue() {
        assertEquals(1, Day(1).count)
    }

    @Test
    fun dayInitialisedWith10Hours() {
        assertEquals(36000, Day(1).timeRemaining)
    }

    @Test
    fun deductTimeReducesTimeRemaining() {
        val day = Day(1)
        day.deductTime(60)
        assertEquals(35940, day.timeRemaining)
    }

    @Test
    fun `dayToString correctly formats seconds into a string of day, hours and minutes`() {
        val day = Day(1)
        day.deductTime(3932)
        assertEquals("0 days, 1 hours and 6 minutes", day.dayToString())
    }
}

