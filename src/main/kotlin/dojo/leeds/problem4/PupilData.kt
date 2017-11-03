package dojo.leeds.problem4

enum class Grade {
    A, B, C, D, E, F
}

data class PupilData(val name: String, val gender: String, val `class`: String, val english: Int, val maths: Int, val science: Int) {
    val EnglishGrade: Grade get() = getGrade(english)
    val MathsGrade: Grade get() = getGrade(maths)
    val ScienceGrade: Grade get() = getGrade(science)
    fun getGrade(score: Int) = when {
        score >= 80 -> Grade.A
        score >= 70 -> Grade.B
        score >= 60 -> Grade.C
        score >= 50 -> Grade.D
        score >= 45 -> Grade.E
        else -> Grade.F
    }
}