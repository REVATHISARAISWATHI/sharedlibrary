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

 def cns=0
 def cnf=0
 def ct=0
 
    List<String> SUCCESS = new ArrayList<String>();
    List<String> FAILURE = new ArrayList<String>();
 //ArrayList<Student> students = new ArrayList<Student>();

//HashMap<String,List<String>> map = new HashMap<>();
 //Map<Object,ArrayList<Object>> map = new HashMap<>();
 Map<String, List<String>> map = new HashMap<String, List<String>>();


  List<String> USER = new ArrayList<String>();
	List<String> suser = new ArrayList<String>();
	//user [] u=new user[mailcount]
// List<Person> people = new ArrayList<>();

 

println(mailcount)
 def arr= new int[mailcount]
   for(j=0;j<mailcount;j++)
   {
    def email=jsonObj.config.emails.email[j] 
  for(i=0;i<50;i++)
  {
 
   
   def state=resultJson.results.result[i].buildState
  
   if(resultJson.results.result[i].buildReason.contains(email) && state.equals("Successful"))
   {
   
    USER.add(resultJson.results.result[i])
   
    ct++
    
    
   }

   }
    arr[j]=ct
   ct=0
	   suser[j]=(JsonOutput.toJson(USER))
	 //  map.put(email,suser)
	   USER.clear()
    
    
 
  }
 
println(suser[0])
	

}
