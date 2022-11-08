def call (String userEmail, String productName,String notificarA) {
				
		
	emailext body: "Se ha iniciado la cadena de integración continua para el proyecto ${productName} <br/>"+
	"A continuación detallamos información sobre el contexto de ejecución: <br/>"+
	"gitlabUserName: ${gitlabUserName} <br/>"+
	"gitlabActionType: ${gitlabActionType} <br/>"+
	"gitlabSourceRepoSshUrl: ${gitlabSourceRepoSshUrl} <br/>"+
	"gitlabSourceBranch: ${gitlabSourceBranch}"  ,
	mimeType: 'text/html', 
	subject: "[CALIDAD][PIPELINE][INICIO] Producto SW: " + productName + " ID BUILD JENKINS# ${BUILD_NUMBER}",
	to: userEmail + ","+notificarA


}

def call(String adminEmail) {
	
	emailext body: "Se ha iniciado la cadena de integración continua para el proyecto ${JOB_NAME} de forma manual. Se aborta la ejecución",
	subject: "[CALIDAD][PIPELINE][INICIO] Producto SW: ${JOB_NAME} cancelado por ejecución manual",
	to: adminEmail
}