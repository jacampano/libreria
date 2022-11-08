def call(String versionMaven, String versionJDK,String entorno, String cucumberOptions) {
     
        withCredentials([string(credentialsId: 'credendialesHarbor', variable: 'PASS')]) { 
       
        sh("docker login --username calidad --password $PASS harbor.chie.junta-andalucia.es/sw_base_calidad/fwk_api:latest")
    }
       println("WORKSPACE:"+env.WORKSPACE)
       println("PWD:"+PWD)
        sh "docker run --rm -e entornoLanzamiento=${entorno} -v $env.WORKSPACE/tests:/project  harbor.chie.junta-andalucia.es/sw_base_calidad/fwk_api:latest"
  
}
