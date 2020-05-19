package data

var studentsToWork:MutableMap<Int,MutableList<MutableList<Int>>> =
	mutableMapOf(
		0 //student index
				to mutableListOf(mutableListOf<Int>(1,2,3),//0 - work indices
						   mutableListOf<Int>(5,5,2)),//1 - marks

		1 to mutableListOf(mutableListOf<Int>(0,5,9),
						   mutableListOf<Int>(2,2,2)),

		2 to mutableListOf(mutableListOf<Int>(0,1,2,3,4,5,6,7,8,9),
						   mutableListOf<Int>(5,2,2,3,5,5,5,3,4,4))
	)
