package component

import data.Work
import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent
import store

interface TableSWProps: RProps {
    var test:Array<Work>
}

fun tableSWFC(header: String) =
    functionalComponent<TableSWProps> {
        div("container tableSW"){
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
                        td{+"$studentIndex"}//#
                        td { +student.toString() }//Students
                        td {//Work
                             store.getState().studentsToWork[studentIndex]!![0].mapIndexed{ _, j ->
                                p{  +"${store.getState().works[j]}"}
                             }
                        }
                        td {//mark
                            store.getState().studentsToWork[studentIndex]!![1].mapIndexed{ _, j ->
                                p{  +"$j"}
                            }
                        }
                        td {//WorkDate
                            store.getState().studentsToWork[studentIndex]!![0].mapIndexed { _, j ->
                                p{ +store.getState().works[j].date.toLocaleDateString()}
                            }
                        }
                    }
                }
            }
        }
    }


fun RBuilder.tableSW(
    test: Array<Work>,
    header: String
) = child(
    withDisplayName(header, tableSWFC(header))
){
    attrs.test = test
}

