package commands

import services.RepositoryService

object InitCommand {
  def execute(repoName: String): Unit = {
    val repoPath = s"./$repoName"
    val repo = RepositoryService.initializeRepository(repoPath)
    println(s"Initialized WEgit repository in $repoPath")
  }
}
