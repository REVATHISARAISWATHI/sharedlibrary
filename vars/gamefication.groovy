import groovy.json.*
import groovy.json.JsonOutput
def call(bamboo1)
{
//println(bamboo1)
  def jsonSlurper = new JsonSlurper()
  //def reader = new BufferedReader(bamboo1)
  def resultJson = jsonSlurper.parse(bamboo1)
  def cnt =resultJson.bamboo.teamsuccessbuild_cnt
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(cnt)
}
