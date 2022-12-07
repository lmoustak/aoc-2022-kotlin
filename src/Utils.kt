import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Turn a string into CharArray, then into a set of its Chars
 */
fun String.toCharSet() = this.toCharArray().toSet()

infix fun IntProgression.contains(that: IntProgression) = this.first <= that.first && this.last >= that.last
infix fun IntProgression.overlaps(that: IntProgression) = this.first in that || this.last in that
fun <T> MutableCollection<T>.addIfNotPresent(predicate: (T) -> Boolean, item: T): T {
    var collectionItem = this.find(predicate)
    if (collectionItem == null) {
        this.add(item)
        collectionItem = item
    }

    return collectionItem!!
}