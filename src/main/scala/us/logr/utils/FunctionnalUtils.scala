package us.logr.utils

import api.controllers.FutureOptionDSL.Fail

import scalaz._
import Scalaz._

object FunctionnalUtils {

  /**
   * J'ai pas trouvé de fonction sequence / traverse pour les Map mais si ca existe on peut s'en passer
   *
   * cf. http://stackoverflow.com/questions/26602611/how-to-understand-traverse-traverseu-and-traversem/26606232#26606232
   */
  def convertMap[A, B](m: Map[A, Fail \/ B]): Fail \/ Map[A, B] = {
    val errors = m.filter { case (k, v) => v.isLeft }
    if (errors.nonEmpty) {
      errors.values.map(_.swap.toOption).flatten.reduceLeft((f1, f2) => f1.withEx(f2)).left[Map[A, B]]
    } else {
      // we know for sure that every value is of type B and not Fail
      m.mapValues(_.toOption.get).right[Fail]
    }
  }

  def sequence[A](s: Seq[Fail \/ A]): Fail \/ Seq[A] = {
    val fails = for (-\/(x) <- s) yield (x)
    if (fails.isEmpty) {
      s.map(_.toOption).flatten.right[Fail]
    } else {
      fails.reduceLeft((f1, f2) => f1.withEx(f2)).left[Seq[A]]
    }
  }

}
