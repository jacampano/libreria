def call() {

          try {
                    sleep(10) //Por alguna razón, si no se pone pausa el hilo, no se garantiza ejecutar correctamente la tarea. ¿Posible condición de carrera? TODO: Revisar y solucionar
                    timeout(time: 1, unit: 'MINUTES') {
                    def qg = waitForQualityGate() 
                    if (qg.status != 'OK') {
                        error "${qg.status}: No se ha superado el umbral requerido de calidad "
                    }
                
                }

               }catch (err) {
                         echo err
                         }
               echo currentBuild.result
     
}
