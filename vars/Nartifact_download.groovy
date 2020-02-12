def call(){
//def jsonString = JSON
//println(jsonString)
//def jsonObj = readJSON text: jsonString
//println(jsonObj.brm)

//String a=jsonObj.data.name
//String repoName=a.replaceAll("\\[", "").replaceAll("\\]","");
//String b=jsonObj.brm.repositories.repository.id
//String repoid=b.replaceAll("\\[", "").replaceAll("\\]","");
    withCredentials([usernamePassword(credentialsId: 'nexus_cred', passwordVariable: 'password', usernameVariable:'username')]) {

        sh ' curl -v -F r=pri -F hasPom=true -F e=war -F file=@pom.xml -F file=**/*.war -u admin:admin123 "http://3.15.18.214:8081/nexus/service/local/artifact/maven/content" '
}
}
