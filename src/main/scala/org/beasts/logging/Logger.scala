package org.beasts.logging

import org.beasts.logging.basics.Level._
import org.beasts.logging.writer.LogWriter
import org.beasts.logging.writerfactory.LogWriterFactory

/**
  * Created by Sak on 31-01-2017.
  */
object Logger {
  private var level: Level = LOG
  private var logWriterList:Map[String,LogWriter] = Map[String,LogWriter]()
  private val logFactory:LogWriterFactory = new LogWriterFactory
  logWriterList.+=(("__CONSOLE__",logFactory.getLogWriter("__CONSOLE__","ConsoleLogWriter")))
  logWriterList.getOrElse("__CONSOLE__",throw new NullPointerException)

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
