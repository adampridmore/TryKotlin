package dojo.leeds.Problem3

import dojo.leeds.problem3.KAddress
import org.junit.Test

class StruffTest {
    @Test
    fun `My test`() {
        val a = KAddress("line1", "line2", "line3", "line4", "My Country", "My Postcode")

        System.out.println(a.toString())
    }
}