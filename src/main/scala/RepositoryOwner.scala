class RepositoryOwner extends Repository {
 var ownerquery= "{ "
  def setRepository(name:String):RepositoryOwner={
    var name1= "\\\""+name+"\\\""
    ownerquery=ownerquery+s"repository(name:${name1})"
    this
  }
  override def build():String={
    ownerquery
  }

}
