import groovy.json.*
import groovy.json.JsonOutput
def call(gitlab)
{
def jsonString = bamboo1
def jsonObj = readJSON text: jsonString
  println(jsonObj)
  def cnt =jsonObj.Bamboo.teamsuccessbuild_cnt
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(cnt)
}
