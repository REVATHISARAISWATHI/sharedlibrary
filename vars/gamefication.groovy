import groovy.json.*
import groovy.json.JsonOutput
def call(jsondata,bamboo1)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.config.emails.email.size()

 
 List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();

  for(j=0;j<ecount;j++)
   {
	 def email=jsonObj.config.emails.email[j] 
	   
	    
     // name="Bamboo"
    //  def jsonStringb = bamboo
def jsonObjb = readJSON text: bamboo1
  //println(jsonObj)
  def scnt =jsonObjb.Bamboo.individualsuccess[j].Success_cnt
  def fcnt =jsonObjb.Bamboo.individualfailure[j].Failure_cnt
 def email1=jsonObjb.Bamboo.individualsuccess[j].email
      
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(scnt)
 int score=0
 if(email==email1 && scnt>10)
  {
   score=score+10 
    LIST.add(["metric":"No of more successful builds","score":score])
    score=0
  }
     if(email==email1 && fcnt<2)
  {
   score=score+10 
    LIST.add(["metric":"No of least failure builds","score":score])
    score=0
  }
	   JSON1=LIST.clone()
   JSON.add(["email":email,"metrics":JSON1])
    LIST.clear()
    }
     
    
  println(JSON)
}
