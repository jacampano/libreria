import groovy.json.JsonSlurper
def call(String urlServidorConfiguracion) {
    
    def urlListadoProductos= urlServidorConfiguracion+"/?keys"
    def respuestaListadoProductos = new URL(urlListadoProductos).openConnection()
    def responseCodeListadoProductos = respuestaListadoProductos.getResponseCode()

   Set listaProductos = []

    println("Codigo respuesta: "+responseCodeListadoProductos);

    if(responseCodeListadoProductos.equals(200)) {
        
        listaProductos = procesar(respuestaListadoProductos.getInputStream().getText())
    }

    println("--- Fin Obtener Listado Productos ---")

    return listaProductos

}

@NonCPS
def procesar(String respuesta) {
    Set listadoProductos = []
    println("DENTRO DE PROCESAR LISTA")
   
    def i=0;

    ruta = respuesta.split(',')
   
    while (i<ruta.size()) {
        producto = ruta[i].split('/')
        listadoProductos.add(producto[0].replace('"','').toString())
        i++;
    }

    listadoProductos.remove(0)
    return listadoProductos
}




