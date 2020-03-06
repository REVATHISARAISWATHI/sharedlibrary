import groovy.json.*
import groovy.json.JsonOutput
def call(jsondata,merge)
{
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
//int ecount = jsonObj.config.emails.email.size()
def team=jsonObj.riglet_info.name
     def jsonString1 = merge
        def jsonObj1 = readJSON text: jsonString1
List<String> JSON = new ArrayList<String>();
  List<String> LIST = new ArrayList<String>();
  List<String> JSON1 = new ArrayList<String>();
  def jsonBuilder = new groovy.json.JsonBuilder()
	

 /* for(j=0;j<ecount;j++)
   {
	 def email=jsonObj.config.emails.email[j]*/
	    def mcnt =jsonObj1.gitlab.merge_cnt
       //def email1=jsonObj1.gitlab.individual_commit_Details[j].email
        println(mcnt)
        int score=0
        if(mcnt>2)
        {
        score=score+10
         LIST.add(["metric":"No of merge_requests","score":score])
         }
            JSON1=LIST.clone()
	   
   JSON.add(["metrics":JSON1])
    LIST.clear()
    }
	 jsonBuilder(
		 "team":team,
		 "metrics":JSON
  
  )
     
    
  println(jsonBuilder)

File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/merge_score.json")
	file.write(jsonBuilder.toPrettyString())
}
