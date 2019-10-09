import kotlin.reflect.KClass

class Journey {
    var currentDay : Day
    var remainingLocations : MutableList<Location> = mutableListOf()
    var visitedLocations : MutableList<Location> = mutableListOf()
    var distanceCalculator = DistanceCalculator()

    constructor(startPointName: String, locationString: String) {
        parseLocationString(locationString)
        val startPoint = remainingLocations.find { location -> location.name == startPointName } ?: throw(VerifyError("Start location not found in locations list"))
        remainingLocations.remove(startPoint)
        visitedLocations.add(startPoint)
        currentDay = Day(1)
    }

    fun parseLocationString(locationString: String) {
        var splitString = locationString.split(",").toMutableList()
        while(splitString.size != 0) {
            remainingLocations.add(Location(
                splitString.removeAt(0),
                splitString.removeAt(0),
                splitString.removeAt(0).toDouble(),
                splitString.removeAt(0).toDouble()))
        }
    }

    fun travelToLocation(destination: Location, journeyTime: Int) {
        currentDay.deductTime(journeyTime + 1200)
        remainingLocations.remove(destination)
        visitedLocations.add(destination)
    }

    fun calculateTrip(milesPerHour: Int) : String {
        while(remainingLocations.size != 0) {
            val currentLocation = visitedLocations.last()
            val closestLocation = remainingLocations.minBy {
                distanceCalculator.timeBetweenTwoLocations(currentLocation, it, milesPerHour)
            }
            if(closestLocation == null) { error("Closest Location could not be determined") }
            val travelTime = distanceCalculator.timeBetweenTwoLocations(currentLocation, closestLocation, milesPerHour)
            if(travelTime >= 36000) {
                println("Journey ended Early: Next leg of journey will take more than the maximum possible journey time")
                break
            }
            else if(currentDay.timeRemaining < travelTime) {
                currentDay = Day(currentDay.count + 1)
                travelToLocation(closestLocation, travelTime)
            } else {
                travelToLocation(closestLocation, travelTime)
            }
        }
        return currentDay.dayToString()
    }

    fun formatResultString() : String {
        var elapsedTodaySeconds = 36000 - currentDay.timeRemaining
        println(currentDay.timeRemaining)
        val elapsedTodayHours = elapsedTodaySeconds/1200
        return "Trip Time: ${currentDay.count - 1} days, $elapsedTodayHours hours"
    }
}