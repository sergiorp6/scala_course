package com.letgo.scala_course.application

import scala.concurrent.{ExecutionContext, Future}

import com.letgo.scala_course.domain.{ChannelId, Message, SlackClient}

class SlackMessagesFetcherUseCase(slackClient: SlackClient)(implicit ec: ExecutionContext) {
  var numberOfApiCalls: Int = 0

  var cache: Option[Future[Seq[Message]]] = None

  def fetch(channelName: ChannelId): Future[Seq[Message]] = {
    numberOfApiCalls = numberOfApiCalls + 1
    slackClient.fetchChannelMessages(channelName)
  }

  def fetchWithCacheSergio(channelName: ChannelId): Future[Seq[Message]] = {
    if (cache.isDefined) {
      cache.get
    } else {
      val result = fetch(channelName)
      cache = Some(result)
      result
    }
  }

  def fetchWithCacheImanol(channelName: ChannelId): Future[Seq[Message]] = {
    cache.getOrElse {
      val result = fetch(channelName)
      cache = Some(result)
      result
    }
  }

  def fetchWithCache(channelName: ChannelId): Future[Seq[Message]] = {
    cache match {
      case None => {
        val result = fetch(channelName)
        cache = Some(result)
        result
      }
      case Some(a) => a
    }
  }
}
