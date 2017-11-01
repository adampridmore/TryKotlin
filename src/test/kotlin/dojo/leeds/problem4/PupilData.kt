package dojo.leeds.problem4

data class PupilData(val name: String, val gender: String, val `class`: String, val english: Int, val maths: Int, val science: Int) {
    val EnglishGrade: String get() = getGrade(english)
    val MathsGrade: String get() = getGrade(maths)
    val ScienceGrade: String get() = getGrade(science)
    fun getGrade(score: Int) = when {
        score >= 80 -> "A"
        score >= 70 -> "B"
        score >= 60 -> "C"
        score >= 50 -> "D"
        score >= 45 -> "E"
        else -> "F"
    }
}