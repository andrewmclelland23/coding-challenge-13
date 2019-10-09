import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import kotlin.math.floor

class Day(val count: Int, var timeRemaining: Int = 36000) {

    fun deductTime(time: Int) {
        timeRemaining -= time
    }

    fun dayToString() : String {
        val secondsElapsed = 36000 - timeRemaining
        val days = count - 1
        println(timeRemaining)
        val hours = floor((secondsElapsed)/3600.0)
        val minutes = floor((secondsElapsed - (hours * 3600))/60)

        return "Journey Time: $days days, $hours hours and $minutes minutes"
    }
}