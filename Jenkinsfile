pipeline {
    agent any

    stages {
        stage('Git repository pulling') {
            steps {
                bat 'git pull'
            }
        }

        stage('Build JAR file') {
            steps {
                bat 'mvn clean install -Dmaven.test.skip=true'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build . -t lequochai26/tasks-management:1000'
            }
        }

        stage('Push Docker Image to hub') {
            steps {
                bat 'docker push lequochai26/tasks-management:1000'
            }
        }

        stage('Delete Kubernetes Pod') {
            steps {
                bat 'kubectl delete pod -l k8s-app=tasks-management-dev'
            }
        }
    }
}