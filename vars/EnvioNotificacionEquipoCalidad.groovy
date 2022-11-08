def call (String productName, String notificarCalidad) {
	
	emailext body: "Se han ejecutado todas las tareas en entornos de validación. Por favor, valide el proyecto si quiere continuar con el despliegue en otros entornos." ,
	subject: "[CALIDAD][PIPELINE][VALIDACION CALIDAD] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER} ${currentBuild.result}",
	to: notificarCalidad

}