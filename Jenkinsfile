pipeline {
    agent {
        label 'maven'
    }
    stages {
        stage("parallel") {
            parallel{
                stage('ProjectNG'){
                    steps {
                        sh 'touch file{1..10}'
                        sh 'ls -lart && pwd'
                    }
                }
                stage('ProjectONGC'){
                    steps{
                        sh 'touch file{10..20}'
                        sh 'ls -lart && pwd'
                    }
                }
            }
        }
        stage('ProjectONGC'){
            steps{
                sh 'mkdir Test'
                sh 'hostname'
                sleep 15
                cleanWs()
            }
        }
    }
}
