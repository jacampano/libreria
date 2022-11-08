def call (String versionJDK, String versionMaven, String perfil,rutaPOM) {
    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
        withMaven(maven: versionMaven) 
        {

             if ( "X${rutaPOM}" != "X") {
                echo "--- SE HA RELLENADO rutaPOM, por lo que se utiliza dicha ruta para lanzar el goal de test ---"
                sh "mvn -f ${rutaPOM} test"
            } else {
                echo "--- NO SE HA RELLENADO rutaPOM. Se lanza la tarea de generación del site sobre el directorio de trabajo ---"
                    sh 'mvn test'
                
            }
            
                
        }
            

     }
}