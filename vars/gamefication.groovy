import groovy.json.*
import groovy.json.JsonOutput
def call(bamboo1)
{
println(bamboo1)
  /*def jsonSlurper = new JsonSlurper()
  def reader = new BufferedReader(bamboo1)
  def resultJson = jsonSlurper.parse(reader)
  def cnt =resultJson.bamboo.teamsuccessbuild_cnt
  println(cnt)*/
  def cnt=bamboo1.bamboo.teamsuccessbuild_cnt
}
