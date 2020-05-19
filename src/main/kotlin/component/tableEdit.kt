package component

import data.Work
import data.Student
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.functionalComponent
import redux.*
import store
import kotlin.browser.document

interface TableEditProps: RProps {
	var test:Array<Work>
	var students:Array<Student>
}

fun tableEditFC(header: String) =
		functionalComponent<TableEditProps> {
			div("container tableEdit"){
				h2 { +header }
				table {
					tr{
						th { +"#" }
						th { +"Students" }
						th { +"Work" }
						th { +"Mark" }
						th { +"WorkDate" }
					}
					store.getState().students.mapIndexed {  studentIndex, student ->
						tr {
							attrs.id = "row>$studentIndex<"
							td{//#
								+"$studentIndex"
							}
							td {//Students
								+student.toString()
							}
							td {//Work
								store.getState().studentsToWork[studentIndex]!![0].mapIndexed{i,j ->
									div("tableContainer"){
										+"${store.getState().works[j]}"
										button {
											+"del"
											attrs.id = "$i|$j"
											attrs.onClickFunction = {
												store.dispatch(RemoveStudentsWork(studentIndex,i))
											}
										}
									}
								}
							}
							td {//mark
								store.getState().studentsToWork[studentIndex]!![1].mapIndexed{i,j ->
									div("tableContainer"){
										+"$j"
										input(InputType.number){
											attrs.defaultValue = "0"
											attrs.id = "changeMark$i|$j"
										}
										button{
											+"change"
											attrs.onClickFunction = changeMark(i, j, studentIndex)
										}
									}
								}
							}
							td {//WorkDate
								store.getState().studentsToWork[studentIndex]!![0].mapIndexed { i,j ->
									div("tableContainer"){
										+store.getState().works[j].date.toLocaleDateString()
										input(InputType.date){
											attrs.defaultValue = "0"
											attrs.id = "changeDate$i|$j"
										}
										button{
											+"change"
											attrs.onClickFunction = changeDate(i, j)
										}
									}
								}
							}
							td{
								attrs.colSpan = "3"
								input(InputType.number) {
									attrs.id = "addWork>$studentIndex"
									attrs.max = "10"
								}

								button {
									+"submit"
									attrs.id = "addWorkSubmit>$studentIndex"
									attrs.onClickFunction = addWorkToStudent(studentIndex)
								}
							}
						}
					}
				}
			}
		}

private fun changeMark(i: Int, j: Int, studentIndex: Int): (Event) -> Unit {
	return {
		val newMark = (document.getElementById("changeMark$i|$j") as HTMLInputElement).value.toInt()
		store.dispatch(ChangeMark(studentIndex, i, newMark))
	}
}

private fun changeDate(i: Int, j: Int): (Event) -> Unit {
	return {
		val newDate = (document.getElementById("changeDate$i|$j") as HTMLInputElement).value
//		console.log(newDate)
		store.dispatch(ChangeDate(j, newDate))
	}
}

private fun addWorkToStudent(studentIndex: Int): (Event) -> Unit {
	return {
		var newWorkIndex = (document.getElementById("addWork>$studentIndex") as HTMLInputElement).value.toInt()
		newWorkIndex = if (newWorkIndex < store.getState().works.size) {
			newWorkIndex
		} else {
			store.getState().works.size - 1
		}
		store.dispatch(AddWorkToStudent(studentIndex, newWorkIndex))
	}
}

fun RBuilder.tableEdit(
		test: Array<Work>,
		students:Array<Student>,
		header: String
) = child(
		withDisplayName(header, tableEditFC(header))
){
	attrs.test = test
	attrs.students = students
}

