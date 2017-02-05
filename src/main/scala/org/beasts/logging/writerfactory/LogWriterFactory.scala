package org.beasts.logging.writerfactory

import org.beasts.logging.writer.LogWriter

/**
  * Created by User on 03-02-2017.
  */
class LogWriterFactory {
  def getLogWriter(name:String,logWriter:String):LogWriter={
    logWriter match {
      case "ConsoleLogWriter" => new ConsoleLogWriter(name)
      case "FileLogWriter" => new FileLogWriter(name)
      case _ => throw new NullPointerException
    }
  }

}
