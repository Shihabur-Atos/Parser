package com.kata

object main extends App {
  val parser = new Parser()

  parser.parseInt("-l -p 8080 -d usr/logs/dir80")
}
