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
	}
}

