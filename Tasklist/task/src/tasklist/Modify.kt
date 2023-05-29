package tasklist

object Modify {
    fun edit(tasks: MutableList<Task>) {
        val taskNumber = getTaskNumber(tasks)
        if (taskNumber == -1) {
            println("No tasks have been input")
            return
        }
        val task = tasks[taskNumber - 1]
        println("Input a field to edit (priority, date, time, task):")
        when (readln().trim().lowercase()) {
            "priority" -> task.priority = TaskList.readPriority()
            "date" -> task.date = TaskList.readDate()
            "time" -> task.time = TaskList.readTime()
            "task" -> task.tasks = TaskList.readTasks()
            else -> println("Invalid field")
        }

        println("The task is edited")
    }

    fun delete(tasks: MutableList<Task>) {
        val taskNumber = getTaskNumber(tasks)
        if (taskNumber == -1) {
            println("No tasks have been input")
        } else {
            tasks.removeAt(taskNumber - 1)
            println("The task is deleted")
        }
    }

    /**
     * ask the user to input the task number and validate the input
     * @return -1 if tasks is empty, else return the task number
     */
    private fun getTaskNumber(tasks: MutableList<Task>): Int {
        if (tasks.isEmpty()) {
            return -1
        }

        View.printTasks(tasks)
        println("Input the task number (1-${tasks.size}):")
        var taskNumber = readln().trim()
        while (!taskNumber.matches(Regex("\\d+")) && taskNumber.toInt() !in 1..tasks.size) {
            println("Invalid task number")
            println("Input the task number (1-${tasks.size}):")
            taskNumber = readln().trim()
        }
        return taskNumber.toInt()
    }
}