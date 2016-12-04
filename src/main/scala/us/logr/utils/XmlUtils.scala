package us.logr.utils

import scala.xml.NodeSeq

object XmlUtils {

  def prettyPrintXml(xml: NodeSeq) = {
    val printer = new scala.xml.PrettyPrinter(80, 2)
    xml.toList.map(_.toString).fold("")((x, y) => x + y)
  }
}
