package utils

import java.security.MessageDigest

object HashUtils {
    def hashFile(filePath: String): String = {
        val content = scala.io.Source.fromFile(filePath).mkString
        hashString(content)
    }

    def hashString(content: String): String = {
        val md = MessageDigest.getInstance("SHA-1")
        md.digest(content.getBytes("UTF-8")).map("%02x".format(_)).mkString
    }
}
