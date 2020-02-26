import groovy.json.*
 import groovy.json.JsonOutput


def call(JSON,IP)
{
def jsonString = JSON
def jsonObj = readJSON text: jsonString
def mailcount = jsonObj.config.emails.email.size()

 sh """ curl -X GET \
  'http://18.220.143.53:8085/rest/api/latest/result/LAT-WEB.json?max-result=50&expand=results.result.artifacts&expand=changes.change.files&start-index=0' \
  -H 'authorization: Basic cmlnOnJpZ2FEYXB0QGRldk9wcw==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 50b866a3-885a-2d59-ea9d-b76fb8b13a16'  -o output.json """

//  sh "curl -G -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/result/LAT-WEB.json?max-result=50%26expand=results.result.artifacts%26expand=changes.change.files%26start-index=0  -o output.json"
 println(mailcount)
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
//def state=resultJson.results.result[0].buildCompletedDate
  //println(state)
 def cns=0
 def cnf=0
 def cnus=0
    List<String> SUCCESS = new ArrayList<String>();
    List<String> FAILURE = new ArrayList<String>();
    List<String> USER = new ArrayList<String>();
 


  for(i=0;i<50;i++)
  {
 
   def date=resultJson.results.result[i].buildCompletedDate
   //println(date)
   def state=resultJson.results.result[i].buildState
  // println(state)
  
   
  if(state.equals("Successful"))
  {
   for(j=0;j<mailcount;j++)
   {
   def email=jsonObj.config.emails.email[j]
   if(results.result[i].buildReason.contains(email))
   {
   cnus++
   user.add(JsonOutput.toJson(resultJson.results.result[i]))
    
   }
   }
   // def user=resultJson.buildReason
   // println(user)
    //String res = user.substring(3, user.indexOf(' '))
   /* def val = user.split('>')
    //def str=val[1]
     def vals = val[1].split('<')
     def username=vals[0].split(' ')
     println(username[0])*/
   //echo "hi"
   //filename = args[i]

  cns++
   //JSONObject obj=new JSONObject(output.json); 
   //JSONArray success=obj.getJSONArray(resultJson.results.result[i]);
  // println(success)
 /*  def json_str = JsonOutput.toJson(resultJson.results.result[i])
def json_beauty = JsonOutput.prettyPrint(json_str)
File file = new File(filename)
file.write(json_beauty)*/
   //List<String> JSON = new ArrayList<String>();
  // SUCCESS.add(JsonOutput.toJson(resultJson.results.result[i]))
      //def jsonString = JSON
   //def jsonObj = readJSON text: JSON
 
   //println(JSON)

  // println(JsonOutput.toJson(resultJson.results.result[0]))
   //sh "echo  ${JsonOutput.toJson(resultJson.results.result[0])} > bam.json"
    
  }
   else if(state.equals("Failed"))
   {
    cnf++
       //FAILURE.add(JsonOutput.toJson(resultJson.results.result[i]))
     
   }
  }
 //println(cnt)
// println(Success)
 println(cnf)
 println(user)
 //echo "$cnt"
}
