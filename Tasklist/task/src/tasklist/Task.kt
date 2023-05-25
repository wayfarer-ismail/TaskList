package tasklist

data class Task(val priority: String, val date: String, val time: String, val tasks: MutableList<String>) {
    constructor(tasks: MutableList<String>): this("0", "NA", "NA", tasks)

    override fun toString(): String {
        return "$priority $date\n   ${tasks.joinToString("\n   ")}"
    }
}
