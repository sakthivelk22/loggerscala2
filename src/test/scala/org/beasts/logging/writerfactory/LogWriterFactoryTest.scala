package org.beasts.logging.writerfactory

import org.scalatest._
import org.beasts.logging.writer.LogWriter
/**
  * Created by Sak on 03-02-2017.
  */
class LogWriterFactoryTest extends FlatSpec with GivenWhenThen with Matchers{
  var logWriter:LogWriter = _
  "LogWriterFactory" should "return ConsoleLogWriter object for 'ConsoleLogWriter' string" in {
    Given("LogWriterFactory object is available")
      val logFactory = new LogWriterFactory
    When("getLogWriter is called with string 'ConsoleLogWriter' ")
      logWriter=logFactory.getLogWriter("test","ConsoleLogWriter")
    Then("the method returns with 'ConsoleLogWriter' object")
      (logWriter.getClass.toString) shouldBe "class org.beasts.logging.writerfactory.ConsoleLogWriter"
  }
  "LogWriterFactory" should "return FileLogWriter object for 'FileLogWriter' string" in {
    Given("LogWriterFactory object is available")
      val logFactory = new LogWriterFactory
    When("getLogWriter is called with string 'FileLogWriter' ")
      logWriter=logFactory.getLogWriter("test","FileLogWriter")
    Then("the method returns with 'FileLogWriter' object")
      (logWriter.getClass.toString) shouldBe "class org.beasts.logging.writerfactory.FileLogWriter"
  }
  "LogWriterFactory" should "throw NullPointerException if some other values are passed" in {

    Given("LogWriterFactory object is available")
    val logFactory = new LogWriterFactory
    When("getLogWriter is called with string 'default' ")
     val thrower = intercept[NullPointerException] {logWriter = logFactory.getLogWriter("test","default")}
    Then("the method throws NullPointerException")
      (thrower.getClass.toString) should be ("class java.lang.NullPointerException")
  }
}
