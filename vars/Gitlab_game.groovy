import groovy.json.*
import groovy.json.JsonOutput
def call(jsondata,gitlab)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
int ecount = jsonObj.config.emails.email.size()
def team=jsonObj.riglet_info.name
     def jsonString1 = gitlab
        def jsonObj1 = readJSON text: jsonString1
List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();
  def jsonBuilder = new groovy.json.JsonBuilder()
	

  for(j=0;j<ecount;j++)
   {
	 def email=jsonObj.config.emails.email[j] 
	    def ccnt =jsonObj1.gitlab.individual_commit_Details[j].Commit_cnt  
       def email1=jsonObj1.gitlab.individual_commit_Details[j].email
        println(ccnt)
       // int score=0
       // if(email==email1 && ccnt>5)
        {
        //score=score+10
         LIST.add(["metric":"No of commits","value":ccnt])
         }
            JSON1=LIST.clone()
	   
   JSON.add(["email":email,"metrics":JSON1])
    LIST.clear()
    }
	 jsonBuilder(
		 "team":team,
		 "metrics":JSON
  
  )
     
    
  println(jsonBuilder)

File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/score.json")
	file.write(jsonBuilder.toPrettyString())
}
