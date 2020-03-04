import groovy.json.*
import groovy.json.JsonOutput
def call(bamboo1)
{
  def jsonSlurper = new JsonSlurper()
  def resultJson = jsonSlurper.parse(bamboo1)
  def cnt =resultJson.bamboo.teamsuccessbuild_cnt
  println(cnt)
}
