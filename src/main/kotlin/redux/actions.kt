package redux

class ChangeName(val studentIndex:Int, val newName:String) : RAction

class RemoveStudentsWork(val studentIndex:Int,val workIndex:Int) : RAction

class ChangeMark(val studentIndex:Int,val workIndex:Int,val newMark:Int) : RAction

class ChangeDate(val workIndex:Int,val newDate:String) : RAction

class AddWork(val newWork:String,val newDate:String) : RAction

class AddWorkToStudent(
		val studentIndex:Int,
		val newWorkIndex: Int ) : RAction