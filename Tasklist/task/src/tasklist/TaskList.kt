package tasklist

object TaskList {
    private val tasks = mutableListOf<String>()

    fun start() {
        println("Input the tasks (enter a blank line to end):")
        readTasks()
        printTasks()
    }

    private fun readTasks() {
        while (true) {
            val task = readln()
            if (task.isBlank()) break
            tasks.add(task)
        }
    }

    private fun printTasks() {
        for (i in tasks.indices) {
            println(String.format("%-2d ${tasks[i]}", (i+1)))
        }
    }
}