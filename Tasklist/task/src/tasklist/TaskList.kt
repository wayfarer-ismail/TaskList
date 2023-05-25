package tasklist

object TaskList {
    private val tasks: MutableList<Task> = mutableListOf()

    fun start() {

        while (true) {
            println("Input an action (add, print, end):")
            when (readln().trim().lowercase()) {
                "add" -> add()
                "print" -> printTasks()
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
        else {
            this.tasks.add(Task(priority, date, time, inputTasks))
        }

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
        } else {
            date
        }
    }

    private fun isValidDate(date: String): Boolean =
        date.matches(Regex("20([3-9]\\d|2[2-9])-(\\d|1[0-2])-(\\d|[12]\\d|3[01])"))

    private fun readTime(): String {
        println("Input the time (hh:mm):")
        val time = readln().trim()
        return if (!time.matches(Regex("\\d?\\d:\\d?\\d"))) {
            println("The time is invalid")
            readTime()
        } else
            time
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