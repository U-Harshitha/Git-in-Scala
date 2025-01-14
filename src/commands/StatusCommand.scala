package commands

import services.IndexService
import utils.StatusUtils

object StatusCommand {
  def execute(): Unit = {
    val currentBranchPath = "./main/.wegit" // Assuming `main` is the current branch
    val stagingArea = IndexService.loadStagingArea(currentBranchPath)
    val status = StatusUtils.calculateStatus(currentBranchPath, stagingArea)
    println(status)
  }
}
