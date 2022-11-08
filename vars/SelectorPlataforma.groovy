def call() {

  println("-----------------SELECTOR Plataforma--------------")

  mensaje="Seleccionar plataforma"


  timeout(time:5, unit: 'DAYS') {

      USER_INPUT = input(
        message: "${mensaje}",
        submitter: 'calidad', //Usuario o grupo que realiza la acción
        parameters: [choice(choices: 'servidorAplicaciones\ncontenedores\nambos\n', description: '', name: 'Plataforma')])
    }

    echo "Respuesta usuario: ${USER_INPUT}"


    return USER_INPUT
}

