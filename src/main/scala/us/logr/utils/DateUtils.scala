package us.logr.utils

import java.time._
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

import scala.util.Try

object DateUtils {

  def now(): ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)

  def stringToDate(value: String, format: String): TemporalAccessor = {
    val format = DateTimeFormatter.ofPattern(format)
    format.parse(value)
  }

  def dateToString(date: TemporalAccessor, format: String): String = {
    val format = DateTimeFormatter.ofPattern(format)
    format.format(date)
  }

  /**
   * iso format : yyyy-MM-ddTHH:mm:ssZ
   * Z can be a timezone, eg +01:00 like in 2015-05-05T14:35:52.657+02:00
   */
  def dateToISO8601(date: TemporalAccessor): String = {
    val format = DateTimeFormatter.ISO_DATE_TIME
    Try {
      format.format(date)
    }.getOrElse("")
  }

  def iso8601ToDate(input: String): TemporalAccessor = {
    val format = DateTimeFormatter.ISO_DATE_TIME
    format.parse(input)
  }
}
