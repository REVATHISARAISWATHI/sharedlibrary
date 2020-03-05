import groovy.json.*
import groovy.json.JsonOutput
def call(bamboo1)
{
def jsonString = bamboo1
def jsonObj = readJSON text: jsonString
  //println(jsonObj)
  def scnt =jsonObj.Bamboo.teamsuccessbuild_cnt
  def fcnt=jsonObj.Bamboo.teamfailure_cnt
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(scnt)
 def score=1
 if(scnt>10)
  {
   score=score*10 
  }
   if(fcnt<1)
  {
 score=score*10
  }
  println(score)
}
