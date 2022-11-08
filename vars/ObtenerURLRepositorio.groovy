def call(String productID) {
    
    def proyectoGIT = null
     
    withCredentials([string(credentialsId: 'TOKEN_GITLAB', variable: 'TOKEN')]) { 
        proyectoGIT = jsonParse(["curl", "-s", "-H", "PRIVATE-TOKEN: ${TOKEN}", "-k", "--url", "https://gitlabexp.chap.junta-andalucia.es/api/v4/projects/${productID}/"].execute().text)
    }
   
    println("URL Repositorio: "+proyectoGIT.http_url_to_repo)
   
    return proyectoGIT.http_url_to_repo
    
    
}

@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}