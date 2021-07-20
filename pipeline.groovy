pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/pradiptaAccolite/jgsu-spring-petclinic'
            }
        }
        stage('Build') {
            steps {
                bat "mvnw clean package"
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
