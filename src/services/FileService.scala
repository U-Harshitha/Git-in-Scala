package services
import java.nio.file.{Files, Paths, StandardCopyOption}
import java.io.File

object FileService {
    def createDirectory(path: String): String = {
        val dir = new File(path)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        dir.getPath
    }

    def createFile(path: String): Unit = {
        val file = new File(path)
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    def writeFile(path: String, content: String): Unit = {
        val file = new File(path)
        val writer = new java.io.PrintWriter(file)
        writer.write(content)
        writer.close()
    }

    def readFile(path: String): String = {
        val file = new File(path)
        if (file.exists()) {
            scala.io.Source.fromFile(file).mkString
        } else {
            ""
        }
    }

    def fileExists(path: String): Boolean = {
        val file = new File(path)
        file.exists()
    }
     def copyDirectory(sourceDir: String, destDir: String): Unit = {
    val source = new File(sourceDir)
    val dest = new File(destDir)

    if (!dest.exists()) {
      dest.mkdirs()  // Create destination directory if it doesn't exist
    }

    // Copy all files and subdirectories recursively
    source.listFiles().foreach { file =>
      if (file.isDirectory) {
        // Recursively copy subdirectories
        copyDirectory(file.getPath, s"${dest.getPath}/${file.getName}")
      } else {
        // Copy files
        Files.copy(file.toPath, Paths.get(s"${dest.getPath}/${file.getName}"), StandardCopyOption.REPLACE_EXISTING)
      }}}
}
