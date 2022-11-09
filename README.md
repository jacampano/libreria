## Instalación

Como paso previo para utilizar la librería, es necesario registrarla en la configuración de Jenkins. Para ello, como administrador, accedemos a "Administrar Jenkins -> Configurar el sistema" y rellenamos el siguiente formulario con la información necesaria:

* *Library Name:* Nombre que le daremos a la librería (desde el Pipeline se referenciará mediante @Library('Library Name'))
* *Default version:* Versión por defecto a utilizar
* *Propiedades del repositorio GIT:* Las propiedades necesarias para el repositorio GIT (URL/Credenciales/Rama etc)

![](resources/images/GlobalPipelineLibraries.png)

## Uso 

Una vez se ha realizado el registro en la configuración de Jenkins, para hacer uso de la librería solo será necesario referenciarla desde el Pipeline de la siguiente forma:

```
@Library('Library Name')
```

Por ejemplo, si a nuestra libreria la hemos llamado 'libreria', en nuestro pipeline deberemos referenciarla:

```
@@Library('libreria')
```

