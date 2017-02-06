package org.beasts.logging.basics

import org.scalatest._
import Level._
/**
  * Created by Sak on 01-02-2017.
  */
class LevelTest extends FlatSpec with Matchers{
  "Level Class" should "have value of Error higher than Warning" in {
    (ERROR > WARNING) should be (true)
  }
  it should "have value of Warning higher than Log" in {
    (WARNING > LOG) should be (true)
  }
  it should "have value of Log higher than Debug" in {
    (LOG > DEBUG) should be (true)
  }
  it should "have value of Debug higher than Info" in {
    (DEBUG > INFO) should be (true)
  }
  it should "be cconverted to String" in {
    INFO.toString should be ("INFO")
    DEBUG.toString should be ("DEBUG")
    LOG.toString should be ("LOG")
    WARNING.toString should be ("WARNING")
    ERROR.toString should be ("ERROR")
  }
  it should "be created from String with toLevel" in {
    toLevel("INFO") should be (INFO)
    toLevel("DEBUG") should be (DEBUG)
    toLevel("LOG") should be (LOG)
    toLevel("WARNING") should be (WARNING)
    toLevel("ERROR") should be (ERROR)
    val thrower = intercept[NullPointerException] {toLevel("default") }
    thrower.getClass.toString should be ("class java.lang.NullPointerException")
  }
}

