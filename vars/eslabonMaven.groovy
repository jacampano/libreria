def compilacion(String rutaPOM, String mavenGoalCompile, String versionMaven, String versionJDK, String productName, String entorno) {
    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
        withMaven(maven: versionMaven,jdk: versionJDK) {

                echo "--- COMPILACION ---"
                echo "JAVA_HOME: $JAVA_HOME"

                    if ( mavenGoalCompile != null && "X${mavenGoalCompile}" != "X") {
                        echo "--- SE HA RELLENADO mavenGoalCompile, por lo que se utiliza dicha cadena para lanzar la compilación ---"
                        sh "mvn ${mavenGoalCompile}"
                    } else {
                        echo "--- NO SE HA RELLENADO mavenGoalCompile ---"
                        def cadenaCompilacionDefecto= "mvn clean compile -DskipTests -Dmaven.test.skip=true -X -U -P"+entorno
                        echo "--- Se va a lanzar la compilación por defecto: "+cadenaCompilacionDefecto
                        sh cadenaCompilacionDefecto
                    }
             
             }
        }
  
}

def empaquetado(String rutaPOM, String mavenGoalBuild, String versionMaven, String versionJDK, String productName, String entorno) {
    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
         withMaven(maven: versionMaven) {

             echo "--- EMPAQUETADO ---"
             echo "JAVA_HOME: $JAVA_HOME"

            if ( "X${mavenGoalBuild}" != "X") {
                        echo "--- SE HA RELLENADO mavenGoalBuild, por lo que se utiliza dicha cadena para lanzar el empaquetado ---"
                        sh "mvn ${mavenGoalBuild}"
                    } else {
                        echo "--- NO SE HA RELLENADO mavenGoalCompile ---"
                        def cadenaEmpaquetadoDefecto= "mvn -f pom.xml clean install -DskipTests -Dmaven.test.skip=true -X -U -P"+entorno
                        echo "--- Se va a lanzar la compilación por defecto: "+cadenaEmpaquetadoDefecto
                        sh cadenaEmpaquetadoDefecto
                    }

        }
    }
  
}