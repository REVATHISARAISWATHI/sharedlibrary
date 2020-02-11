def call(){


   // withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
      def status= sh'  curl -X GET -i -H "Accept:application/json" -d  -u admin:admin123 "http://3.15.18.214:8081/nexus/service/local/repositories/id1/status" -o output1.json '
 

}
