def call(String versionJDK) {
     withEnv(["SONAR_SCANNER_OPTS=-Xms1024m -Xmx2048m","JAVA_HOME=${ tool versionJDK}"]) {
          scannerHome = tool 'SonarScanner'
          
          try {
          
               withSonarQubeEnv(credentialsId: 'Sonar7Token', installationName: 'Sonar') {
                    sh "${scannerHome}/bin/sonar-scanner -Dproject.settings=${WORKSPACE}/sonar.properties"
                    }
      

               }catch (err) {
                         echo err
                         }
               echo currentBuild.result
     }
     
}
