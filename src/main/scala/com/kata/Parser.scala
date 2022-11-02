package com.kata

import scala.util.matching.Regex

class Parser {
  /*
    Schema:
    -l is a boolean (logging)
    -p is a integer (porting)
    -d is a string that contains / or \  (directory)
   */
  val integerPatternMatch: Regex = "-p \\d+".r
  val stringPatternMatch: Regex = "-d \\D[\\w\\-/\\\\.]*".r
  val booleanFlag: String = "-l"

  def parse(stringInput: String): Unit = {
    val parsedBooleanValue = parseBool(stringInput, booleanFlag)
    val parsedIntegers = parseInt(stringInput, integerPatternMatch)
    val parsedStrings = parseString(stringInput, stringPatternMatch)
    println(s"String: $parsedStrings, Boolean: $parsedBooleanValue, Integer: $parsedIntegers")
  }

  def parseBool(stringInput: String, flagIndicator: String): Boolean = {
    if (stringInput.contains(flagIndicator)) true
    else false
  }

  def parseInt(stringInput: String, intRegex: Regex): Int = {
    if (stringInput.contains("-p")) {
      val numberFound = intRegex.findAllIn(stringInput).mkString
      numberFound.substring(numberFound.indexOf("p") + 2).toInt
    }
    else 0
  }

  def parseString(stringInput: String, stringRegex: Regex): String = {
    if (stringInput.contains("-d")) {
      val stringFound = stringRegex.findAllIn(stringInput).mkString
      stringFound.substring(stringFound.indexOf("d") + 2)
    } else ""
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

