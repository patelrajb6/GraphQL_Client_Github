class StargazerConnection(after:String="",before:String="",first:Int=0,last:Int=0) {
    //if you define the default values for the above parameters than it turns into optional parameters
  var options=""
  //following statements checks if the optional parameters where used or not
  if(!after.isEmpty) options+=s"after:$after,"
  if(!before.isEmpty) options+=s"before:$before,"
  if(!first.equals(0)) options+=s"first:$first, "
  if(!last.equals(0)) options+=s"last:$last,"

  var query=s" stargazers($options) {"    //rest is usual as nodes are list of users they are nested
  def setNodeList(user: User):this.type ={
    query+=s" nodes ${user.build()}"
    this
  }
  def gettotalCount():StargazerConnection={
    query=query+" totalCount"
    this
  }
  def build():String={
    query=query+"}"
    query
  }
}

