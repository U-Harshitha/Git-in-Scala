package services

import core.StagingArea

object IndexService {
    def loadStagingArea(branchPath: String): StagingArea = {
        val indexPath = s"$branchPath/.wegit/index.txt"
        val trackedPath = s"$branchPath/.wegit/tracked.txt"

        // Read index.txt file
        val addedFiles = if (FileService.fileExists(indexPath) && FileService.readFile(indexPath).nonEmpty) {
            FileService.readFile(indexPath).split("\n").map { line =>
                val parts = line.split(" ")
                parts(0) -> parts(1)
            }.toMap
        } else Map.empty[String, String]

        // Read tracked.txt file
        val removedFiles = if (FileService.fileExists(trackedPath) && FileService.readFile(trackedPath).nonEmpty) {
            FileService.readFile(trackedPath).split("\n").filter(_.startsWith("D ")).map(_.substring(2)).toSet
        } else Set.empty[String]

        StagingArea(addedFiles, removedFiles)
    }

    def saveStagingArea(branchPath: String, stagingArea: StagingArea): Unit = {
        val indexPath = s"$branchPath/.wegit/index.txt"
        val trackedPath = s"$branchPath/.wegit/tracked.txt"

        val indexContent = stagingArea.addedFiles.map { case (filePath, hash) => s"$filePath $hash" }.mkString("\n")
        FileService.writeFile(indexPath, indexContent)

        val trackedContent = stagingArea.removedFiles.map(file => s"D $file").mkString("\n")
        FileService.writeFile(trackedPath, trackedContent)
    }

    def addToTracked(branchPath: String, filePath: String): Unit = {
        val trackedPath = s"$branchPath/.wegit/tracked.txt"
        val content = if (FileService.fileExists(trackedPath)) {
            FileService.readFile(trackedPath) + s"\n$filePath"
        } else {
            s"$filePath"
        }
        FileService.writeFile(trackedPath, content)
    }

    def markAsDeleted(branchPath: String, filePath: String): Unit = {
        val trackedPath = s"$branchPath/.wegit/tracked.txt"
        val content = if (FileService.fileExists(trackedPath)) {
            FileService.readFile(trackedPath) + s"\nD $filePath"
        } else {
            s"D $filePath"
        }
        FileService.writeFile(trackedPath, content)
    }
}
