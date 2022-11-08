def call (String userEmail, String productName,notificarTodo,notificarA, resultado) {
	

	def log = null

	if(notificarTodo=="true") {

		
		if (fileExists('salida.txt'))  {
			log = readFile('salida.txt').trim()
        	sh 'rm salida.txt'

		} 


		if (resultado != 0 && log != null) {
			
			emailext body: "Se han producido errores en la verficicación funcional de los WS. Por favor, revise el log para más información: ${log}" ,
			subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES WS] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
			to: userEmail + ","+notificarA

		} 	else if (resultado != 0 && log == null ) {
			
			emailext body: "Se han producido errores en la verficicación funcional de los WS. No ha sido posible obtener el log de las pruebas. Revise el log completo del job" ,
			subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES WS] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
			to: userEmail + ","+notificarA
		} else if (resultado == 0 && log != null) {
			emailext body: "La verificación funcional de los WS se ha realizado correctamente. A cotninuación se muestran los detalles de la ejecución: ${log}" ,
			subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES WS] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
			to: userEmail + ","+notificarA

		} else {
			emailext body: "La verificación funcional de los WS se ha realizado correctamente.  No ha sido posible obtener el log de las pruebas. Revise el log completo del job" ,
			subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES WS] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
			to: userEmail + ","+notificarA


		}
	}

}