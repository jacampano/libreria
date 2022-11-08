def call (String servidor, String WARaDesplegar, Map entorno ) {

    def credenciales = entorno["credencialesServidorAplicaciones"]
    def contextoDespliegue = entorno["contextoDespliegue"]
    def numeroNodos = entorno["numeroNodos"]
    def URLServidor = null
    def indiceUrlServidor = null

    println("--- Despliegue numeroNodos: "+numeroNodos)
    println("--- Contexto despliegue: "+contextoDespliegue)
    println("--- Fichero a desplegar: "+WARaDesplegar)
    println("--- credenciales: "+credenciales)
    println("--- Variable entorno: "+entorno.toString())
    println("--- PRUEBA ---")


    echo "--- DESPLIEGUE EN SERVIDOR DE APLICACIONES ---"
    
        switch (servidor) {
        case 'tomcat7':
            echo "-- SERVIDOR DE APLICACIONES: TOMCAT7 --"
            for(int i = 1;i < numeroNodos+1 ;i++) {
                
                indiceUrlServidor = "urlServidorAplicaciones_nodo"+i
                URLServidor = entorno[indiceUrlServidor]
                println("Despliege en nodo:"+i+" "+URLServidor)
                deploy adapters: [tomcat7(credentialsId: credenciales, path: '', url: URLServidor)], contextPath: contextoDespliegue, war: WARaDesplegar

            }

        break

        case 'tomcat8':
            echo "-- SERVIDOR DE APLICACIONES: TOMCAT8 --"
            for(int i = 1;i < numeroNodos+1 ;i++) {
                
                indiceUrlServidor = "urlServidorAplicaciones_nodo"+i
                URLServidor = entorno[indiceUrlServidor]
                println("Despliege en nodo:"+i+" "+URLServidor)
                deploy adapters: [tomcat8(credentialsId: credenciales, path: '', url: URLServidor)], contextPath: contextoDespliegue, war: WARaDesplegar

            }
            

        break

        case 'tomcat9':
            echo "-- SERVIDOR DE APLICACIONES: TOMCAT9 --"
            for(int i = 1;i < numeroNodos+1 ;i++) {
                
                indiceUrlServidor = "urlServidorAplicaciones_nodo"+i
                URLServidor = entorno[indiceUrlServidor]
                println("Despliege en nodo:"+i+" "+URLServidor)
                deploy adapters: [tomcat9(credentialsId: credenciales, path: '', url: URLServidor)], contextPath: contextoDespliegue, war: WARaDesplegar

            }
        break

        case 'jboss7':

         echo "-- SERVIDOR DE APLICACIONES: Jboss7 --"
            //ObtenerDesplegables()
            for(int i = 1;i < numeroNodos+1 ;i++) {
                
                indiceUrlServidor = "urlServidorAplicaciones_nodo"+i
                URLServidor = entorno[indiceUrlServidor]
                println("Despliege en nodo:"+i+" "+URLServidor)
                deploy adapters: [jboss7(credentialsId: credenciales, url: URLServidor)], contextPath: contextoDespliegue, war: WARaDesplegar

            }
        break

        default:

            echo "Servidor no compatible"
            currentBuild.result = 'ABORTED'
            error('Servidor no compatible')
            break
         }
        
    
}