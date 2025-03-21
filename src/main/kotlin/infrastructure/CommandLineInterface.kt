package infrastructure

import application.Application
import java.util.*

class CommandLineInterface : RobotCommandAdapter {

    private val application = Application()

    override fun sendInstructionsToApplication(input: String) {
        val scanner = Scanner(System.`in`)
        println("Factory Robot Clean Up - CLI")
        println("Introduce las instrucciones del robot (como texto): ")

        val input = buildString {
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                if (line.isNullOrBlank()) {
                    break
                }
                appendLine(line)
            }
        }

        try {
            val result = application.executeCleanUp(input)

            println("Resultados del movimiento del robot:")
            result.forEach { println(it.toString()) }
        } catch (e: Exception) {
            println("Ocurrió un error al procesar las instrucciones del robot: ${e.message}")
        } finally {
            scanner.close()
        }
    }
}
