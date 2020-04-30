class User( query:String) extends RepositoryInfo {
  var userQuery=query+"{ "

  def getLocation():User={
   userQuery=userQuery+"location "
    this
  }
  def getlogin():User={
    userQuery=userQuery+"login "
    this
  }

 override def build():String={
    userQuery=userQuery+super.build()
    userQuery
  }

}
