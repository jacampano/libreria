def call(versionJDK,entorno) {
    withEnv(["JAVA_HOME=${ tool versionJDK}"]) {
        def runner = "/opt/SmartBear/SoapUI-5.5.0/bin/testrunner.sh"
        entorno = entorno.toUpperCase()

        def resultado=sh (script: "${runner} -r -a -f ./resultados -sTest_automatizados -cFuncionales -PENTORNO=${entorno} ./SOAP-UI/circuitoExpedientesCC_EMISOR-soapui-project.xml > salida.txt", returnStatus:true)
        println("Resultado: "+resultado)
       
        
        if (resultado !=0 ) {
            // EnvioInformePruebasFuncionalesSOAPUI(userEmail, productName,notificarTodo,notificarA,resultado)
            currentBuild.result = 'FAILED'
            error('[Error] Ha fallado la verificación funcional de WS')
        }
    }
}