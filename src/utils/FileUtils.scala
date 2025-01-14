package utils

object FileUtils {
    def createDirectory(path: String): String = {
        val dir = new java.io.File(path)
        if (!dir.exists()) dir.mkdirs()
        path
    }

    def createFile(path: String): Unit = {
        val file = new java.io.File(path)
        if (!file.exists()) {
            file.getParentFile.mkdirs()
            file.createNewFile()
        }
    }

    def writeFile(path: String, content: String): Unit = {
        createFile(path)
        val writer = new java.io.PrintWriter(path)
        writer.write(content)
        writer.close()
    }

    def readFile(path: String): String = {
        scala.io.Source.fromFile(path).mkString
    }
}
