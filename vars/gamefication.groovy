import groovy.json.*
import groovy.json.JsonOutput
def call(jsondata,bamboo1)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.config.emails.email.size()
def team=jsonObj.riglet_info.name
//println(bamboo1)

 
 List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();
def jsonBuilder = new groovy.json.JsonBuilder()

  for(j=0;j<ecount;j++)
   {
	 def email=jsonObj.config.emails.email[j] 
	   
	  //println(email)  
     // name="Bamboo"
      def jsonString1 = bamboo1
     def jsonObj1 = readJSON text: jsonString1
  println(jsonObj1)
  def scnt =jsonObj1.Bamboo.individualsuccess[j].Success_cnt
  def fcnt =jsonObj1.Bamboo.individualfailure[j].Failure_cnt
 def email1=jsonObj1.Bamboo.individualsuccess[j].email
      
 // def res=bamboo1.bamboo.teamsuccessbuild_cnt
 // def obj = JSON.parse(bamboo1)
 println(scnt)
 int score=0
	   int reward=0
 if(email==email1 && scnt>1)
  {
   score=score+10 
    LIST.add(["metric":"No of more successful builds","score":score])
	reward=reward+score  
    score=0
  }
     if(email==email1 && fcnt<2 && scnt!=0)
  {
   score=score+10 
    LIST.add(["metric":"No of least failure builds","score":score])
	  reward=reward+score 
    score=0
  }
	   JSON1=LIST.clone()
	   
   JSON.add(["email":email,"reward": reward,"metrics":JSON1])
    LIST.clear()
    }
	 jsonBuilder(
		 "team":team,
		 "metrics":JSON
  
  )
     
    
  println(jsonBuilder)
}
