def call(String versionJDK) {
     withEnv(["SONAR_SCANNER_OPTS=-Xms512m -Xmx1024m","JAVA_HOME=${ tool versionJDK}"]) {
          scannerHome = tool 'SonarScanner'
          
          try {
          
               withSonarQubeEnv(credentialsId: 'sonarToken', installationName: 'SonarLocal') {
                    sh "${scannerHome}/bin/sonar-scanner -Dproject.settings=${WORKSPACE}/sonar.properties"
                    }
      

               }catch (err) {
                         echo err
                         }
               echo currentBuild.result
     }
     
}
