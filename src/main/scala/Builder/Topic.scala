package Builder

import com.typesafe.scalalogging.LazyLogging

class Topic(name:String) extends LazyLogging {      // the mandatory string for the Builder.Topic
  logger.debug("Builder.Topic object is created.")
  private var name1="\\\""+name+"\\\""
  private var query=s"topic(name:$name1) {" //building string
  def setStarGrazers(stargazerConnection: StargazerConnection):this.type={  //has nested stargazer
    query+=s"${stargazerConnection.build()} "
    this
  }
  def getname():this.type={
    query+=" name"
    this
  }
  def getID():this.type={
    query+=" id "
    this
  }

   def build():String={
    query=query+"}"
    query
  }

}
