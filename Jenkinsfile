pipeline {
    agent any
/*    tools {
        gradle "GRADLE_LATEST"
    }*/
    stages {
        stage('Build Gradle'){
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/dontturnaway/myapp.git']])
                sh './gradlew clean build'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build . -t deem0ne/myapp'
                }
            }
        }
        stage('Push image to Dockerhub'){
            steps {
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u deem0ne -p ${dockerhubpwd}'
                    sh 'docker push deem0ne/myapp'
                    }
                }
            }
        }
    }
}