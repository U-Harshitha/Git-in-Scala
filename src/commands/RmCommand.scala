package commands

import services.IndexService

object RmCommand {
  def execute(fileName: String): Unit = {
    val currentBranchPath = "./main/.wegit" // Assuming `main` is the current branch
    val stagingArea = IndexService.loadStagingArea(currentBranchPath)

    if (fileName == ".") {
      // Remove all files in the directory
      val files = stagingArea.addedFiles.keys
      files.foreach { file =>
        // Create a new StagingArea with the file removed from addedFiles
        val updatedStagingArea = stagingArea.copy(
          addedFiles = stagingArea.addedFiles - file
        )
        IndexService.markAsDeleted(currentBranchPath, file)
        println(s"Removed $file")
        // Save the updated staging area
        IndexService.saveStagingArea(currentBranchPath, updatedStagingArea)
      }
    } else {
      // Remove specific file
      if (stagingArea.addedFiles.contains(fileName)) {
        // Create a new StagingArea with the file removed from addedFiles
        val updatedStagingArea = stagingArea.copy(
          addedFiles = stagingArea.addedFiles - fileName
        )
        IndexService.markAsDeleted(currentBranchPath, fileName)
        println(s"Removed $fileName")
        // Save the updated staging area
        IndexService.saveStagingArea(currentBranchPath, updatedStagingArea)
      } else {
        println(s"File $fileName is not tracked.")
      }
    }
  }
}
