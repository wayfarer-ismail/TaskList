package tasklist

private const val border = "+----+------------+-------+---+---+--------------------------------------------+"
private const val header = "| N  |    Date    | Time  | P | D |                   Task                     |"
private const val RESET = "\u001B[0m"
private const val RED = "\u001B[101m"
private const val GREEN = "\u001B[102m"
private const val YELLOW = "\u001B[103m"
private const val BLUE = "\u001B[104m"

fun printTasks(tasks: List<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks have been input")
        return
    }

    println("$border\n$header\n$border")
    for (i in tasks.indices) {
        println(formatTask(i + 1, tasks[i]))
        println(border)
    }
}

private fun formatTask(index: Int, task: Task): String {
    val (priority, date, time, tasks) = task

    val pColor = getColor(priority)
    val tagColor = getColor(task.dueTag())
    val separator = "|\n" + "|    |            |       |   |   |"

    return String.format(
        "| %-2s | %s | %s | %s | %s |%s|",
        index, date, time, " ".color(pColor), " ".color(tagColor),
        tasks.joinToString(separator){
            it.chunked(44).joinToString(separator) {it.padEnd(44, ' ')}
        }
    )
}

private fun getColor(item: String): String {
    return when (item) {
        "C", "O" -> RED
        "H", "T" -> YELLOW
        "N", "I" -> GREEN
        "L" -> BLUE
        else -> RESET
    }
}

private fun String.color(color: String) = "$color$this$RESET"