class Journey(startPointName: String, locationString: String) {
    var currentDay : Day
    var remainingLocations : MutableList<Location> = mutableListOf()
    var visitedLocations : MutableList<Location> = mutableListOf()
    val stringFormatter : StringFormatter = StringFormatter

    init {
        parseLocationString(locationString)
        val startPoint = remainingLocations.find { location -> location.name == startPointName } ?: throw(VerifyError("Start location not found in locations list"))
        remainingLocations.remove(startPoint)
        visitedLocations.add(startPoint)
        currentDay = Day(1)
    }

    fun calculateTrip(milesPerHour: Int, distanceCalculator : DistanceCalculator = DistanceCalculator) : String {
        while(remainingLocations.size != 0) {
            val currentLocation = visitedLocations.last()
            val closestLocation = remainingLocations.minBy {
                distanceCalculator.timeBetweenTwoLocations(currentLocation, it, milesPerHour)
            }
            if(closestLocation == null) { error("Closest Location could not be determined") }
            val travelTime = distanceCalculator.timeBetweenTwoLocations(currentLocation, closestLocation, milesPerHour)
            if(travelTime >= 36000) {
                println("Journey ended early: Next leg of journey, ${currentLocation.name} to ${closestLocation.name}, will take more than a full travel day\n")
                break
            }
            else if(currentDay.timeRemaining < travelTime) {
                currentDay = Day(currentDay.count + 1)
                travelToLocation(closestLocation, travelTime)
            } else {
                travelToLocation(closestLocation, travelTime)
            }
        }
        return printResult()
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
        if(remainingLocations.remove(destination)) {
            visitedLocations.add(destination)
        } else {
            throw(Error("Can't find destination in remaining location list"))
        }
    }

    private fun printResult() : String {
        val resultList : MutableList<String> = mutableListOf(
            "Total journey time: ${currentDay.dayToString()}\n",
            "Final Destination: ${visitedLocations.last().name}\n",
            "Skipped Shops:\n${stringFormatter.locationToString(remainingLocations)}",
            "Shops Visited:\n${stringFormatter.locationToString(visitedLocations)}"
        )
        return resultList.joinToString(separator = "\n")
    }
}
