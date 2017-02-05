package org.beasts.logging

import org.scalatest._
import org.beasts.logging.basics.Level._

/**
  * Created by Sak on 05-02-2017.
  */
class LoggerTest extends FunSpec with GivenWhenThen with Matchers  {

  describe("Logger Object"){
    it("by default should have a Console logger"){
      Given("Console Logger is left to default log Level")
      When("messaged is logged at higher than default levels")
        Logger.log(this.getClass,ERROR,"ERROR Message must appear on Console")
      Then("message must be displayed on Console")
    }

    it(" should log to Console based on its changed LogLevels"){
      Given("Console Logger level is changed to INFO")
        Logger.setDefaultLevel(INFO)
      When("messaged is logged at new levels")
        Logger.log(this.getClass,INFO,"INFO Message must appear on Console")
      Then("message must be displayed on Console")
    }

    it(" should log to file based on the default logLevels"){
      Given("Console Logger level is changed to INFO")
        Logger.addLogWriter("test.log","FileLogWriter",ERROR)
      When("messaged is logged at new levels")
        Logger.log(this.getClass,ERROR,"ERROR Message must appear on File")
        Logger.log(this.getClass,ERROR,"ERROR1 Message must appear on File")
        Logger.log(this.getClass,WARNING,"WARNING Message must not appear on File")
      Then("message must be displayed on File")
    }

  }
}
