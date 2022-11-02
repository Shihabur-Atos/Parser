package com.kata

import scala.util.matching.Regex

class Parser {
  /*
    Schema:
    -l is a boolean (logging)
    -p is a integer (porting)
    -d is a string that contains / or \  (directory)
   */
  val integerPatternMatch: Regex = "-p (d+)".r
  val stringPatternMatch: Regex = "-d \\D[\\w\\-/\\\\.]*".r
  val booleanFlag: String = "-l"

  def parse(stringInput: String): Unit = {
    val parsedBooleanValue = parseBool(stringInput, booleanFlag)
    val parsedIntegers = parseInt(stringInput, integerPatternMatch)
    val parsedStrings = parseString(stringInput, stringPatternMatch)

    println(s"Integers: $parsedIntegers")

    println(s"String: $parsedStrings, Boolean: $parsedBooleanValue")
  }

  def parseBool(stringInput: String, flagIndicator: String): Boolean = {
    if (stringInput.contains(flagIndicator)) true
    else false
  }

  def parseInt(stringInput: String, intRegex: Regex): Int = {
    if (stringInput.contains("-p")) intRegex.findFirstIn(stringInput).
    else 0
  }

  def parseString(stringInput: String, stringRegex: Regex): String = {
    if (stringInput.contains("-d")) stringRegex.findAllIn(stringInput).mkString("", ",", "")
    else ""
  }
}

/*
  parseBool(stringInput)
  val args = stringInput.split("-[l,pd]")
  args.foreach(x => {
    if(x.contains('p')) {
      parseInt(x, integerPatternMatch)
    }
    if(x.contains('d')) {
      parseString(stringInput, stringPatternMatch)
    }
  })
 */

