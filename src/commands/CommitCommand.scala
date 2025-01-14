package commands

import services.CommitService
import services.IndexService
import utils.HashUtils

object CommitCommand {
  def execute(message: String): Unit = {
    val currentBranchPath = "./main/.wegit" // Assuming `main` is the current branch
    val stagingArea = IndexService.loadStagingArea(currentBranchPath)
    val hash = HashUtils.hashString(message + System.currentTimeMillis().toString)
    CommitService.createCommit(currentBranchPath, hash, stagingArea, message)
    println(s"Commit created with hash $hash")
  }
}
