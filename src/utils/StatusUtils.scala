package utils

import core.StagingArea

object StatusUtils {
    def calculateStatus(branchPath: String, stagingArea: StagingArea): String = {
        val trackedFiles = FileUtils.readFile(s"$branchPath/tracked.txt").split("\n").toSet
        val addedFiles = stagingArea.addedFiles.keySet
        val removedFiles = stagingArea.removedFiles

        val untrackedFiles = trackedFiles -- addedFiles
        val readyToCommit = addedFiles -- removedFiles

        s"""
           |Tracked files: ${trackedFiles.mkString(", ")}
           |Untracked files: ${untrackedFiles.mkString(", ")}
           |Modified and need to add: ${removedFiles.mkString(", ")}
           |Added and ready to commit: ${readyToCommit.mkString(", ")}
           |""".stripMargin
    }
}
