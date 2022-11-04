package com.kata

import org.scalatest.wordspec.AnyWordSpec
import scala.util.matching.Regex
import scala.util.{Try,Success,Failure}


class parserSpec extends AnyWordSpec {

  val args = new Parser()
  val integerPatternMatch: Regex = "-p \\d+".r
  val stringPatternMatch: Regex = "-d \\D[\\w\\-/\\\\.]*".r
  val booleanFlag: String = "-l"
  val stringInput = "-l -p 8080 -d usr/logs/dir"

  "A parser" should {

    "give true if -l is found " in {
      assert(args.parseBool(stringInput, booleanFlag))
    }

    "give a number if -p with a number is found" in {
      val result = args.parseInt(stringInput, integerPatternMatch)
      result match {
        case Failure(_) => fail("Should succeed!")
        case Success(result) => assert(result == 8080)
      }
    }

    "give a string if -d with a string is found" in {
      val result = args.parseString(stringInput, stringPatternMatch)
      result match {
        case Failure(_) => fail("Should succeed!")
        case Success(value) => assert(value.equals("usr/logs/dir"))
      }
    }

    "give an error if -p has something other than a number" in {
      val result = args.parseInt("-p axy",integerPatternMatch)
      result match {
        case Failure(exception) => assert(exception.isInstanceOf[IllegalArgumentException])
        case Success(_) => fail("Should fail!")
      }
    }

    "give an error if -d has something other than an a directory" in {
      val result = args.parseString("-d 8asd",stringPatternMatch)
      result match {
        case Failure(exception) => assert(exception.isInstanceOf[RuntimeException])
        case Success(_) => fail("Should fail!")
      }
    }
  }

}
