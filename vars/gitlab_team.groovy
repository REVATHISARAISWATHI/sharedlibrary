import groovy.json.*
import groovy.json.JsonOutput
def call(gitlab)
{
def jsonString = gitlab
def jsonObj = readJSON text: jsonString
  //println(jsonObj)
  def cnt =jsonObj.gitlab.gitlab.commit_cnt
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(cnt)
}
