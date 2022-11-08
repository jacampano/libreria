import groovy.json.JsonSlurper
def call(String productID) {
  
  println("-----------------SELECTOR Version--------------")
  productID=productID.replaceAll('\\[','').replaceAll('\\]','')
  
  println("ID de proyecto en GIT:"+productID)

  String cadenaDescripcion = ""
  String cadenaValor = ""

    
  def listadoTags = jsonParse(["curl", "-H", "PRIVATE-TOKEN: z8NVwQnkAnb5RFtLNbYf", "-k", "--url", "https://gitlabexp.chap.junta-andalucia.es/api/v4/projects/${productID}/repository/tags"].execute().text)
  
  if(listadoTags.size()<1 || listadoTags==null) {
    
    currentBuild.result = 'ABORTED'
    error('[Error] Se ha producido un error al obtener la lista de tags o esta es vacía')

  }
  

  mensaje="Seleccionar versión"
  
  listadoTags.each { tag ->
    cadenaDescripcion=cadenaDescripcion.concat(tag.name).concat(",")  

  }
cadenaValor=cadenaDescripcion

cadenaDescripcion=cadenaDescripcion.substring(0,cadenaDescripcion.length()-1)
cadenaValor=cadenaValor.substring(0,cadenaValor.length()-1)

println("Tags encontrados:"+cadenaValor)

timeout(time:5, unit: 'DAYS') {

      USER_INPUT = input(
        message: "Seleccione el tag a redesplegar",
        submitter: 'calidad', //Usuario o grupo que realiza la acción
       parameters: [extendedChoice(
         defaultValue: '', 
         description: '', 
         descriptionPropertyValue: cadenaDescripcion, 
         multiSelectDelimiter: ',', 
         name: 'Tag', 
         quoteValue: false, 
         saveJSONParameterToFile: false, 
         type: 'PT_SINGLE_SELECT', 
         value: cadenaValor, 
         visibleItemCount: 15)]
        )
    }


  println("TAG Seleccionado:"+USER_INPUT)
  return USER_INPUT

}


@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}
