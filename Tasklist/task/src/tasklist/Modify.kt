package tasklist

fun add(tasks: MutableList<Task>) {
    val priority = readPriority()
    val date = readDate()
    val time = readTime()
    val inputTasks = readTasks()

    if (inputTasks.isEmpty()) println("The task is blank")
    else tasks.add(Task(priority, date, time, inputTasks))
}

fun edit(tasks: MutableList<Task>) {
    val taskNumber = readTaskNumber(tasks)
    if (taskNumber == -1) {
        println("No tasks have been input")
        return
    }
    val task = tasks[taskNumber - 1]

    when (readFieldOption()) {
        "priority" -> task.priority = readPriority()
        "date" -> task.date = readDate()
        "time" -> task.time = readTime()
        "task" -> task.tasks = readTasks()
        else -> println("Invalid field")
    }

    println("The task is changed")
}

fun delete(tasks: MutableList<Task>) {
    val taskNumber = readTaskNumber(tasks)
    if (taskNumber == -1) {
        println("No tasks have been input")
    } else {
        tasks.removeAt(taskNumber - 1)
        println("The task is deleted")
    }
}