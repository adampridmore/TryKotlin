package dojo.leeds.problem4

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class PupilDataTests {

    private val pupilReader = PupilReader()

    @Test
    fun `pupil grade`() {
        val pupil = PupilData("myName", "M", "myClass", 50, 60, 70)
        assertThat(pupil.EnglishGrade, equalTo(Grade.D))
        assertThat(pupil.MathsGrade, equalTo(Grade.C))
        assertThat(pupil.ScienceGrade, equalTo(Grade.B))
    }

    @Test
    fun `given a PupilReader then readFile should return a list of lines `() {
        val pupils = pupilReader.readPupilFile()

        System.out.println("Number of pupils " + pupils.size)
        System.out.println("Distinct pupil names " + pupils.distinctBy { (name) -> name }.size)

        System.out.println(pupils
                .groupBy { it -> it.`class` }
                .map { it -> "Class " + it.key + " Size " + it.value.size})
    }

    @Test
    fun `The number of pupils is 121`() {
        val pupils = pupilReader.readPupilFile()

        System.out.println("Number of pupils " + pupils.size)

        assertThat(pupils.size, equalTo(121))
    }

    @Test
    fun `The number of classes is 38`() {
        val pupils = pupilReader.readPupilFile()

        val numberOfClasses = pupils.distinctBy { (name) -> name }.size

        System.out.println("Distinct pupil names " + numberOfClasses)
        assertThat(numberOfClasses, equalTo(38))
    }

    @Test
    fun `The size of each class is`() {
        val pupils = pupilReader.readPupilFile()

        System.out.println(pupils
                .groupBy { it -> it.`class` }
                .map { it ->  Pair(it.key,it.value.size)}
                .sortedBy { (`class`, _) -> `class`}
                .map { (`class`, size) -> "Class ${`class`} Size ${size}"}
                .joinToString(System.lineSeparator()))
    }

    @Test
    fun `Which students were awarded 3 grade 'A's?`() {
        val pupils = pupilReader.readPupilFile()

        val gradeAPupils = pupils
                .filter { p -> p.EnglishGrade == Grade.A }
                .filter { p -> p.MathsGrade == Grade.A}
                .filter { p -> p.ScienceGrade == Grade.A}

        printPupils(gradeAPupils)

        assertThat(gradeAPupils.size, equalTo(1))
        assertThat(gradeAPupils[0].name, equalTo("Joshua"))
    }

    private fun printPupils(gradeAPupils: List<PupilData>) {
        System.out.println(gradeAPupils
                .joinToString(System.lineSeparator()))
    }

    @Test
    fun `Which students were awarded at least a 'C' in all subjects`() {
        fun atLeastC(grade: Grade) = when(grade){
            Grade.A -> true
            Grade.B -> true
            Grade.C -> true
            else -> false
        }

        val allPassPupils =
                pupilReader.readPupilFile()
                .filter { p -> atLeastC(p.EnglishGrade) }
                .filter { p -> atLeastC(p.MathsGrade) }
                .filter { p -> atLeastC(p.ScienceGrade) }

        printPupils(allPassPupils)
    }

    @Test
    fun `Which were the highest scoring Boy & Girl in each class`() {
        val (boys, girls) =
                pupilReader
                        .readPupilFile()
                        .partition { pupil -> pupil.gender == "M" }

        val highestScoring = fun(pupils : List<PupilData>) : PupilData {
            return pupils
                    .sortedByDescending { p-> p.english + p.maths + p.science }
                    .first()
        }

        println(highestScoring(boys))
        println(highestScoring(girls))
    }
}