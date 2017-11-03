package dojo.leeds.problem2

fun fizzBuzzValue(i: Int,fizzDivisor : Int = 3, buzzDivisor : Int = 5  ) : String {
    val fizzable = i % fizzDivisor == 0
    val buzzable = i % buzzDivisor == 0
    return when {
        fizzable && buzzable -> "Fizz Buzz"
        fizzable -> "Fizz"
        buzzable -> "Buzz"
        else -> i.toString()
    }
}

fun fizzBuzz(i: Int): List<String> {
    return (1..i).map { it -> fizzBuzzValue(it)}
}
