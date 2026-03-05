pipeline {
    agent any

    // Poll SCM every 2 minutes for new commits
    triggers {
        pollSCM('H/2 * * * *')
    }

    environment {
        IMAGE_NAME   = 'scientific-calculator'
        IMAGE_TAG    = 'latest'
    }

    stages {

        // ── Stage 1: Checkout ─────────────────────────────────────────────────
        stage('Checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[
                        credentialsId: 'github-credentials',
                        url: 'https://github.com/RushilKhatri/Scientific_calculator.git'
                    ]]
                )
            }
        }

        // ── Stage 2: Build ────────────────────────────────────────────────────
        stage('Build') {
            steps {
                sh 'mvn clean compile -B'
            }
        }

        // ── Stage 3: Test ─────────────────────────────────────────────────────
        stage('Test') {
            steps {
                sh 'mvn test -B'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        // ── Stage 4: Package ──────────────────────────────────────────────────
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests -B'
            }
        }

        // ── Stage 5: Docker Build ─────────────────────────────────────────────
        stage('Docker Build') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                    )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker build -t scientific-calculator:latest .
                    '''
                }
            }
        }

        // ── Stage 6: Docker Push ──────────────────────────────────────────────
        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                    )]) {
                    sh '''
                        docker tag scientific-calculator:latest $DOCKER_USER/scientific-calculator:latest
                        docker push $DOCKER_USER/scientific-calculator:latest
                        docker logout
                    '''
                }
            }
        }
        // ── Stage 7: Ansible Deploy ───────────────────────────────────────────
        stage('Ansible Deploy') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
            sh '''
                cd ansible
                ansible-playbook -i inventory.ini playbook.yml \
                    --extra-vars "dockerhub_user=$DOCKER_USER" \
                    -v
            '''
                  }
            }
        }
    }

    
    // ── Post Actions: Email Notifications ─────────────────────────────────────
    post {
        success {
            emailext(
                subject: "✅ BUILD SUCCESS: ${env.JOB_NAME} [#${env.BUILD_NUMBER}]",
                body: '''
                    <p>The build completed <b>successfully</b>.</p>
                    <ul>
                        <li><b>Job:</b> $JOB_NAME</li>
                        <li><b>Build #:</b> $BUILD_NUMBER</li>
                        <li><b>Status:</b> SUCCESS</li>
                        <li><b>Build URL:</b> <a href="$BUILD_URL">$BUILD_URL</a></li>
                    </ul>
                ''',
                mimeType: 'text/html',
                attachLog: true,
                to: 'rushilkhatri1836@gmail.com'
            )
        }
        failure {
            emailext(
                subject: "❌ BUILD FAILED: ${env.JOB_NAME} [#${env.BUILD_NUMBER}]",
                body: '''
                    <p>The build has <b>failed</b>. Please check the console log attached below.</p>
                    <ul>
                        <li><b>Job:</b> $JOB_NAME</li>
                        <li><b>Build #:</b> $BUILD_NUMBER</li>
                        <li><b>Status:</b> FAILURE</li>
                        <li><b>Build URL:</b> <a href="$BUILD_URL">$BUILD_URL</a></li>
                    </ul>
                ''',
                mimeType: 'text/html',
                attachLog: true,
                to: 'rushilkhatri1836@gmail.com'
            )
        }
    }  
}
