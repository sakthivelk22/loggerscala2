package org.beasts.logging.writerfactory


import java.text.SimpleDateFormat
import java.util.Date

import org.beasts.logging.basics.Level.Level
import org.beasts.logging.writer.LogWriter

/**
  * Created by User on 03-02-2017.
  */
class ConsoleLogWriter (val name:String) extends LogWriter {
  override def log(identifier:Class[_],logLevel:Level,message:String):Unit = {
    if (this.isEnabled && this.getLogLevel <= logLevel)
      new Thread(new Runnable() {
        val identity = identifier
        val level = logLevel
        val msg = message
        def run(): Unit = {
          val messageString = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss:SSS").format(new Date()) + " - " + level.toString
          println(messageString)
          println(identity.toString + " - " + msg)
        }
      }).start()
  }

  override def rollOverLog():Unit = ???







}

