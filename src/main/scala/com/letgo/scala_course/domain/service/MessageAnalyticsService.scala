package com.letgo.scala_course.domain.service

import com.letgo.scala_course.domain.{Message, UserMessage, UserName}

class MessageAnalyticsService {

  def countMessagesOfUser(messages: Seq[UserMessage], userName: UserName): Int = {
    messages.count(_.userName == userName)
  }

  def groupByUserName(messages: Seq[UserMessage]): Map[UserName, Seq[Message]] = {

    var map: Map[UserName, Seq[Message]] = Map()

    messages.foreach(message => {
      if (map.contains(message.userName)) {
        var previousMessages = map(message.userName)
        map = map + (message.userName -> previousMessages.:+(message.message))
      } else {
        map = map + (message.userName -> Seq(message.message))
      }
    })
    map
  }
}
