package tasklist

import java.time.LocalDate

data class Task(var priority: String, var date: String, var time: String, var tasks: MutableList<String>) {
    constructor(tasks: MutableList<String>): this("0", "NA", "NA", tasks)

    fun dueTag(): String {
        return when {
            date == "NA" -> ""
            LocalDate.parse(date) == LocalDate.now() -> "T"
            LocalDate.now() < LocalDate.parse(date) -> "I"
            else -> "O"
        }
    }

    override fun toString(): String {
        return "$date $time $priority ${dueTag()}\n   ${tasks.joinToString("\n   ")}"
    }
}