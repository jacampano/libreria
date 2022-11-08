def call (String userEmail, String productName,String notificarTodo,String notificarA,String estado, String entorno) {
		
	if(notificarTodo=="true") {
			
		if(estado=="OK") {

			emailext body: "El despliegue de la aplicación en el entorno ${entorno} se ha realizado correctamente" ,
			subject: "[CALIDAD][PIPELINE][DESPLIEGUE][ENTORNO][${entorno}] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER}",
			to: userEmail + ","+notificarA


		} else {
			emailext body: "El despliegue de la aplicación en el entorno: ${entorno} ha fallado. Por favor, revise el log de la ejecución" ,
			subject: "[CALIDAD][PIPELINE][DESPLIEGUE][ENTORNO][${entorno}][ERROR] Producto SW: " + productName + "  ID BUILD JENKINS# ${BUILD_NUMBER}",
			to: userEmail + ","+notificarA

		}
		
		
		
	}

}