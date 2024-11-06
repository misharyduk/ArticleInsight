pipeline {
    agent any
    tools{
        maven 'maven_3_9_9'
    }
    stages{
        // stage('Build Maven Projects'){
        //     steps{
        //         checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/misharyduk/ArticleInsight.git']])
        //         dir("apigateway"){
        //             sh 'mvn clean package -DskipTests'
        //         }
        //         dir("articles"){
        //             sh 'mvn clean package -DskipTests'
        //         }
        //         dir("eurekaserver"){
        //             sh 'mvn clean package -DskipTests'
        //         }
        //         dir("configserver"){
        //             sh 'mvn clean package -DskipTests'
        //         } 
        //         dir("review"){
        //             sh 'mvn clean package -DskipTests'
        //         }
        //     }
        // }
        // stage('Build Docker Images'){
        //     steps{
        //         dir("apigateway"){
        //             script{
        //                 sh 'docker build -t mykhailorudyk/apigateway .'
        //             }
        //         }
        //         dir("articles"){
        //             script{
        //                 sh 'docker build -t mykhailorudyk/articles .'
        //             }
        //         }
        //         dir("eurekaserver"){
        //             script{
        //                 sh 'docker build -t mykhailorudyk/eurekaserver .'
        //             }
        //         }
        //         dir("configserver"){
        //             script{
        //                 sh 'docker build -t mykhailorudyk/configserver .'
        //             }
        //         } 
        //         dir("review"){
        //             script{
        //                 sh 'docker build -t mykhailorudyk/review .'
        //             }
        //         }
        //     }
        // }
        // stage('Push Docker Images'){
        //     steps{
        //         script{
        //             withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
        //                 sh 'docker login -u mykhailorudyk -p ${dockerhubpwd}'
        //             }
        //             sh 'docker push mykhailorudyk/apigateway'
        //             sh 'docker push mykhailorudyk/articles'
        //             sh 'docker push mykhailorudyk/eurekaserver'
        //             sh 'docker push mykhailorudyk/configserver'
        //             sh 'docker push mykhailorudyk/review'
        //         }
        //     }   
        // }
        stage('Install Helm charts into Kubernetes cluster'){
            steps{
                script{
                    sh 'helm upgrade articleinsight-postgres --install helm/postgresql'
                    sh 'helm upgrade articleinsight-rabbitmq --install helm/rabbitmq'
                    sh 'helm upgrade loki --install helm/grafana-loki'
                    sh 'helm upgrade prometheus --install helm/kube-prometheus'
                    sleep time: 40, unit: 'SECONDS'
                    sh 'helm upgrade grafana --install helm/grafana'
                    sleep time: 30, unit: 'SECONDS'
                    sh 'helm upgrade articleinsight --install helm/environments/default-env'
                    sleep time: 120, unit: 'SECONDS'
                    sh 'kubectl port-forward $(kubectl get pods | grep -oh "apigateway-deployment-\\w*-\\w*") 8072:8072 &'
                }
            }
        }
    }
}