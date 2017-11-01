package dojo.leeds.problem4

class PupilReader {
    private fun readFileLines(): List<String> {
        return this::class.java.classLoader
                .getResourceAsStream("endOfyearResults.csv")
                .bufferedReader().readLines()
    }

    fun readPupilFile(): List<PupilData> {
        return readFileLines()
                .drop(1)
                .map { it -> it.split(",").map { it -> it.trim() } }
                .map { it ->
                    PupilData(it[0], it[1], it[2], Integer.parseInt(it[3]), Integer.parseInt(it[4]), Integer.parseInt(it[5]))
                }
    }
}