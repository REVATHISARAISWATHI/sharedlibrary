import groovy.json.*

@NonCPS
commitproj()
{

/*def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/gitcheckout/projectid.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projId = resultJson.id*/
 
sh "curl -X GET  -H -d  -u Priyakumar:pri4jay16 https://gitlab.com/api/v4/projects/$projId/repository/commits -o output.json"


}

def call()
{
commitproj()
}
