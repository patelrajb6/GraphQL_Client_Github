class RepositoryOwner{
  var repoName:String="{"
  var ownerquery= "{ "
  def setRepository(name:String):RepositoryInfo={
    var name1= "\\\""+name+"\\\""
    ownerquery=ownerquery+s"repository(name:${name1})"
    new RepositoryInfo() {}
  }
  def getRepository():RepositoryInfo={
    new Repository() {}
  }
   def build():String={
    ownerquery
  }

}
