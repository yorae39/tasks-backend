pipeline {
	agent any
	stages {
		stage ('Build Backend') {
			steps {
				sh 'mvn clean package -DskipTestes'
			}
		}
		stage ('Unit Tests') {
			steps {
				sh 'mvn test'
			}
		}
		stage ('Sonar Analysis') {
			environment{
				scannerHome = tool 'SONAR_SCANNER'
			}
			steps {
				withSonarQubeEnv('SONAR_LOCAL'){
					sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=9b68e7a2726a388fd3d427386920f2e584849486 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java**"
				}
			}	
		}
		stage ('Quality Gate') {
			steps {
				sleep(5)
				timeout(time: 1, unit: 'MINUTES') {
					waitForQualityGate abortPipeline: true
				}
			}
		}
		stage ('Deploy Backend') {
			steps {
				deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
			}
		}
		stage ('API Test') {
			steps {
				dir ('api-test') {
					git credentialsId: 'github_login', url: 'https://github.com/yorae39/tasks-api-test'
				}	
			}
		}
		stage ('Deploy Frontend') {
			steps {
				dir ('frontend') {
					git credentialsId: 'github_login', url: 'https://github.com/yorae39/tasks-frontend'
					sh 'mvn clean package'
					deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
				}	
			}
		}
		stage ('Deploy Prod') {
			steps {
				sh 'docker-compose build'
				sh 'docker-compose up -d'
			}
		}
		stage ('Health Check') {
			steps {
				dir ('health-check') {
					sleep(5)
					git credentialsId: 'github_login', url: 'https://github.com/yorae39/health-check'
					sh 'mvn test'
				}	
			}
		}
	}
	post {
		always {
			junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'

		}
	}
	
}

