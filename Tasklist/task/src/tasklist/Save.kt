package tasklist

import java.io.File

fun load(listOfTasks: MutableList<Task>) {
    // Load JSON from file
    val file = File("tasklist.json")
    if (!file.exists()) return
    val jsonString = file.readText()
    if (jsonString.isEmpty()) return

    // Parse JSON into a mutable list of Task objects
    val jsonArray = jsonString.drop(2).dropLast(2).split("},")

    jsonArray.forEach { json ->
        val keyValuePairs = json.split(",", limit = 4)
        val priority = keyValuePairs[0].substringAfter(":").trim().removeSurrounding("\"")
        val date = keyValuePairs[1].substringAfter(":").trim().removeSurrounding("\"")
        val time = keyValuePairs[2].substringAfter(":").trim().removeSurrounding("\"")
        val tasksList = keyValuePairs[3].substringAfter(":").trim().removeSurrounding("[", "]")
            .split(",").map { it.trim().removeSurrounding("\"") }.toMutableList()
        listOfTasks.add(Task(priority, date, time, tasksList))
    }
}

/**
 * Save the task list to a json file in the current directory using json format
 */
fun save (listOfTasks: MutableList<Task>) {
    // Convert the list of tasks to a JSON-like representation
    val jsonText = buildString {
        append("[")
        listOfTasks.forEachIndexed { index, task ->
            append("{")
            append("\"priority\": \"${task.priority}\", ")
            append("\"date\": \"${task.date}\", ")
            append("\"time\": \"${task.time}\", ")
            append("\"tasks\": [${task.tasks.joinToString(", ") { "\"$it\"" }}]")
            append("}")
            if (index != listOfTasks.size - 1)
                append(", ")
        }
        append("]")
    }

    // Save the JSON-like representation to a file
    val file = File("tasklist.json")
    if (!file.exists()) file.createNewFile()
    file.writeText(jsonText)
}