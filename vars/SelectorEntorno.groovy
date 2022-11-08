def call() {

  println("-----------------SELECTOR Entornos--------------")

  mensaje="Seleccionar entorno"


  timeout(time:5, unit: 'DAYS') {

      USER_INPUT = input(
        message: "${mensaje}",
        submitter: 'calidad', //Usuario o grupo que realiza la acción
        parameters: [choice(choices: 'PXP\nEXP\n', description: '', name: 'Entorno')])
    }

    echo "Respuesta usuario: ${USER_INPUT}"


    return USER_INPUT
}

