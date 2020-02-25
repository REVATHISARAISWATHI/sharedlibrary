  def call(){


   // withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
sh "curl -X GET -i -H  -d  -u priyakumar:pri4jay16 https://gitlab.com/api/v4/projects/17097955/merge_requests"
}
