import java.io.*
import java.net.ServerSocket

fun main() {
    val serverSocket = ServerSocket(8080)
    println("Server started. Waiting for connections on port 8080...")

    while (true) {
        try {
            serverSocket.accept().use { clientSocket ->
                println("Client connected: ${clientSocket.inetAddress.hostAddress}")

                val inputStream = clientSocket.getInputStream()
                val reader = DataInputStream(inputStream)

                val fileName = reader.readUTF()
                println("Receiving file: $fileName")

                val content = reader.readUTF()

                File(fileName).writeText(content)
                println("File saved successfully: $fileName")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}