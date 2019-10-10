import kotlin.math.floor
import kotlin.math.round

class Day(val count: Int, var timeRemaining: Int = 36000) {

    fun deductTime(time: Int) {
        timeRemaining -= time
    }

    fun dayToString() : String {
        val secondsElapsed = 36000 - timeRemaining
        val days = count - 1
        val hours = floor((secondsElapsed)/3600.0).toInt()
        val minutes = round((secondsElapsed - (hours * 3600.0))/60).toInt()

        return "$days days, $hours hours and $minutes minutes"
    }
}