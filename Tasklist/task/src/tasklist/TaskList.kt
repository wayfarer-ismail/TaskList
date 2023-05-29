package tasklist

object TaskList {
    private val tasks: MutableList<Task> = mutableListOf()

    fun start() {

        while (true) {
            println("Input an action (add, print, edit, delete, end):")
            when (readln().trim().lowercase()) {
                "add" -> add()
                "print" -> printTasks()
                "edit" -> edit()
                "delete" -> delete()
                "end" -> break
                else -> println("The input action is invalid")
            }
        }
        println("Tasklist exiting!")
    }

    private fun add() {
        val priority = readPriority()

        val date = readDate()

        val time = readTime()

        val inputTasks = readTasks()

        if (inputTasks.isEmpty()) println("The task is blank")
        else this.tasks.add(Task(priority, date, time, inputTasks))

    }

    private fun readPriority(): String {
        var priority: String
        do {
            println("Input the task priority (C, H, N, L):")
            priority = readln().trim().uppercase()
        } while (!priority.matches(Regex("[CHNL]")))
        return priority
    }

    private fun readDate(): String {
        println("Input the date (yyyy-mm-dd):")
        val date: String = readln().trim()
        return if (!isValidDate(date)) {
            println("The input date is invalid")
            readDate()
        } else date
    }

    private fun isValidDate(date: String): Boolean {
        return if (date.matches(Regex("\\d+-\\d+-\\d+"))) {
            val (year, month, day) = date.split("-")
            when (month.toInt()) {
                1, 3, 5, 7, 8, 10, 12 -> day.toInt() in 1..31
                4, 6, 9, 11 -> day.toInt() in 1..30
                2 -> {
                    if (year.toInt() % 4 == 0) day.toInt() in 1..29
                    else day.toInt() in 1..28
                }
                else -> false
            }
        } else false
    }


    private fun readTime(): String {
        println("Input the time (hh:mm):")
        val time = readln().trim()
        return if (!isValidTime(time)) {
            println("The input time is invalid")
            readTime()
        } else time
    }

    private fun isValidTime(time: String): Boolean {
        return if (time.matches(Regex("\\d+:\\d+"))) {
            val (hour, minute) = time.split(":")
            hour.toInt() in 0..23 && minute.toInt() in 0..59
        } else false
    }

    private fun readTasks(): MutableList<String> {
        println("Input a new task (enter a blank line to end):")

        val inputTasks = mutableListOf<String>()
        while (true) {
            val task = readln().trim()
            if (task.isBlank()) break
            inputTasks.add(task)
        }
        return inputTasks
    }

    private fun edit() {
        TODO("Not yet implemented")
    }

    private fun delete() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }

        printTasks()
        println("Input the task number (1-${tasks.size}):")
        var taskNumber = readln().trim()
        while (!taskNumber.matches(Regex("\\d+")) && taskNumber.toInt() !in 1..tasks.size) {
            println("Invalid task number")
            println("Input the task number (1-${tasks.size}):")
            taskNumber = readln().trim()
        }

        tasks.removeAt(taskNumber.toInt() - 1)
        println("The task is deleted")
    }

    private fun printTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
        } else {
            for (i in tasks.indices) {
                println(String.format("%-2d %s\n", (i+1), tasks[i]))
            }
        }
    }
}