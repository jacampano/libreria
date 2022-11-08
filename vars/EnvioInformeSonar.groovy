def call (String userEmail, String productName,String notificarTodo, String notificarA) {
    
    if(notificarTodo=="true") {

		echo "--- Se ha seleccionado que se notifiquen todos los eventos. Se va a realizar la notificación de los resultados de Sonar ---"
		def sonarProperties = readProperties  file:'sonar.properties'
		def sonarID = sonarProperties['sonar.projectKey']


    	emailext body: "Puede consultar el resultado de la ejecución en https://intranet.chap.junta-andalucia.es/sonar7-cci/dashboard?id="+sonarID ,
		subject: "[CALIDAD][PIPELINE][SONAR] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
		to: userEmail + ","+notificarA

	} else {
		echo "--- Se ha desactivado la notificación para todos los eventos. No se realiza el envío de los resultados de Sonar. Solo se realizará una única notificación con el resultado global de la ejecución ---"
	}
	
	

}