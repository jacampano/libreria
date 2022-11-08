def call(String plataforma, String productName, String etiqueta, LinkedHashMap mapEntorno,String nombreFase){
    
    
    echo "Fase actual: ${nombreFase}"
    
    switch(plataforma) {

        case 'OKD':

            ConstruirImagenOKD(false);

        break

        case 'TANZU':
            if (nombreFase == 'Despliegue en pruebas') {
                ConstruirImagenDocker(etiqueta,productName,mapEntorno["namespace"])
            }
            
            DesplegarImagenEnClusterKubernetes(productName, etiqueta, nombreFase, mapEntorno)

        break

        default:

            echo "Plataforma no compatible"
            currentBuild.result = 'ABORTED'
            error('Plataforma no compatible')
        break

    }

}