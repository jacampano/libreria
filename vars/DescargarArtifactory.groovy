def call(String versionJDK, String versionMaven,String rutaPOM, String servidor, String credenciales) {

    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {

        GroupId = readMavenPom(file: "${rutaPOM}").getGroupId()
        ArtifactId = readMavenPom(file: "${rutaPOM}").getArtifactId()
        Version = readMavenPom(file: "${rutaPOM}").getVersion()
        Type = readMavenPom(file: "${rutaPOM}").getPackaging()
        
        instanciaArtifactory = Artifactory.server servidor
        instanciaArtifactory.bypassProxy = true
        instanciaArtifactory.credentialsId = credenciales
        def descargarDesde = null
        
        descargarDesde ="ja-artifacts-deploy/"+ GroupId.replaceAll("\\.", "\\/") + "/" + ArtifactId + "/" + Version + "/*"
        echo "Ruta despliegue: "+"${descargarDesde}"
        
        //Nos vamos a descargar  todo lo que este disponible para la versión          
        def downloadSpec = """{
                          "files": [
                                 {
                                  "pattern": "${descargarDesde}",
                                  "target": "temporal/"
                                 }
                                 ]
                                }"""
                 
        instanciaArtifactory.download spec: downloadSpec
    }

}