def call (String userEmail, String productName,notificarTodo,notificarA,buildInfo) {
	
	echo "Nombre Objeto BuildInfo"+buildInfo.getName()
	def enlace = "http://deploytools01.chap.junta-andalucia.es/artifactory/webapp/builds/"+buildInfo.getName()+"/${BUILD_ID}"
	enlace = enlace.replaceAll(' ','%20')
  
	if(notificarTodo=="true") {
			
		emailext body: "La subida a artifactory se ha realizado correctamente. Puede ver un listado de los artefactos subidos visitando el siguiente enlace ${enlace}" ,
		subject: "[CALIDAD][PIPELINE][SUBIDA ARTIFACTORY] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
		to: userEmail + ","+notificarA
		
	}

}