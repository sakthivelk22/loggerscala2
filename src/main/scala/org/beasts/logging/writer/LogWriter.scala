package org.beasts.logging.writer

import org.beasts.logging.basics.Level._

/**
  * Created by Sak on 02-02-2017.
  */
trait LogWriter{
  private var defaultLogLevel:Level = LOG
  private var enabled:Boolean = true
  protected var identifier:Class[_] = _
  protected var logLevel:Level = _
  protected var message:String = _
  final def getLogLevel:Level = { this.defaultLogLevel }
  final def setLogLevel(logLevel:Level):Level = { this.defaultLogLevel=logLevel ; this.defaultLogLevel }
  final def isEnabled:Boolean = { enabled }
  final def setEnabled(enabled:Boolean):Boolean = { this.enabled=enabled ; this.enabled }



  /* to be Overridden in all derived classes */
  def rollOverLog():Unit
  def log(identifier:Class[_],logLevel:Level,message:String)
}

