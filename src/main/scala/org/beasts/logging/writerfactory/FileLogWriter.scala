package org.beasts.logging.writerfactory

import java.io.{BufferedWriter, File, FileOutputStream, OutputStreamWriter}
import java.text.SimpleDateFormat
import java.util.Date

import org.beasts.logging.basics.Level.Level
import org.beasts.logging.writer.LogWriter

/**
  * Created by User on 03-02-2017.
  */
class FileLogWriter (val name:String) extends LogWriter {
  override def log(identifier:Class[_],logLevel:Level,message:String):Unit = {
    if (this.isEnabled && this.getLogLevel <= logLevel)
      new Thread(new Runnable() {
        val identity = identifier
        val level = logLevel
        val msg = message
        def run(): Unit = {
          val messageString = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss:SSS").format(new Date()) + " - " + level.toString
          val bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name, true)))
          bw.write(messageString+"\r")
          bw.write(identity.toString + " - " + msg +"\r")
          bw.flush()
          bw.close()
        }
      }).start()
  }

  override def rollOverLog():Unit = {
      new File(name).renameTo(new File(name+ new SimpleDateFormat(".yyyy-MM-dd-HH-mm-ss-SSS").format(new Date())))
  }
}
