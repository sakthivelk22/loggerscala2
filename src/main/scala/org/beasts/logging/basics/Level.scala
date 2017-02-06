package org.beasts.logging.basics

/**
  * Created by Sak on 31-01-2017.
  */

object Level extends Enumeration{
  type Level = Value
  val INFO, DEBUG, LOG, WARNING, ERROR = Value

  def toLevel(value:String):Level={
    value match {
      case "ERROR" => ERROR
      case "WARNING" => WARNING
      case "LOG" =>  LOG
      case "DEBUG" => DEBUG
      case "INFO" => INFO
      case _ => throw new NullPointerException
    }
  }
}