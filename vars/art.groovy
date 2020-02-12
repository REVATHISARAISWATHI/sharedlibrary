def call(){
  sh 'wget  --user=admin --password=admin123 "http://3.15.18.214:8081/nexus/service/local/repositories/pri/content/dynamic_new-1.0.war" '
}
