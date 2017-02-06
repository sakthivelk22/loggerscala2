package org.beasts.logging

import org.beasts.logging.basics.Level._
import org.beasts.logging.writer.LogWriter
import org.beasts.logging.writerfactory.LogWriterFactory
import scala.io.Source

/**
  * Created by Sak on 31-01-2017.
  */
object Logger {
  private var level: Level = LOG
  private var logWriterList:Map[String,LogWriter] = Map[String,LogWriter]()
  private val logFactory:LogWriterFactory = new LogWriterFactory
  logWriterList.+=(("__CONSOLE__",logFactory.getLogWriter("__CONSOLE__","ConsoleLogWriter")))
  logWriterList.getOrElse("__CONSOLE__",throw new NullPointerException)
  private val propFile: String = "Logger.properties"

  println("Loading properties file : "+propFile)
  try {
    for (line <- Source.fromFile(propFile).getLines()) { setProps(line.split('='){0},line.split('='){1}) }
  }
  catch {
    case ex: scala.MatchError =>
      println("Aborting properties file load. Unrecognised values found")
      ex.printStackTrace()
    case ex: Exception =>
      println("Unable to load properties file. Defaulting Logger properties")
      ex.printStackTrace()
  }

  private def setProps(key: String, value:String):Unit = {
    key match {
      case "AddLogWriter" =>
        val values = value.split(',')
        if (values.length==3) this.addLogWriter(values{0},values{1},toLevel(values{2}))
        else if (values.length==2) this.addLogWriter(values{0},values{1})
        else throw new scala.MatchError
      case "DefaultLevel" => this.setDefaultLevel( toLevel(value))
      case "WriteToConsole" => this.setWriteToConsole(value.toBoolean)
    }
  }
  def log(identifier:Class[_],logLevel:Level,message:String): Unit ={
    logWriterList.foreach( {logWriter=> logWriter._2.log(identifier, logLevel, message)})
  }

  def rollOverLogs():Unit = {
    logWriterList.foreach(x=>x._2.rollOverLog())
  }

  def setDefaultLevel(level: Level): Unit = {
    this.level = logWriterList("__CONSOLE__").setLogLevel(level)
  }

  def setWriteToConsole(toWrite: Boolean): Unit = {
    logWriterList("__CONSOLE__").setEnabled(toWrite)
  }

  def setWriteToLog(name:String,toWrite: Boolean): Unit = {
    logWriterList(name).setEnabled(toWrite)
  }

  def addLogWriter(name:String,logWriter:String):Unit = {
    logWriterList.+=((name,logFactory.getLogWriter(name,logWriter)))
    logWriterList(name).setLogLevel(this.level)
  }

  def addLogWriter(name:String,logWriter:String,logLevel:Level):Unit = {
    logWriterList.+=((name,logFactory.getLogWriter(name,logWriter)))
    logWriterList(name).setLogLevel(logLevel)
  }
}
