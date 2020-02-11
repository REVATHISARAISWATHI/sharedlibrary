def call(){
//sh "curl -X GET -i -H  -d https://gitlab.com/api/v4/projects/16837979/repository/commits"
def jso=sh "curl -s https://gitlab.com/api/v4/projects/16837979/repository/commits --user Priyakumar:przqBFp-r6X7K_3okz5v"
  println(jso)
}
