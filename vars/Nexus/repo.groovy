def call(JSON){
def jsonString = JSON
def jsonObj = readJSON text: jsonString


String b=jsonObj.brm.repositories.repository.id
String repoid=b.replaceAll("\\[", "").replaceAll("\\]","");
    withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
    sh   'curl  -u admin:admin123 -H "Accept: application/json" "http://3.15.18.214:8081/nexus/service/local/repositories/repo/status" ' 
}
