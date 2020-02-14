import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def lsbno = resultJson.number
  echo "$lsbno"
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'jenkins lastsuccessbuildno=${lsbno}' 
"""
}
def call(JSON){
def jsonString = JSON
def jsonObj = readJSON text: jsonString

String a=jsonObj.ci1.jobs.job.job_name
String jname=a.replaceAll("\\[", "").replaceAll("\\]","");
   sh 'curl -s http://http://18.219.219.69:8080/job/${jname}/lastSuccessfulBuild/api/json --user vj:11e428e94b267ffbab27fa713e2da8e6e8 -o ouput.json'
create()
}
