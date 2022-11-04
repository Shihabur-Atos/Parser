package com.kata

import scala.util.matching.Regex
import scala.util.{Try, Success, Failure}

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

  def parseInt(stringInput: String, intRegex: Regex): Try[Int] = {
    if (stringInput.contains("-p")) {
      val numberFound = intRegex.findAllIn(stringInput).mkString
      if (numberFound.isBlank) return Failure(new NumberFormatException())
      else return Success(numberFound.substring(numberFound.indexOf("p") + 2).toInt)
    }
    Failure(new NumberFormatException())
  }

  def parseString(stringInput: String, stringRegex: Regex): Try[String] = {
    if (stringInput.contains("-d")) {
      val stringFound = stringRegex.findAllIn(stringInput).mkString
      if (stringFound.isBlank) return Failure(new IllegalArgumentException())
      else return Success(stringFound.substring(stringFound.indexOf("d") + 2))
    }
    Failure(new IllegalArgumentException())
  }
}

