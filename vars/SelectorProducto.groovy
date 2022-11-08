def call(Set listaProductos) {

  println("-----------------SELECTOR PRODUCTOS--------------")
  def USER_INPUT = ""
  
  String cadenaSeleccion = "Seleccione una opción"
    
    listaProductos.each { producto ->
    cadenaSeleccion=cadenaSeleccion.concat('\n').concat(producto)
    
  }

  mensaje="Seleccionar producto"


  timeout(time:5, unit: 'DAYS') {

      USER_INPUT = input(
        message: "${mensaje}",
        submitter: 'calidad', //Usuario o grupo que realiza la acción
        parameters: [choice(choices: cadenaSeleccion, description: '', name: 'Producto a desplegar')])
    }

    echo "Respuesta usuario: ${USER_INPUT}"


    return USER_INPUT
}

