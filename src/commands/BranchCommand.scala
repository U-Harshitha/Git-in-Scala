package commands

import services.BranchService
import services.FileService
import core.*
import core.Repository

object BranchCommand {
  def execute(branchName: String): Unit = {
    val repoPath = "." // Assuming current directory is the repository root
    val repo = Repository(repoPath, "main", BranchTree(scala.collection.mutable.Map.empty, BranchNode("main", None, "./main")))
    val currentBranch = repo.currentBranch

    val branchPath = s"./$branchName"
    FileService.copyDirectory(currentBranch, branchPath)
    val newBranch = BranchNode(branchName, Some(repo.currentBranch), branchPath)
    BranchService.addBranch(repo, newBranch)
    println(s"Branch $branchName created.")
  }
}
