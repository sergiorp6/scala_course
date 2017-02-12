package com.letgo.scala_course.domain.service

import com.letgo.scala_course.domain.Message

class MessageCensor(forbiddenKeywords: Set[String]) {

//  private val filterRuleSergio: Message => Message = { message =>
//
//    var returnValue = message
//
//    forbiddenKeywords.foreach(forbiddenWord =>
//        returnValue = Message(returnValue.text.replaceAll(forbiddenWord, ""))
//    )
//
//    Message(returnValue.text.replaceAll("  ", " ").trim)
//  }

  /*
  private val filterRuleSanti: Message => Message = { msg =>
    Message(msg.text.split(" ").filterNot(containsForbiddenWord).mkString(" "))
  }
  def containsForbiddenWord(word: String): Boolean = {
    forbiddenKeywords.contains(word)
  }
  */

  private val filterRule: Message => Message = { msg =>
    Message(msg.text.split(" ").fold("")(concatWordsIfNotForbidden).trim())
  }

  def concatWordsIfNotForbidden(acummulated: String, currentWord: String): String = {
    if (forbiddenKeywords.contains(currentWord)) {
      acummulated
    } else {
      acummulated + " " + currentWord
    }
  }

  def filterMessages(messages: Seq[Message]): Seq[Message] = messages.map(filterRule)
}

