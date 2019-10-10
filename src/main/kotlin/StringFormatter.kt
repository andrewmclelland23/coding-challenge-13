object StringFormatter {
    fun locationToString(locations: MutableList<Location>) : String {
        var result = ""
        locations.forEach { result += "${it.name}\n" }
        return result
    }
}