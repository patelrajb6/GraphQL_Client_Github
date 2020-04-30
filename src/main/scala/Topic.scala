class Topic {
  var query="{ stargazers("
  def setafter(after:String):Topic={
    var log= "\\\""+after+"\\\""
    query=query+s"after:$log"
    this
  }
  def setbefore(before:String):Topic={
    var log= "\\\""+before+"\\\""
    query=query+s"before:$log"
    this
  }
  def setfirst(first:Int):Topic={

    query=query+s"first:$first"
    this
  }
  def setlast(last:Int):Topic={

    query=query+s"last:$last"
    this
  }
  def getname():Topic={
    query=query+"name"
    this
  }
  def build():String={
    query=query+"}"
    query
  }

}
