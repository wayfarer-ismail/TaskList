package tasklist

object View {
    fun printTasks(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
        } else {
            for (i in tasks.indices) {
                println(String.format("%-2d %s\n", (i+1), tasks[i]))
            }
        }
    }
}