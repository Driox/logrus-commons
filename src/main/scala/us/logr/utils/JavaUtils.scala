package us.logr.utils

object JavaUtils {

  def scalaMapToJavaMap[A, B](smap: Map[A, B]): java.util.Map[A, B] = {
    val jmap = new java.util.HashMap[A, B]
    smap.foreach(p => jmap.put(p._1, p._2.asInstanceOf[B]))

    jmap
  }
}
