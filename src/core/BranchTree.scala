package core

import scala.collection.mutable

case class BranchTree(
    branches: mutable.Map[String, BranchNode], // Map of branch names to nodes
    root: BranchNode
) {
    def addBranch(branchNode: BranchNode): Unit = {
        branches.put(branchNode.name, branchNode)
    }

    def getBranch(name: String): Option[BranchNode] = branches.get(name)
}

case class BranchNode(
    name: String,
    parent: Option[String],       // Parent branch name (if any)
    directoryPath: String         // Path to the branch directory
)
