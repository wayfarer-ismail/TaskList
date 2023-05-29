package tasklist

class TaskList {
    private val tasks: MutableList<Task> = mutableListOf()

    fun start() {

        while (true) {
            println("Input an action (add, print, edit, delete, end):")
            when (readln().trim().lowercase()) {
                "add" -> add(tasks)
                "print" -> printTasks(tasks)
                "edit" -> edit(tasks)
                "delete" -> delete(tasks)
                "end" -> break
                else -> println("The input action is invalid")
            }
        }
        println("Tasklist exiting!")
    }
}