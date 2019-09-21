package kotlin_samples


fun validate(answer: String?): Boolean =
//        answer?.get(0)?.isUpperCase() ?: false
        answer?.matches("\\d{7}".toRegex()) ?: false

fun main() {
    println(validate("1234567"))
    println(validate("123456"))
    println(validate("12345678"))
    println(validate("Abc"))
    println(validate("Abc1"))
    println(validate("11"))
    println(validate(null))
}
