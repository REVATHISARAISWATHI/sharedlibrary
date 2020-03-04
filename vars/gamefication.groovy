import groovy.json.*
import groovy.json.JsonOutput
def call(bamboo1)
{
//println(bamboo1)
  //def jsonSlurper = new JsonSlurper()
  //def reader = new BufferedReader(bamboo1)
 // def resultJson = jsonSlurper.parse(bamboo1)
  //def cnt =resultJson.bamboo.teamsuccessbuild_cnt
  def res=JSON.parseText(bamboo1)
 println(res)
}
