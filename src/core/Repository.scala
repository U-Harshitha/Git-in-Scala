package core

import core.BranchTree

case class Repository(
    rootPath: String,          // Path to the repository
    currentBranch: String,     // Current branch
    branchTree: BranchTree     // Tree structure for branches
) {
    def getCurrentBranchPath: String = s"$rootPath/$currentBranch/.wegit"

    def switchBranch(newBranch: String): Repository = {
        if (!branchTree.branches.contains(newBranch)) {
            throw new IllegalArgumentException(s"Branch $newBranch does not exist.")
        }
        this.copy(currentBranch = newBranch)
    }
}
