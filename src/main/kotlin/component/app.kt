package component

import data.*
import hoc.withDisplayName
import react.*
import react.dom.*
import react.router.dom.*
import redux.*

interface RootProps : RProps {

    var store: Store<State, RAction, WrapperAction>
}

fun root() =
    functionalComponent<RootProps> { props ->
        header {
            h1 { navLink("") { +"HOME" } }
            nav {
                ul {
                    navLink("/table-student-work") {li { +"table student work" } }
                    navLink("/table-student-edit") {li { +"table student edit" } }
                    navLink("/table-work-list") {li { +"table work list" } }
                }
            }
        }

        switch {
            route("/table-student-work",
                exact = true,
                render = {
                    tableSW(props.store.getState().works,"table student work")
                }
            )
            route("/table-student-edit",
                exact = true,
                render = {
                    tableEdit(props.store.getState().works,props.store.getState().students, "table student date")
                }
            )
            route("/table-work-list",
                exact = true,
                render = {
                    workList(props.store.getState().works,props.store.getState().students, "table work list")
                }
            )
        }
    }


fun RBuilder.root(
    store: Store<State, RAction, WrapperAction>
) =
    child(
        withDisplayName("Root", root())
    ) {
        attrs.store = store
    }





