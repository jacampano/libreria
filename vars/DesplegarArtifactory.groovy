def call(String versionJDK, String versionMaven,String rutaPOM, String servidor, String credenciales, Boolean isLibrary, Boolean esEntrega) {

    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
        echo "--- Es una libreria: " + isLibrary + "---"

        def buildInfo = null
        def serverDefine = Artifactory.server(servidor)
        serverDefine.bypassProxy = true
        serverDefine.credentialsId = credenciales
        
        // Creación de la instancia Artifactory Maven
        def rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = versionMaven
        rtMaven.resolver server: serverDefine, releaseRepo: 'ja-internal', snapshotRepo: 'ja-internal'

        try{
            if (esEntrega) {

                rtMaven.deployer server: serverDefine, releaseRepo:'ja-artifacts-deploy'

            } else {

                rtMaven.deployer server: serverDefine, releaseRepo:'ja-ci-deploy-snapshots', snapshotRepo: 'ja-ci-deploy-snapshots'

            }
                
            buildInfo = rtMaven.run pom: rutaPOM, goals: 'install -DskipTests -Dmaven.test.skip=true -B -U'
            serverDefine.publishBuildInfo buildInfo
        } catch  (Exception err) {

            echo "--- ENTRO DE EXCEPCION ---"
            println(err)
            currentBuild.result = 'ABORTED'
            error('[Error] Se ha producido un error en la subida a Artifactory')
        }
        return buildInfo
    }
    

}