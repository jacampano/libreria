import groovy.json.JsonSlurper


def call(mapVulnerabilidadesBloqueantes) {
    try {
        dependencyCheck additionalArguments: '''--format ALL''', odcInstallation: 'DC-5.2.4'
    } catch (err) {
        echo err
    }
    echo currentBuild.result

    cotejarResultados(mapVulnerabilidadesBloqueantes)
}


@NonCPS
def cargarFichero(filename) {

    def jsonSlurper = new JsonSlurper()
    return jsonSlurper.parse(new File("${env.WORKSPACE}/${filename}"))
}

@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}


def cotejarResultados(mapVulnerabilidadesBloqueantes) {
    
    def data = [:]
    def filename = 'dependency-check-report.json'
    def vulnerabilidadesBloqueantesEncontradas = []
    mapVulnerabilidadesBloqueantes = jsonParse(mapVulnerabilidadesBloqueantes)

        if (fileExists(filename)) {
            data = cargarFichero(filename)
            //data = readJSON file: "${env.WORKSPACE}/${filename}"

        } else {
            println('[Advertencia] No ha sido posible obtener el fichero de análisis.')
        }

    println("Identificadores de vulnerabilidades bloqueantes configurados: "+ mapVulnerabilidadesBloqueantes)
    println("Identificadores de vulnerabilidades detectadas: "+ data.dependencies.vulnerabilities.name.unique())


    boolean encontrada = false

    data.dependencies.vulnerabilities.name.each { dependencia ->
        mapVulnerabilidadesBloqueantes.each { vulnCrit ->
            if(dependencia.contains(vulnCrit.name)) {
                vulnerabilidadesBloqueantesEncontradas << vulnCrit
                encontrada = true
            }
        
        }

    }


    if (encontrada) {
        println("Se han encontrado vulnerabilidades bloqueantes en las dependencias. No se continuara con la construcción hasta que se subsanen")
        println("A continuación se muestrá el listado de las vulnerabilidades bloqueantes encontradas:" + vulnerabilidadesBloqueantesEncontradas.unique())
        println("Es posible que aparezcan identificadores repetidos. Consultar el informe generado para más información.")
        println("Las vulnerabilidades bloqueantes son aquellas que se han indicado explicitamente en la configuración de CadenaUnica y que si se encuentran, abortan la ejecución de la misma, imposibilitando cualquier despliegue desde el pipeline.")
        currentBuild.result = 'ABORTED'
        error('[Error] Existen vulnerabilidades bloqueantes')

    } else if (data.dependencies.vulnerabilities.name.size()>=1) {
        println("No se han encontrado vulnerabilidades bloqueantes. Las vulnerabilidades bloqueantes son aquellas que se han indicado explicitamente en la configuración de CadenaUnica y que si se encuentran, abortan la ejecución de la misma, imposibilitando cualquier despliegue desde el pipeline. Si se han encontrado otras vulnerabilidades. Se recomienda revisar el informe generado para más información")
    } else {
        println("Parece que no se han encontrado vulnerabilidades, aunque es recomendable revisar el informe generado para asegurarlo")
    }





}