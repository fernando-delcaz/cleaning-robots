package infrastructure

import application.Application
import java.util.Scanner

class CommandLineInterface {

    private val application = Application()

    fun start() {
        val scanner = Scanner(System.`in`)
        println("Factory Robot Clean Up - CLI")
        println("Introduce las instrucciones del robot (como texto): ")

        val input = buildString {
            var line: String?
            while (true) {
                line = scanner.nextLine()
                if (line.isNullOrBlank()) {
                    break
                }
                appendLine(line)
            }
        }

        val result = application.executeCleanUp(input)

        println("Resultados del movimiento del robot:")
        result.forEach { println(it) }

        scanner.close()
    }
}
