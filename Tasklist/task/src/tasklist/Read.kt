package tasklist

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeParseException

/**
 * ask the user to input the task priority and validate the input
 * C = Critical, H = High, N = Normal, L = Low
 */
fun readPriority(): String {
    var priority: String
    do {
        println("Input the task priority (C, H, N, L):")
        priority = readln().trim().uppercase()
    } while (!priority.matches(Regex("[CHNL]")))
    return priority
}

fun readDate(): String {
    println("Input the date (yyyy-mm-dd):")

    return try {
        val input: String = readln().trim()
        val date = LocalDate.parse(input.formatDate())
        date.toString()
    } catch (e: DateTimeParseException) {
        println("The input date is invalid")
        readDate()
    }
}

private  fun String.formatDate(): String {
    if (!this.matches(Regex("\\d+-\\d+-\\d+")))
        throw DateTimeParseException("Invalid date", this, 0)
    val (year, month, day) = this.split("-")
    return "$year-${month.padStart(2, '0')}-${day.padStart(2, '0')}"
}

fun readTime(): String {
    println("Input the time (hh:mm):")
    val input = readln().trim()
    return try {
        val time = LocalTime.parse(input.formatTime())
        time.toString()
    } catch (e: DateTimeParseException) {
        println("The input time is invalid")
        readTime()
    }
}

private fun String.formatTime(): String {
    if (!this.matches(Regex("\\d+:\\d+")))
        throw DateTimeParseException("Invalid time", this, 0)
    val (hour, minute) = this.split(":")
    return "${hour.padStart(2, '0')}:${minute.padStart(2, '0')}"
}

fun readTasks(): MutableList<String> {
    println("Input a new task (enter a blank line to end):")

    val inputTasks = mutableListOf<String>()
    while (true) {
        val task = readln().trim()
        if (task.isBlank()) break
        inputTasks.add(task)
    }
    return inputTasks
}

fun readFieldOption(): String {
    println("Input a field to edit (priority, date, time, task):")
    val input = readln().trim().lowercase()
    return if (input.matches(Regex("priority|date|time|task"))) {
        input
    } else {
        println("Invalid field")
        readFieldOption()
    }
}

/**
 * ask the user to input the task number and validate the input
 * @return -1 if tasks is empty, else return the task number
 */
fun readTaskNumber(tasks: MutableList<Task>): Int {
    if (tasks.isEmpty()) {
        return -1
    }

    printTasks(tasks)
    println("Input the task number (1-${tasks.size}):")
    var taskNumber = readln().trim()
    while (!taskNumber.matches(Regex("\\d+")) || taskNumber.toInt() !in 1..tasks.size) {
        println("Invalid task number")
        println("Input the task number (1-${tasks.size}):")
        taskNumber = readln().trim()
    }
    return taskNumber.toInt()
}

fun String.reply() = println(this).run { readln() }