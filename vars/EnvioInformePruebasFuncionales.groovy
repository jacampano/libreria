def call (String userEmail, String productName,notificarTodo,notificarA,frameworkPruebasAutomatizadas) {
	

	if(notificarTodo=="true") {

		switch(frameworkPruebasAutomatizadas) {

			case 'Selenium':

				emailext body: "La verificación funcional con Selenium ha realizado correctamente." ,
				subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES SELENIUM] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
				to: userEmail + ","+notificarA

			break;


			case 'SoapUI':

				emailext body: "La verificación funcional con SOAP-UI ha realizado correctamente." ,
				subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES SOAP-UI] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
				to: userEmail + ","+notificarA


			break;


			case 'FWKAPIs':

				try {
				
					zip zipFile: 'informe.zip', dir:'./autofwk-cucumber/target/generated-report/'

					echo "--- Se ha generado el informe de resultados FWKAPIs ---"
					emailext mimeType: 'application/zip', attachmentsPattern: '**/informe.zip',
					body: 'La verificación funcional con FWKAPIs ha realizado correctamente. Se adjunta informe de resultados',
					subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES FWKAPIs] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
					to: userEmail + ","+notificarA


				} catch (Exception e) {
					echo "--- ERROR GENERAR INFORME RESULTADOS ---" + e.toString()
					emailext body: "La verificación funcional con FWKAPIs ha realizado correctamente, pero no se ha podido adjuntar el informe. Por favor, revise la tarea" ,
					subject: "[CALIDAD][PIPELINE][PRUEBAS FUNCIONALES FWKAPIs] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
					to: userEmail + ","+notificarA


				}


			break;


		}


		
			
		
	}

}