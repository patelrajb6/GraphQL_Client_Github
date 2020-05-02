import net.liftweb.json.JsonAST.JValue
import net.liftweb.json._

class JsonToScala(private var json:String) {
  implicit val formats = DefaultFormats

  val casetype= Seq("repository","repositoryOwner","topic")
  var jValue:JValue=_

  def parseJson()={
    jValue=parse(json)
    this
  }

  //for the user if they dont know the type of the query
  def getScalaClass(): CaseClass= {
    val data = (jValue\"data").extract[Data]
    if(!data.repositoryOwner.isEmpty) return data.repositoryOwner.get
    if(!data.topic.isEmpty)  return data.topic.get
    if(!data.repository.isEmpty) return  data.repository.get
    if(!data.user.isEmpty) return data.user.get
    error("error")
  }

  //for users who know what type of queries will be returned
  def getUserQueryCaseClass():UserCase={
    (jValue\"data").extract[Data].user.get
  }
  def getRepoQueryCaseClass():RepositoryCase={
    (jValue\"data").extract[Data].repository.get
  }
  def getRepoOwnerQueryCaseClass():RepositoryOwnerCase={
    (jValue\"data").extract[Data].repositoryOwner.get
  }
  def getTopicQueryCaseClass():TopicCase={
    (jValue\"data").extract[Data].topic.get
  }


}
