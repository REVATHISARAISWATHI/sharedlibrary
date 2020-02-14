import groovy.json.*

@NonCPS
influx(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def count = resultJson.count
 
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'azureboard workitems=${count}' >test.txt """
 def res=new File('/var/lib/jenkins/workspace/' +JOB_NAME + '/test.txt').text 
  echo " ++++++++++++ $res"

if(res.contains("200")||res.contains("204"))
{
 echo " Data pushed successfully..."
}
 else
{
 echo "Error while pushing"
}
}
def call()
{
 
  sh """curl  -s -i -XGET https://dev.azure.com/vickysastryvs/d2/_apis/work/boardcolumns?api-version=5.1 --user vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya -o output.json"""


 influx()
 }
