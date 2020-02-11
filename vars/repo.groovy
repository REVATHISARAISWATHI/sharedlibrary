def call(){


   // withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
       sh 'curl -X GET -H  "Accept: application/json"  -u admin:admin123 "http://3.15.18.214:8081/nexus/service/local/repositories/id1/status" -o output1.json '
 

}
