package dojo.leeds.problem4

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class PupilDataTests {
    private val pupils = PupilReader().readPupilFile()

    private fun <T> List<T>.printLines() {
        System.out.println(this.joinToString(System.lineSeparator()))
    }

    @Test
    fun `pupil grade`() {
        val pupil = PupilData("myName", "M", "myClass", 50, 60, 70)
        assertThat(pupil.EnglishGrade, equalTo(Grade.D))
        assertThat(pupil.MathsGrade, equalTo(Grade.C))
        assertThat(pupil.ScienceGrade, equalTo(Grade.B))
    }

    @Test
    fun `given a PupilReader then readFile should return a list of lines `() {
        System.out.println("Number of pupils " + pupils.size)
        System.out.println("Distinct pupil names " + pupils.distinctBy { (name) -> name }.size)

        pupils
                .groupBy { it -> it.`class` }
                .map { it -> "Class " + it.key + " Size " + it.value.size }
                .printLines()
    }

    @Test
    fun `The number of pupils is 121`() {
        System.out.println("Number of pupils " + pupils.size)

        assertThat(pupils.size, equalTo(121))
    }

    @Test
    fun `The number of classes is 38`() {
        val numberOfClasses = pupils.distinctBy { (name) -> name }.size

        System.out.println("Distinct pupil names " + numberOfClasses)
        assertThat(numberOfClasses, equalTo(38))
    }

    @Test
    fun `The size of each class is`() {
        pupils
                .groupBy { it -> it.`class` }
                .map { it -> Pair(it.key, it.value.size) }
                .sortedBy { (`class`, _) -> `class` }
                .map { (`class`, size) -> "Class ${`class`} Size ${size}" }
                .printLines()
    }

    @Test
    fun `Which class has the highest average score per pupil`() {
        val r = fun(fst: Pair<Int, Int>, snd: Pair<Int, Int>): Pair<Int, Int> {
            return Pair(fst.first + snd.first, fst.second + snd.second)
        }

        val averageClassScore =
                pupils
                        .groupBy { p -> p.`class` }
                        .map { clas ->
                            val (gradeCount, gradeTotal) = clas.value
                                    .map { p -> Pair(3, p.science + p.maths + p.english) }
                                    .reduce { a, b -> r(a, b) }
                            val averageGrade = (gradeTotal / gradeCount)
                            Pair(clas.key, averageGrade)
                        }
                        .sortedBy { p -> p.first }

        averageClassScore.printLines()
    }

    @Test
    fun `Which students were awarded 3 grade 'A's?`() {
        val gradeAPupils = pupils
                .filter { p -> p.EnglishGrade == Grade.A }
                .filter { p -> p.MathsGrade == Grade.A }
                .filter { p -> p.ScienceGrade == Grade.A }

        gradeAPupils.printLines()

        assertThat(gradeAPupils.size, equalTo(1))
        assertThat(gradeAPupils[0].name, equalTo("Joshua"))
    }

    @Test
    fun `Which students were awarded at least a 'C' in all subjects`() {
        fun atLeastC(grade: Grade) = when (grade) {
            Grade.A -> true
            Grade.B -> true
            Grade.C -> true
            else -> false
        }

        val allPassPupils =
                pupils
                        .filter { p -> atLeastC(p.EnglishGrade) }
                        .filter { p -> atLeastC(p.MathsGrade) }
                        .filter { p -> atLeastC(p.ScienceGrade) }

        allPassPupils.printLines()
    }

    @Test
    fun `Which were the highest scoring Boy & Girl in each class`() {
        val (boys, girls) =
                pupils.partition { pupil -> pupil.gender == "M" }

        val highestScoring = fun(pupils: List<PupilData>): PupilData {
            return pupils
                    .sortedByDescending { p -> p.english + p.maths + p.science }
                    .first()
        }

        println(highestScoring(boys))
        println(highestScoring(girls))
    }
}