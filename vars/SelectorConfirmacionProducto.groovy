def call(def resultadoBusqueda) {

  println("-----------------SELECTOR Confirmación Producto--------------")
  def USER_INPUT = ""

  String cadenaDescripcion = ""
  String cadenaValor = ""
  String literalNombreProyecto="Nombre Proyecto: "
  String literalRepositorioURL="URL Repositorio: "
    
    resultadoBusqueda.each { producto ->
    
      cadenaDescripcion=cadenaDescripcion.concat(literalNombreProyecto).concat(producto.name).concat(" ").concat(literalRepositorioURL).concat(producto.http_url_to_repo).concat(",")
      cadenaValor=cadenaValor.concat(producto.id.toString()).concat(",")


    }

cadenaDescripcion=cadenaDescripcion.substring(0,cadenaDescripcion.length()-1)
cadenaValor=cadenaValor.substring(0,cadenaValor.length()-1)

println ("Cadena Descripcion: "+cadenaDescripcion)
println("Cadena Valor:"+cadenaValor)

timeout(time:5, unit: 'DAYS') {

      USER_INPUT = input(
        message: "Existe más de una coincidencia en GIT para el nombre seleccionado. Confirme el proyecto",
        submitter: 'calidad', //Usuario o grupo que realiza la acción
       parameters: [extendedChoice(
         defaultValue: '', 
         description: '', 
         descriptionPropertyValue: cadenaDescripcion, 
         multiSelectDelimiter: ',', 
         name: 'Confirmar', 
         quoteValue: false, 
         saveJSONParameterToFile: false, 
         type: 'PT_SINGLE_SELECT', 
         value: cadenaValor, 
         visibleItemCount: 15)]
        )
    }

  println("USER_INPUT:"+USER_INPUT.getClass())
  return USER_INPUT

}

