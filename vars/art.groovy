def call(){
  withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {
  sh 'wget  --user=admin --password=admin123 "http://3.15.18.214:8081/nexus/service/local/repositories/pri/content/Codekillers/dynamic_new/1.0/dynamic_new-1.0.war" '
}
}
