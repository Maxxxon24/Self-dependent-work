package data

data class Student (
    val firstname: String,
    val surname: String
) {
    override fun toString(): String =
        "$firstname $surname"
}

val studentList =
    arrayOf(
        Student("Walter", "White"),
        Student("Jessie", "Pinkman"),
        Student("Gustavo", "Fring")
    )