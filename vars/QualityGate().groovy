def call() {

          try {
                    sleep(10)
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
