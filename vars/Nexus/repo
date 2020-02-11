def call(JSON){
def jsonString = JSON
def jsonObj = readJSON text: jsonString


String b=jsonObj.brm.repositories.repository.id
String repoid=b.replaceAll("\\[", "").replaceAll("\\]","");
    withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
sh "curl -X GET -i -H "Accept: application/json" -d  -u $username:$password http://3.15.18.214:8081/nexus/service/local/repositories/${repoid}/status"
 
}
