pipeline {
    agent any

    tools {
        jdk 'JDK11'
        gradle 'Gradle7.3'
    }

    options {
        timeout(time: 20, unit: 'MINUTES')
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '15'))
    }

    stages {
        stage('Clean WorkSpace'){
            steps{
                deleteDir()
                echo "${env.GIT_BRANCH} - ${currentBuild.durationString} - ${env.JOB_NAME} - ${env.BUILD_NUMBER} - ${env.BUILD_STATUS} - Started!"

            }
        }

        stage('Git Clone Project') {
            steps{
                //git branch: 'develop', url: 'https://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@github.com/Hacybeyker/Mapi.git'
                git branch: 'develop', credentialsId: 'GithubCredentials', url: 'https://github.com/Hacybeyker/Mapi'
            }
        }

        stage ("Compile and Generate APK"){
            steps{
                sh 'chmod +x ./gradlew'
				sh 'git submodule init'
				sh 'git submodule update'
                sh './gradlew maps:clean'
                sh './gradlew maps:lintGoogleDebug'
                sh './gradlew maps:lintHuaweiDebug'
                sh './gradlew maps:ktlintFormat'
                sh './gradlew maps:ktlintCheck'
                sh './gradlew maps:detekt'
                sh './gradlew maps:assembleGoogleDebug'
                sh './gradlew maps:assembleHuaweiDebug'
                sh './gradlew maps:assembleGoogleRelease'
                sh './gradlew maps:assembleHuaweiRelease'
                sh './gradlew maps:testGoogleDebugUnitTest'
                sh './gradlew maps:testHuaweiDebugUnitTest'
                sh './gradlew maps:jacocoGoogleTestReport'
                sh './gradlew maps:jacocoGoogleTestCoverageVerification'
                sh './gradlew maps:jacocoHuaweiTestReport'
                sh './gradlew maps:jacocoHuaweiTestCoverageVerification'
            }
        }
		stage('Sonarqube') {
			environment {
				scannerHome = tool 'SonarQubeScanner4.6'
			}
			steps {
				withSonarQubeEnv('SonarQube') {
					sh 'chmod +x ./gradlew'
					sh './gradlew app:sonarqube -Dsonar.login="${SONAR_TOKEN}" -Dsonar.host.url="http://vmi734657.contaboserver.net:9000/"'
				}
			}
		}
	}

    post{
        success {
            echo 'success!'
        }

        failure {
            echo 'failure!'
        }

        always {
            echo 'always!'
        }
    }
}