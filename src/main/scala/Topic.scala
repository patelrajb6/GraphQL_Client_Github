class Topic(name:String) {
  var name1="\\\""+name+"\\\""

  var query=s"topic(name:$name1) {"
  def setStarGrazers(stargazerConnection: StargazerConnection):this.type={
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
