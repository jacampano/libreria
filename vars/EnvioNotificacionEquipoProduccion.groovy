def call (String productName,String notificarProduccion, String entorno) {

	if (entorno == "PXP") {

		emailext body: "El equipo de calidad ha validado el despliegue. ¿Realizar el despliegue en entorno PXP?" ,
		subject: "[CALIDAD][PIPELINE][DESPLIEGUE PXP] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
		to: notificarProduccion

	} else {

		emailext body: "¿Realizar despliegue en entorno EXP?" ,
		subject: "[CALIDAD][PIPELINE][DESPLIEGUE EXP] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
		to: notificarProduccion
		
	}
	
}