package core

case class Commit(
    hash: String,                    // Unique commit hash
    parentHash: Option[String],      // Parent commit hash (if any)
    message: String,                 // Commit message
    timestamp: Long,                 // Commit timestamp
    fileTree: Map[String, String]    // Map of file paths to object hashes
) {
    def getFileHash(filePath: String): Option[String] = fileTree.get(filePath)
}
