def call() {
	 sh 'env > env.txt'
     sh 'cat env.txt'
}

