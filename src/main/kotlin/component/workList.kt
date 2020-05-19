package component

import data.Work
import data.Student
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.functionalComponent
import redux.*
import store
import kotlin.browser.document

interface WorkListProps: RProps {
    var test:Array<Work>
    var students:Array<Student>
}

fun workListFC(header: String) =
        functionalComponent<WorkListProps> {
            div("container tableEditProps"){
                h2 { +header }
                table {
                    tr{
                        th { +"#" }
                        th { +"Work" }
                        th { +"Date" }
                    }
                    it.test.mapIndexed{testIndex, test ->
                        tr{
                            td { +"$testIndex" }
                            td{
                                +test.name
                            }
                            td{
                                +test.date.toLocaleDateString()
                            }
                        }
                    }
                    tr(){
                        td("lastString"){
                            +"NEW"
                            attrs.colSpan = "3"
                            input(InputType.text) {
                                attrs.id = "newWork"
                            }
                            input(InputType.date) {
                                attrs.id = "newWorkDate"
                            }
                            button {
                                +"submit"
                                attrs.id = "newWorkSubmit"
                                attrs.onClickFunction = {
                                    val newWork = (document.getElementById("newWork") as HTMLInputElement).value
                                    val newDate = (document.getElementById("newWorkDate") as HTMLInputElement).value
                                    store.dispatch(AddWork(newWork,newDate))
                                }
                            }
                        }
                    }
                }
            }
        }

fun RBuilder.workList(
        test: Array<Work>,
        students:Array<Student>,
        header: String
) = child(
        withDisplayName(header, workListFC(header))
){
    attrs.test = test
    attrs.students = students
}

