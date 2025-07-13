pipeline {
    agent any

    environment {
        IMAGE_NAME = 'quanghieu6652/order-consumer'
        REGISTRY = 'docker.io'
        DOCKER_CREDS = 'dockerhub-cred-id'
        K8S_NAMESPACE = 'default'
    }

    options {
        ansiColor('xterm')
        skipDefaultCheckout()
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME:${BUILD_NUMBER} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: "$DOCKER_CREDS", passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $IMAGE_NAME:${BUILD_NUMBER}
                        docker tag $IMAGE_NAME:${BUILD_NUMBER} $IMAGE_NAME:latest
                        docker push $IMAGE_NAME:latest
                    """
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    kubectl set image deployment/order-consumer order-consumer=$IMAGE_NAME:${BUILD_NUMBER} --namespace=$K8S_NAMESPACE || \
                    kubectl apply -f k8s/order-consumer.yaml
                """
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
