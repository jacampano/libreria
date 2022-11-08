def call(String rutaPOM, String mavenGoalSelenium, String versionMaven, String versionJDK, String productName, String entorno) {
    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
        withMaven(
			
			maven: versionMaven) {
               
                    if ( "X${mavenGoalSelenium}" != "X") {
                        echo "--- SE HA RELLENADO mavenGoalSelenium, por lo que se utiliza dicha cadena para lanzar las pruebas automatizadas ---"
                        sh "mvn ${mavenGoalSelenium}"
                    } else {
                        echo "--- NO SE HA RELLENADO mavenGoalSelenium ---"
                        def cadenaSeleniumDefecto= "mvn -f pom.xml clean verify -P"+entorno
                        echo "--- Se va a lanzar la verificación con Selenium Por defecto por defecto: "+cadenaCompilacionDefecto
                        sh cadenaSeleniumDefecto
                    }
                
                    
             
             }
        }
  
}