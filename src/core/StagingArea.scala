package core

case class StagingArea(
    addedFiles: Map[String, String], // Map of file paths to SHA1 hashes
    removedFiles: Set[String]        // Files marked for removal
) {
    def addFile(filePath: String, hash: String): StagingArea = {
        this.copy(
            addedFiles = addedFiles.updated(filePath, hash),
            removedFiles = removedFiles - filePath
        )
    }

    def removeFile(filePath: String): StagingArea = {
        this.copy(
            addedFiles = addedFiles - filePath,
            removedFiles = removedFiles + filePath
        )
    }

    def clear(): StagingArea = {
        this.copy(addedFiles = Map.empty, removedFiles = Set.empty)
    }
}
