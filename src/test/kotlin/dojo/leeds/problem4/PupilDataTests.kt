package dojo.leeds.problem4

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class PupilDataTests {

    private val pupilReader = PupilReader()

    @Test
    fun `pupil grade`() {
        val pupil = PupilData("myName", "M", "myClass", 50, 60, 70)
        assertThat(pupil.EnglishGrade, equalTo("D"))
        assertThat(pupil.MathsGrade, equalTo("C"))
        assertThat(pupil.ScienceGrade, equalTo("B"))
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
                .filter { p -> p.EnglishGrade == "A" }
                .filter { p -> p.MathsGrade == "A" }
                .filter { p -> p.ScienceGrade == "A" }

        System.out.println(gradeAPupils
                .joinToString(System.lineSeparator()))

        assertThat(gradeAPupils.size, equalTo(1))
        assertThat(gradeAPupils[0].name, equalTo("Joshua"))
    }
}