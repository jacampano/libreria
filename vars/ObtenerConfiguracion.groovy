import groovy.json.JsonSlurperClassic
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

def nullTrustManager = [
    checkClientTrusted: { chain, authType ->  },
    checkServerTrusted: { chain, authType ->  },
    getAcceptedIssuers: { null }
]

def nullHostnameVerifier = [
    verify: { hostname, session -> true }
]

SSLContext sc = SSLContext.getInstance("SSL")
sc.init(null, [nullTrustManager as X509TrustManager] as TrustManager[], null)
HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
HttpsURLConnection.setDefaultHostnameVerifier(nullHostnameVerifier as HostnameVerifier)


def aux = prueba("https://k8s-consul.appstest.chie.junta-andalucia.es/v1/kv","TESTCADENA")

def prueba(String urlServidorConfiguracion, String proyecto) {
    
    def urlConfiguracion = urlServidorConfiguracion+"/"+proyecto+"/configuracion"
        def respuestaServidorConfiguracion = new URL(urlConfiguracion).openConnection()
    def codigoRespuestaServidorConfiguracion = respuestaServidorConfiguracion.getResponseCode()
    def configuracion = null

    def result = null

    println("urlConfiguracion: "+urlConfiguracion)
    println("Codigo respuesta: "+codigoRespuestaServidorConfiguracion)

    if(codigoRespuestaServidorConfiguracion.equals(200)) {
        try {
            println("ANTES")
            configuracion = jsonParse(respuestaServidorConfiguracion.getInputStream().getText())
            println("FIN")

        } catch (Exception ex) {
            println(ex)
        }
        
        
        if (configuracion != null)
        {
        
                
            println("Value: "+configuracion.Value)
            byte[] valorDecode = configuracion.Value[0].decodeBase64()
            
            def valor = new String(valorDecode)
            //println("Valor:"+valor)
            
            configuracion=new groovy.json.JsonSlurperClassic().parseText(valor)
            println(configuracion)
            println(configuracion.pipeline.madurez)
            configuracion.pipeline.frameworkPruebasAutomatizadas.each { fwk ->

                println("Framework:"+fwk.framework)
                println("Framework:"+fwk.version)

            }
         
            } else {
                println("No se ha podido obtener la configuración de la cadena del servidor de configuración")
            }
    }

    return configuracion

}

//@NonCPS
def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

