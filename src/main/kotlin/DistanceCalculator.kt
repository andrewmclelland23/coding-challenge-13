import com.sfeatherstone.geodesy.LatLon
import com.sfeatherstone.geodesy.spherical.distanceTo
import kotlin.math.roundToInt

object DistanceCalculator {
    fun timeBetweenTwoLocations(loc1: Location, loc2: Location, milesPerHour: Int) : Int {
        val distanceKm = LatLon(loc1.longitude, loc1.latitude).distanceTo(LatLon(loc2.longitude, loc2.latitude))
        val distanceMiles = distanceKm * 0.000621371
        return (distanceMiles/milesPerHour*3600).roundToInt()
    }
}