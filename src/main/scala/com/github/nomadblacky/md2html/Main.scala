package com.github.nomadblacky.md2html
import java.io.File

import org.commonmark.parser.{Parser => MarkdownParser}
import org.commonmark.renderer.html.HtmlRenderer
import scopt.OParser

import scala.io.Source

object Main {

  private[this] val builder = OParser.builder[Config]
  private[this] val parser = {
    import builder._
    OParser.sequence(
      programName(BuildInfo.name),
      head(BuildInfo.name, BuildInfo.version),
      arg[File]("<file>")
        .required()
        .action((f, c) => c.copy(f))
        .text("Markdown file to convert to HTML")
    )
  }

  def main(args: Array[String]): Unit = {
    OParser.parse(parser, args, Config()) match {
      case Some(config) =>
        val node = MarkdownParser
          .builder()
          .build()
          .parse(Source.fromFile(config.file).mkString)
        println(HtmlRenderer.builder().build().render(node))
      case None =>
        OParser.usage(parser)
    }
  }
}

case class Config(file: File = new File("."))
