package tasklist

object TaskList {
    private val tasks: MutableList<MutableList<String>> = mutableListOf()

    fun start() {

        while (true) {
            println("Input an action (add, print, end):")
            when (readln().trim().lowercase()) {
                "add" -> addTasks()
                "print" -> printTasks()
                "end" -> break
                else -> println("The input action is invalid")
            }
        }
        println("Tasklist exiting!")
    }

    private fun addTasks() {
        println("Input a new task (enter a blank line to end):")

        val inputTasks = mutableListOf<String>()
        while (true) {
            val task = readln().trim()
            if (task.isBlank()) break
            inputTasks.add(task)
        }

        if (inputTasks.isEmpty()) println("The task is blank")
        else tasks.add(inputTasks)
    }

    private fun printTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }
        for (i in tasks.indices) {
            println(String.format("%-2d ${tasks[i].first()}", (i+1)))
            for (item in tasks[i].subList(1, tasks[i].size)) {
                println("   $item")
            }
            println()
        }
    }
}