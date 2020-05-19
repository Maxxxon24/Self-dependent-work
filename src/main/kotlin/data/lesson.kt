package data

import kotlin.js.Date

data class Work(
    var name: String,
    var date:Date
){
    override fun toString(): String = name
}

val workList = arrayOf(
    Work("lab1",Date(2020,5,16)),
    Work("lab2",Date(2020,5,17)),
    Work("lab3",Date(2020,5,18)),
    Work("lab4",Date(2020,5,19)),
    Work("lab5",Date(2020,5,20)),
    Work("lab6",Date(2020,5,21)),
    Work("lab7",Date(2020,5,22)),
    Work("lab8",Date(2020,5,23)),
    Work("lab9",Date(2020,5,24)),
    Work("lab10",Date(2020,5,25))
)