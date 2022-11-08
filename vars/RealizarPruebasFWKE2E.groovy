def call(String navegador) {
    
    //navegador: para selección de navegador en próximas versiones
     withCredentials([string(credentialsId: 'credendialesHarbor', variable: 'PASS')]) { 
       
        sh("docker login --username calidad --password $PASS harbor.chie.junta-andalucia.es/sw_base_calidad/fwk_api:latest")
    }
       println("WORKSPACE:"+env.WORKSPACE)
       println("PWD:"+PWD)
        sh "docker run --rm  -v $env.WORKSPACE/tests:/project  harbor.chie.junta-andalucia.es/sw_base_calidad/fwk_e2e:latest"

}