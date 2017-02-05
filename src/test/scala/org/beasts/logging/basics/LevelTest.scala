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
}

