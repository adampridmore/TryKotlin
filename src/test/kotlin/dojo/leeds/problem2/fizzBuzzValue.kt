package dojo.leeds.problem2


fun fizzBuzzValue(i: Int, fizzDivisor : Int = 3, buzzDivisor : Int = 7): String {
    if (i % (fizzDivisor*buzzDivisor) == 0) {
        return "Fizz Buzz"
    }

    if (i % fizzDivisor == 0) {
        return "Fizz"
    }

    if (i % buzzDivisor== 0) {
        return "Buzz"
    }

    return i.toString()
}

fun fizzBuzz(i: Int): List<String>? {
    return (1..i).map { it -> fizzBuzzValue(it)}
}
