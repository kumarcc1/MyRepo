def test = "xy" --global parameters 
def env_nonprod = 'uat'
def env_non = 'both'
pipeline {
    agent any
    parameters{
        string(name:'env_nonprod',defaultValue:'uat',description:'name of pet')
        string(name:'env_non',defaultValue:'both',description:'name of pet')
    }
    triggers{
        // cron('* * * * *')
        pollSCM('* * * * *')
    }
    // options{
        // retry(3)
        // buildDiscarder(logRotator(numToKeepStr: '10', daysToKeepStr: '30'))
        // disableConcurrentBuilds()
        // timeout(time:20,unit:'SECONDS')
        // timestamps()
    }
    stages {
        stage('test'){
            // when {
            //         expression { env_nonprod == 'uat' }
            //}
            // when {
            //     anyOf {
            //         expression { env_nonprod == 'uat' }
            //         expression { env_non == 'both' }
            //     }
            // }
            when {
                allOf {
                    expression { env_nonprod == 'uat' }
                    expression { env_non == 'both' }
                }
            }
            steps{
                cleanWs()
                sh """
                echo "helloWorld" > a
                echo "helloWorld" > b
                echo "helloWorld" > c
                echo "helloWorld" > d
                """
                sh "ls -lart && pwd"
                echo 'uat operations'
                print cred
            }
        }
        
        stage('Hello') {
            // when {
            //         expression {env_nonprod == 'both' }
            //     }
            // when {
            //     anyOf {
            //         expression { env_nonprod == 'qa' }
            //         expression {env_nonprod == 'both' }
            //     }
            // }
            when {
                allOf {
                    expression { env_nonprod == 'qa' }
                    expression { env_non == 'both' }
                }
            }
            steps {
                // ÷cleanWs()
                echo 'Hello World'
                // sh 'mkdir $folder'
                sh 'ls -lart'
                sh 'pwd'
                print BUILD_URL
                // sh "echo tool name is $tool"
                // print pet
                // retry(3){
                   echo 'qa oprtions'
                    // uytsleep 60
                // }
                // build('projectA/test')
                // build('projectB/test')
            }
        }
    }
    
}
