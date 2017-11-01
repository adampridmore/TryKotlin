package dojo.leeds.problem2

fun fizzBuzzValue(i: Int): String {
    if (i % 15 == 0) {
        return "Fizz Buzz"
    }

    if (i % 3 == 0) {
        return "Fizz"
    }

    if (i % 5 == 0) {
        return "Buzz"
    }

    return i.toString()
}

fun fizzBuzz(i: Int): List<String>? {
    return (1..i).map { it -> fizzBuzzValue(it)}
}

