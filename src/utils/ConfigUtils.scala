package utils

object ConfigUtils {
    def parseConfig(filePath: String): Map[String, String] = {
        val content = FileUtils.readFile(filePath)
        content.split("\n").map { line =>
            val parts = line.split("=")
            parts(0).trim -> parts(1).trim
        }.toMap
    }

    def writeConfig(filePath: String, config: Map[String, String]): Unit = {
        val content = config.map { case (key, value) => s"$key=$value" }.mkString("\n")
        FileUtils.writeFile(filePath, content)
    }
}
