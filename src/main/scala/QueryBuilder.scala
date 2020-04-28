class QueryBuilder {
  private var query:String= "{"

  def setUser(login:String):QueryBuilder={
    var log= "\\\""+login+"\\\""
   // println(log)
    query=query+"user(login:"+log+")"
    this
  }
  def setRepositoryOwnerandName(Owner:String,name:String):QueryBuilder={
    var log= "\\\""+name+"\\\""
    var owner= "\\\""+Owner+"\\\""
    query=query+s"repository(owner:${owner}name:$log)"
    this
  }
  def getRepository(repoInfo:String):QueryBuilder={
    query=query+repoInfo
    this
  }
  def getUser(userString:String):QueryBuilder={
    query=query+ userString
    this
  }

  def build(): String ={
    query=query+" }"
    query
  }
}
