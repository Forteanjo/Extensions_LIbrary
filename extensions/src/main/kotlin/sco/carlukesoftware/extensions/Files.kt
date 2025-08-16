package sco.carlukesoftware.extensions

import java.io.File
import java.io.IOException
import java.nio.charset.Charset

/**
 * read file to string
 */
fun File.readFile(): String = this.readText(Charset.defaultCharset())

/**
 * get human-readable size string from given file
 *
 * @return human-readable string
 */
fun File.getSizeByMb(): String {
    var size: Long = 0

    if (this.exists() && this.canRead()) {
        size = this.length()
    }

    return size.toNumInUnits()
}

/**
 * Delete all files in a directory using recurssion
 *
 * @param[path] path of directory containing files
 * @return Boolean successful or not
 */
//Deletes all files in a directory using recursion
fun File.deleteFilesInDirectory(): Boolean {
    if (this.exists()) {
        //Get our list of files, if there's none we're done
        val files = this.listFiles() ?: return true

        for (index in files.indices) {
            if (files[index].isDirectory) {
                files[index].deleteFilesInDirectory()
            }

            files[index].delete()
        }
    }

    return true
}

/**
 * @param file
 * Canonical file to match.
 * @return Whether the specified file matches a filesystem root.
 * @throws IOException
 */
fun File.isMountPoint(): Boolean {
    for (root in File.listRoots()) {
        if (root == this) {
            return true
        }
    }

    return false
}
