def call (String userEmail, String productName,notificarTodo,notificarA) {
	
	if(notificarTodo=="true") {
			def html = readFile 'dependency-check-report.html'
	
		if(html.contains('<li><i>Vulnerabilities Found</i>:&nbsp;0</li>')) {
		
			echo "--- No se han detectado vulnerabilidades de la dependencias de seguridad ---"
			emailext mimeType: 'text/html', attachmentsPattern: '**/dependency-check-report.html',
			body: 'No se han detectado vulnerabilidades de la dependencias de seguridad',
			subject: "[CALIDAD][PIPELINE][ANALISIS DEPENDENCIAS] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
			to: userEmail + ","+notificarA
			
			}else {
			//Envio de informe de resultados
			emailext mimeType: 'text/html', attachmentsPattern: '**/dependency-check-report.html',
			body: '${FILE,path="dependency-check-report.html"}',
			subject: "[CALIDAD][PIPELINE][ANALISIS DEPENDENCIAS] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
			to: userEmail + ","+notificarA
		
		}

	}

}