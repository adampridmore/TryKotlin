package dojo.leeds.problem4

class PupilReader {
    private fun readFileLines(): List<String> {
        return this::class.java.classLoader
                .getResourceAsStream("endOfyearResults.csv")
                .bufferedReader().readLines()
    }

    fun readPupilFile(): List<PupilData> {
        val valuesToPupilData = fun(values: List<String>): PupilData {
            return PupilData(
                    values[0],
                    values[1],
                    values[2],
                    Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]),
                    Integer.parseInt(values[5]))
        }

        return readFileLines()
                .drop(1)
                .map { line -> line.split(",").map { value -> value.trim() } }
                .map { values -> valuesToPupilData(values) }
    }
}