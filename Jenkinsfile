pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }
    stages {
        stage('Build') {
            steps {
                dir('electrolink') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                dir('electrolink') {
                    sh 'mvn test -DskipTest'
                }
            }
        }

        stage('Package') {
            steps {
                dir('electrolink') {
                    sh 'mvn package -DskipTests'
                }
            }
        }

        stage('Archive') {
            steps {
                dir('electrolink') {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        success {
            echo 'Build réussi '
        }
        failure {
            echo 'Build échoué '
        }
    }
}