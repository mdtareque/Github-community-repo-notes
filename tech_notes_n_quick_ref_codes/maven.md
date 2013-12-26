local artifact in local m2 repo

    mvn install
    
deploy artifact to remote maven repo

    mvn deploy
    
distribution management tag should be correct in pom.xml

	<distributionManagement>
		<repository>
			<uniqueVersion>false</uniqueVersion>
			<id>repo-id</id>
			<url>svn:http://repo-url/</url>
		</repository>
	</distributionManagement>
	
Gives complete hierarchy and analysis of dependant jars
	
    mvn dependency:tree
    mvn dependency:analyze (Good to see unused dependencies)
