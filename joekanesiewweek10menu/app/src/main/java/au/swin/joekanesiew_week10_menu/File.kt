package au.swin.joekanesiew_week10_menu

import java.io.File

fun main() {
    val mutableListMyWord = mutableListOf<MyData>()
    val mFile = File("E:\\GitHub\\JakeSiewJK64\\kotlin-projects\\joekanesiewweek10menu\\app\\src\\main\\java\\au\\swin\\joekanesiew_week10_menu\\input.txt")
    mFile.forEachLine {
        val temp = it.split(",")
        mutableListMyWord.add(MyData(temp[0], temp[1].toInt()))
    }
    for (i in mutableListMyWord) {
        println(i)
    }
//    print("write here: ")
//    val input = readLine()
//    print(input)
}

data class MyData (val word: String, val num: Int)
