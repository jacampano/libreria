def call(String rutaPOM, String versionJDK,String versionMaven) {
	withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
        withMaven(maven: versionMaven) 
        {
            
		sh 'mvn dependency:purge-local-repository -DreResolve=false -f '+rutaPOM
	    }
    }
	
}