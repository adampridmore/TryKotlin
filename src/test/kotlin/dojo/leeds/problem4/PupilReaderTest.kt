package dojo.leeds.problem4

import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class PupilReaderTest {

    val pupilReader = PupilReader()

    @Test
    fun `pupil grade`() {
        val pupil = PupilData("myName", "M", "myClass", 50, 60, 70)
        assertThat(pupil.EnglishGrade, equalTo("D"))
        assertThat(pupil.MathsGrade, equalTo("C"))
        assertThat(pupil.ScienceGrade, equalTo("B"))
    }

    @Test
    fun `given a PupilReader then readFile should return a list of lines `() {
        val lines = pupilReader.readPupilFile()
        assertThat(lines, not(nullValue()))
        assertThat(lines.count(), equalTo(122))

        //Name, Gender, Class, English, Maths, Science
        val pupils =
                lines
                        .drop(1)
                        .map { it -> it.split(",").map { it-> it.trim() }}
                        .map { it ->
                            PupilData(it[0], it[1], it[2],Integer.parseInt(it[3]),Integer.parseInt(it[4]),Integer.parseInt(it[5]))}

        System.out.println("Number of pupils " + pupils.size)
        System.out.println("Distinct pupil names " + pupils.distinctBy { (name) -> name }.size)

        System.out.println(pupils
                .groupBy { it -> it.`class` }
                .map { it -> "Class " + it.key + " Size " + it.value.size})
    }
}