package services

import core.{BranchTree, Repository}

object RepositoryService {
    def initializeRepository(repoPath: String): Repository = {
        val wegitPath = FileService.createDirectory(s"$repoPath/.wegit")
        FileService.createFile(s"$wegitPath/config")
        FileService.createFile(s"$wegitPath/branches.tree")
        FileService.createDirectory(s"$wegitPath/global_logs")

        val mainBranchPath = FileService.createDirectory(s"$repoPath/main/.wegit")
        FileService.createDirectory(s"$mainBranchPath/objects")
        FileService.createFile(s"$mainBranchPath/index.txt")
        FileService.createFile(s"$mainBranchPath/tracked.txt")  // Ensure tracked.txt is created
        FileService.createFile(s"$mainBranchPath/log")
        FileService.createFile(s"$mainBranchPath/HEAD")

        // Initialize HEAD with "main" as the default branch
        FileService.writeFile(s"$repoPath/.wegit/HEAD", "main")

        val branchTree = BranchTree(scala.collection.mutable.Map.empty, core.BranchNode("main", None, mainBranchPath))
        Repository(repoPath, "main", branchTree)
    }
}
