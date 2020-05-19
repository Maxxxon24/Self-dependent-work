package data

class State (
		val studentsToWork : MutableMap<Int,MutableList<MutableList<Int>>>,
		var works : Array<Work>,
		var students : Array<Student>
)