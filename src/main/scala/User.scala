class User{
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
  def build():String={
    userQuery=userQuery+" }"
    userQuery
  }

}
