package main

import commands._

object Main {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println("Please provide a command. Use --help for a list of commands.")
      System.exit(1)  // Exit with a non-zero status code indicating an error
    }

    args(0) match {
      case "init" =>
        if (args.length < 2) {
          println("Usage: init <repository_name>")
        } else {
          InitCommand.execute(args(1))
        }

      case "add" =>
        if (args.length < 2) {
          println("Usage: add <filename> | add .")
        } else {
          AddCommand.execute(args(1))
        }

      case "rm" =>
        if (args.length < 2) {
          println("Usage: rm <filename> | rm .")
        } else {
          RmCommand.execute(args(1))
        }

      case "status" =>
        StatusCommand.execute()

      case "commit" =>
        if (args.length < 2) {
          println("Usage: commit <message>")
        } else {
          CommitCommand.execute(args.tail.mkString(" "))
        }

      case "branch" =>
        if (args.length < 2) {
          println("Usage: branch <branch_name>")
        } else {
          BranchCommand.execute(args(1))
        }

      case "--help" =>
        println(
          """
            |WEgit - A simplified version control system
            |
            |Commands:
            |  init <repository_name>   - Initialize a new repository
            |  add <filename> | add .   - Add files to the staging area
            |  rm <filename> | rm .     - Remove files from the staging area
            |  status                   - Show repository status
            |  commit <message>         - Commit changes with a message
            |  branch <branch_name>     - Create a new branch
            |  --help                   - Show this help message
            |""".stripMargin)

      case _ =>
        println(s"Unknown command: ${args(0)}. Use --help for a list of commands.")
    }
  }
}
