package core

case class Branch(
    name: String,                // Branch name
    headCommitHash: String,      // Current HEAD commit hash
    trackedFiles: Map[String, String] // Map of tracked files to their hashes
) {
    def updateHead(newCommitHash: String): Branch = {
        this.copy(headCommitHash = newCommitHash)
    }

    def updateTrackedFiles(newTrackedFiles: Map[String, String]): Branch = {
        this.copy(trackedFiles = newTrackedFiles)
    }
}
