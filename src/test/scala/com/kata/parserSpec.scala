package com.kata

import org.scalatest.wordspec.AnyWordSpec

import scala.util.matching.Regex

class parserSpec extends AnyWordSpec {

  val args = new Parser()
  val integerPatternMatch: Regex = "-p (d+)".r
  val stringPatternMatch: Regex = "-d \\D[\\w\\-/\\\\.]*".r
  val booleanFlag: String = "-l"
  val stringInput = "-l -p 8080 -d usr/logs/dir"

  "A parser" should {

    "give true if -l is found " in {
      assert(args.parseBool(stringInput, booleanFlag))
    }

    "give a number if -p with a number is found" in {
      assert(args.parseInt(stringInput, integerPatternMatch) == Seq[8080])
    }

    "give a string if -d with a string is found" in {
      assert(args.parseString(stringInput, stringPatternMatch).equals("usr/logs/dir"))
    }

    "give an error if -p has something other than a number" in {
      assertThrows[ClassFormatError](args.parseInt("-p axy",integerPatternMatch))
    }

    "give an error if -d has something other than an a directory" in {
      assertThrows[ClassFormatError](args.parseString("-d 8asd",stringPatternMatch))
    }
  }

}
