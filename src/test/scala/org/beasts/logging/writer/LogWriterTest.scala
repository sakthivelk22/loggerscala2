package org.beasts.logging.writer


import org.scalatest._
import org.beasts.logging.basics.Level._
/**
  * Created by Sak on 03-02-2017.
  */
class LogWriterTest extends FunSpec with BeforeAndAfter with Matchers{
  var testLogWriter:LogWriter = _
  before {
    testLogWriter = new LogWriter {def log(identifier:Class[_],logLevel:Level,message:String):Unit = ???
                    def rollOverLog():Unit = ???}
  }
  after{
    testLogWriter = null
  }

  describe("LogWriter"){
    it("should have default LogLevel as LOG"){
      testLogWriter.getLogLevel should be (LOG)
    }
    it("can have the default LogLevel reset to other values") {
      testLogWriter.setLogLevel(ERROR)
      testLogWriter.getLogLevel should be (ERROR)
    }
    it("should be enabled by default"){
      testLogWriter.isEnabled should be (true)
    }
    it("can be set to boolean value as necessary"){
      testLogWriter.setEnabled(false)
      testLogWriter.isEnabled should be (false)
    }

  }

}
