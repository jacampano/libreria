def call(String urlAPIGIT, productName) {

   def listadoProyectos = null
   withCredentials([string(credentialsId: 'TOKEN_GITLAB', variable: 'TOKEN')]) { 
        listadoProyectos = jsonParse(["curl", "-H", "PRIVATE-TOKEN: ${TOKEN}", "-k", "--url", "https://gitlabexp.chap.junta-andalucia.es/api/v4/search?scope=projects&search=${productName}"].execute().text) 
       
    }
    
   return listadoProyectos

}

@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}
