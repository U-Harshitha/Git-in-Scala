package services

import core.{Commit, StagingArea}

object CommitService {
    def createCommit(branchPath: String, hash: String, stagingArea: StagingArea, message: String): Unit = {
        val objectsPath = s"$branchPath/objects"
        val commitFilePath = s"$objectsPath/$hash"

        val fileTreeContent = stagingArea.addedFiles.map { case (filePath, fileHash) =>
            s"$filePath $fileHash"
        }.mkString("\n")

        val commitContent =
            s"""
               |hash: $hash
               |message: $message
               |timestamp: ${System.currentTimeMillis()}
               |files:
               |$fileTreeContent
               |""".stripMargin

        FileService.writeFile(commitFilePath, commitContent)
    }
}
