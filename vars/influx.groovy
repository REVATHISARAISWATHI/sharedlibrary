import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def total = resultJson
  def commit=total.commits
String oldpass=commit.replace(/[\[\]]+/g,'')
  echo "$commit"
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'gitlabcommit =${oldpass}' 
"""
}


def call()
{
create()
}
