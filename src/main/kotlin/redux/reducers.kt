package redux

import data.*
import kotlin.js.Date

fun changeReducer(state: State, action: RAction) =
    when (action) {
        is ChangeName -> State(
                state.studentsToWork,
                state.works,
                changeStudentName(state.students,action.studentIndex,action.newName)
        )
        is RemoveStudentsWork -> State(
                removeStudentsWork(state.studentsToWork,action.studentIndex,action.workIndex),
                state.works,
                state.students
        )
        is ChangeMark -> State(
                changeMark(state.studentsToWork,action.studentIndex,action.workIndex,action.newMark),
                state.works,
                state.students
        )
        is ChangeDate -> State(
                state.studentsToWork,
                changeDate(state.works,action.workIndex,action.newDate),
                state.students
        )
        is AddWork -> State(
                state.studentsToWork,
                addWork(state.works,action.newWork,action.newDate),
                state.students
        )
        is AddWorkToStudent -> State(
                addToStudent(state.studentsToWork,action.studentIndex,action.newWorkIndex),
                state.works,
                state.students
        )
        else -> state
    }

fun addToStudent(studentsToWork:MutableMap<Int,MutableList<MutableList<Int>>>,
                  studentIndex:Int,
                  newWorkIndex: Int ): MutableMap<Int,MutableList<MutableList<Int>>>{
    val temp = studentsToWork
    temp[studentIndex]!![0].add(newWorkIndex)
    temp[studentIndex]!![1].add(2)
    return temp
}

fun addWork(works : Array<Work>,newWork:String,newDate:String): Array<Work>{
    var temp = works
    temp+= Work(newWork, Date(newDate.substringBefore("-").toInt(),newDate.substringAfterLast("-").toInt(),newDate.substringAfterLast("-").toInt()))
    return temp
}

fun changeDate(works : Array<Work>,workIndex: Int,newDate:String): Array<Work>{
    works[workIndex].date = Date(newDate.substringBefore("-").toInt(),newDate.substringAfterLast("-").toInt(),newDate.substringAfterLast("-").toInt())
    return works
}

fun changeStudentName(students : Array<Student>,studentIndex:Int,newName:String):Array<Student>{
    students[studentIndex] = Student(newName.substringBefore(" "),newName.substringAfter(" "))
    return students
}

fun removeStudentsWork(studentsToWork:MutableMap<Int,MutableList<MutableList<Int>>>,studentIndex: Int,workIndex:Int):MutableMap<Int,MutableList<MutableList<Int>>>{
    val temp = studentsToWork
    temp[studentIndex]!![0].removeAt(workIndex)
    temp[studentIndex]!![1].removeAt(workIndex)
    return temp
}
fun changeMark(studentsToWork:MutableMap<Int,MutableList<MutableList<Int>>>,studentIndex: Int,workIndex:Int,newMark:Int):MutableMap<Int,MutableList<MutableList<Int>>>{
    val temp = studentsToWork
    temp[studentIndex]!![1][workIndex] = newMark
    return temp
}