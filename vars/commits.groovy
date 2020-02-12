import groovy.json.*

@NonCPS
influx(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson.size()
 
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'gitlab gitlabcommit=${total}' 
"""
}

def call(){
//sh "curl -X GET -i -H  -d https://gitlab.com/api/v4/projects/16837979/repository/commits"
def jso=sh "curl -s https://gitlab.com/api/v4/projects/16837979/repository/commits --user Priyakumar:przqBFp-r6X7K_3okz5v -o ouput.json"
  echo "$jso"
  influx()
}
