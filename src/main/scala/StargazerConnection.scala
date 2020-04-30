class StargazerConnection {
  var query="{"
  def getNodeList():User={
    new User(query)
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
