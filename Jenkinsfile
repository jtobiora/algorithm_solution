pipeline {
    agent any
     tools {
        maven 'M3'
    }
    stages {
        stage('Git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/jtobiora/algorithm_solution.git'
            }
        }
        stage('Maven Build') {
            steps {
                 sh 'mvn package'
            }
        }
        stage("Deploy") {
            steps {
                echo 'Deployment completed'
            }
        }
    }
}
