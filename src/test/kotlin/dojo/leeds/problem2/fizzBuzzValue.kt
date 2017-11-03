package dojo.leeds.problem2

fun fizzBuzzValue(i: Int,fizzDivisor : Int = 3, buzzDivisor : Int = 5 ) : String = when {
    i % (fizzDivisor*buzzDivisor) ==0 -> "Fizz Buzz"
    i % fizzDivisor == 0 -> "Fizz"
    i % buzzDivisor== 0 -> "Buzz"
    else -> i.toString()
}

fun fizzBuzz(i: Int): List<String> {
    return (1..i).map { it -> fizzBuzzValue(it)}
}
