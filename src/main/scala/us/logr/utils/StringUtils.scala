package us.logr.utils

import java.security.SecureRandom
import java.text.DecimalFormat
import java.text.Normalizer
import java.util.regex.Pattern

import scala.util.control.NonFatal
import scala.util.Try

object StringUtils {

  def formatBytes(bytes: Int): String = {
    if (bytes == 0) {
      "0 Byte"
    } else {
      val k = 1024
      val sizes = List("Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB")
      val i: Int = (Math.floor(Math.log(bytes) / Math.log(k))).toInt

      val df = new DecimalFormat("#,###,###,##0.00")
      val formatedBytes = df.format(bytes / Math.pow(k, i)) + " " + sizes(i)
      formatedBytes
    }
  }

  def deAccent(str: String): String = {
    Try {
      val nfdNormalizedString: String = Normalizer.normalize(str, Normalizer.Form.NFD)
      val pattern: Pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
      pattern.matcher(nfdNormalizedString).replaceAll("")
    }.getOrElse("")
  }

  def generateUuid(): String = {
    java.util.UUID.randomUUID().toString
  }

  /*
  Elegant random strign generation in Scala -> http://www.bindschaedler.com/2012/04/07/elegant-random-string-generation-in-scala/
   */
  //Random Generator
  private val random = new SecureRandom()

  // Generate a random string of length n from the given alphabet
  private def randomString(alphabet: String)(n: Int): String = {
    Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString
  }

  // Generate a random alphabnumeric string of length n
  def randomAlphanumericString(n: Int) = {
    randomString("abcdefghijklmnopqrstuvwxyz0123456789")(n)
  }

  private val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  def isEmailIsValid(e: String): Boolean = {
    Try(emailRegex.findFirstMatchIn(e).isDefined).getOrElse(false)
  }
}
