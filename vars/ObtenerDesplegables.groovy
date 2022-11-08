def call () {

   def listaDesplegables = 'find .  -name \"*.war\" -o -name \"*.ear\"'.execute()
   println("Lista Desplegables: "+listaDesplegables)

   listaDesplegables.in.eachLine { 
	desplegable -> println("Desplegable: "+desplegable) 
	}

    
}