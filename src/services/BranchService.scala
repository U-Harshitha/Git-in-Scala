package services

import core.{BranchNode, Repository}

object BranchService {
    def addBranch(repo: Repository, branchNode: BranchNode): Unit = {
        repo.branchTree.addBranch(branchNode)
        val branchesTreePath = s"${repo.rootPath}/.wegit/branches.tree"
        val branchData = s"${branchNode.name},${branchNode.parent.getOrElse("")},${branchNode.directoryPath}"
        val existingContent = FileService.readFile(branchesTreePath)
        FileService.writeFile(branchesTreePath, existingContent + s"\n$branchData")
    }
}
