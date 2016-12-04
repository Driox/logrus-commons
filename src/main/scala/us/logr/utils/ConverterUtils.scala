package us.logr.utils

import scala.concurrent.{ExecutionContext, Future}

object ConverterUtils {

  def stringToIng(s: String): Option[Int] = {
    if (s == "inf") {
      Some(Integer.MAX_VALUE)
    } else {
      scala.util.Try(s.toInt).toOption
    }
  }

  def transform[A](o: Option[Future[A]])(implicit exec: ExecutionContext): Future[Option[A]] =
    o.map(f => f.map(Option(_))).getOrElse(Future.successful(None))
}
