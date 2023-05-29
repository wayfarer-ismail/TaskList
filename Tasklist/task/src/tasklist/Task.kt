package tasklist

data class Task(var priority: String, var date: String, var time: String, var tasks: MutableList<String>) {
    constructor(tasks: MutableList<String>): this("0", "NA", "NA", tasks)

    private fun formatDate(): String {
        val (year, month, day) = date.split("-")
        return "$year-${month.padStart(2, '0')}-${day.padStart(2, '0')}"
    }

    private fun formatTime(): String {
        val (hour, minute) = time.split(":")
        return "${hour.padStart(2, '0')}:${minute.padStart(2, '0')}"
    }

    override fun toString(): String {
        return "${formatDate()} ${formatTime()} $priority\n   ${tasks.joinToString("\n   ")}"
    }
}