class QueryBuilder {
  var query:String= "{"

  def setUser(login:String):User={
    var log= "\\\""+login+"\\\""
   // println(log)
    query=query+"user(login:"+log+")"
    new User(query)
  }
  def setRepositoryOwner(login:String):QueryBuilder={
    var login1= "\\\""+login+"\\\""
    query=query+s"repositoryOwner(login:$login1)"
    this
  }
  def getRepositoryOwner(repoowner:String):QueryBuilder={
    query=query+repoowner
    this
  }
  def setRepositoryOwnerandName(Owner:String,name:String):QueryBuilder={
    var log= "\\\""+name+"\\\""
    var owner= "\\\""+Owner+"\\\""
    query=query+s"repository(owner:${owner},name:$log)"
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
