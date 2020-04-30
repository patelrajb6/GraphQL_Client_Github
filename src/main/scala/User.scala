class User extends RepositoryInfo {
  var userQuery:String="{ "
  def getLocation():User={
   userQuery=userQuery+"location "
    this
  }
  def getlogin():User={
    userQuery=userQuery+"login "
    this
  }
  def getName():User={
    userQuery=userQuery+"name "
    this
  }
  override def build():String={
    userQuery=userQuery+" }"
    userQuery
  }

}
