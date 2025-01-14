package commands

import services.{IndexService, FileService}
import utils.HashUtils
import java.io.File

object AddCommand {
  def execute(fileName: String): Unit = {
    // Read the current branch from the HEAD file inside .wegit
    val currentBranch = FileService.readFile("./.wegit/HEAD").trim

    // Determine the path to the current branch's directory (e.g., ./main)
    val currentBranchPath = s"./$currentBranch"
    val stagingArea = IndexService.loadStagingArea(currentBranchPath)

    if (fileName == ".") {
      // Add all files in the current branch directory (main) excluding .wegit
      val files = new File(currentBranchPath).listFiles.filter(f => f.isFile && !f.getPath.contains(".wegit"))
      files.foreach { file =>
        val hash = HashUtils.hashFile(file.getPath)
        val updatedStagingArea = stagingArea.copy(
          addedFiles = stagingArea.addedFiles.updated(file.getPath, hash)
        )
        IndexService.addToTracked(currentBranchPath, file.getPath)
        println(s"Added ${file.getPath}")
        IndexService.saveStagingArea(currentBranchPath, updatedStagingArea)
      }
    } else {
      // Add specific file in the current branch directory (main)
      val file = new File(s"$currentBranchPath/$fileName")
      if (file.exists() && file.isFile && !file.getPath.contains(".wegit")) {
        val hash = HashUtils.hashFile(file.getPath)
        val updatedStagingArea = stagingArea.copy(
          addedFiles = stagingArea.addedFiles.updated(file.getPath, hash)
        )
        IndexService.addToTracked(currentBranchPath, file.getPath)
        println(s"Added $fileName")
        IndexService.saveStagingArea(currentBranchPath, updatedStagingArea)
      } else {
        println(s"File $fileName does not exist or is inside .wegit.")
      }
    }
  }
}
