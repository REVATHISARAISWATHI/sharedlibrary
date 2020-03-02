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
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)


   
 //ArrayList<Student> students = new ArrayList<Student>();

//HashMap<String,List<String>> map = new HashMap<>();
 //Map<Object,ArrayList<Object>> map = new HashMap<>();
 


  List<String> USERS = new ArrayList<String>()
	List<String> USERF = new ArrayList<String>()
 List<String>  LISTSUCCESS=new ArrayList<String>()
	List<String> LISTFAILURE=new ArrayList<String>()
	List<String> SUCCESS = new ArrayList<String>()
    List<String> FAILURE = new ArrayList<String>()
  //List<String> SUSER=new ArrayList<String>()
  
	//user [] u=new user[mailcount]
// List<Person> people = new ArrayList<>();

 

println(mailcount)
	def jsonBuilder = new groovy.json.JsonBuilder()
// def arr= new int[mailcount]
   for(j=0;j<mailcount;j++)
   {
	   def cns=0
	   def cnf=0
    def email=jsonObj.config.emails.email[j] 
  for(i=0;i<50;i++)
  {
 
   
   def state=resultJson.results.result[i].buildState
  
   if(resultJson.results.result[i].buildReason.contains(email) && state.equals("Successful"))
   {
   
    USERS.add(resultJson.results.result[i])
	  
   }
   else if(resultJson.results.result[i].buildReason.contains(email) && state.equals("Failed"))
   {
	   
	   USERF.add(resultJson.results.result[i])
   }
   }
   cns=USERS.size()
   LISTSUCCESS.add(JsonOutput.toJson(["email":email,"success":USERS,"Success_cnt":cns]))
   USERS.clear()
   cnf=USERF.size()
   LISTFAILURE.add(JsonOutput.toJson(["email":email,"failure":USERF,"Success_cnt":cnf]))
   USERF.clear()
   }
	for(i=0;i<50;i++)
  {
   def date=resultJson.results.result[i].buildCompletedDate
   def state=resultJson.results.result[i].buildState

   
  if(state.equals("Successful"))
  {
   
 
   SUCCESS.add(resultJson.results.result[i])
     
  }
   else if(state.equals("Failed"))
   {
    
       FAILURE.add(resultJson.results.result[i])
     
   }
  }
	
		    jsonBuilder.bamboo(
  "success" : SUCCESS,
  "successbuild_cnt" : SUCCESS.size(),
  "failure" : FAILURE,
  "failurebuild_cnt" :FAILURE.size(),
  "individualsuccess": LISTSUCCESS,
  "individualfailure": LISTFAILURE
  )
	def json = jsonBuilder
def jsonObj1 = readJSON text: json
	//def count=jsonObj1.bamboo.failurebuild_cnt

	println(jsonObj1.bamboo)

//println(jsonBuilder.toPrettyString())
	

}
