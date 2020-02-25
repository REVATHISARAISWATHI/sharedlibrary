import groovy.json.*

def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString

int ecount = jsonObj.config.emails.email.size()
println("No of users "+ ecount)
 
// withCredentials([usernamePassword(credentialsId: 'bitbucket_cred', passwordVariable: 'pass', usernameVariable: 'userId')]) {
sh "curl -X GET  -H -d  -u Priyakumar:pri4jay16 https://gitlab.com/api/v4/projects/17097955/repository/commits -o output.json" 
 //} 
def jsonSlurper = new JsonSlurper()
def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"))
def total = resultJson.size
// echo "Total no.of commits in ${repoName} $total"
def commiter=1
List<String> JSON = new ArrayList<String>();

for(i=0;i<ecount;i++)
 {
  for(j=0;j<total;j++)
  {
   if(jsonObj.config.emails.email[i]==resultJson[j].author_email)
   {
	   JSON.add(JsonOutput.toJson(resultJson.values[j]))
  //  y = resultJson.values[j];
    //echo "y >>commiter'${commiter}'.txt"
	//   sh "echo '${y}' >>commiter'${commiter}'.json "
    }
	  //Long commitdate=resultJson.values.committerTimestamp[j]
	  //def name=resultJson.values.author[j].name
	  //println(name)
	  //def email=resultJson.values.author[j].emailAddress
	  //println(email)
	 // sh "echo contributorsName :'${name}', contributorsEmail :'${email}' >>commiter'${commiter}'.txt "
         
	
   }
	
  }
 
/*
def jsonSlurper = new JsonSlurper()
def jsonString1 = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"))	
//def jsonString1 = output.json
def jsonObj1 = readJSON text: jsonString1	
String total=jsonObj1.size
String commits=total.replaceAll("\\[", "").replaceAll("\\]","");
println(commits)
	
*/	
println(JSON)

 }
