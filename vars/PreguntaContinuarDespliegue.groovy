def call(String fase) {
  
  def USER_INPUT = null
  def equipo = null

  switch (fase) {
    case 'Visto Bueno Calidad':
      
      mensaje= "Equipo Calidad, ¿es apto para pasar a despliegue en PreExplotación?"
      equipo="calidad"

    break;

    case 'Despliege PreExplotacion':

      mensaje="Equipo Producción, ¿continuar con el despliegue en PreExplotación?"
      equipo="produccion"

    break;


    case 'Pruebas de aceptacion':

      mensaje="Equipo de Proyecto, ¿Las pruebas de aceptación han sido satisfactorias?"
      equipo="desarrollo"

    break;

    case 'Despliege Explotacion':

       mensaje="Equipo Producción, ¿Continuar con el despliegue en Explotación?"
       equipo="produccion"

    break;

  }

    timeout(time:5, unit: 'DAYS') {

      USER_INPUT = input(
          message: "${mensaje}",
          submitter: equipo,
          parameters: [
              [$class: 'ChoiceParameterDefinition',
                choices: ['Si','No'].join('\n'),
                name: 'Continuar',
                description: 'Seleccionar opción']
                ])
    }

    echo "Respuesta usuario: ${USER_INPUT}"

    return USER_INPUT
}
