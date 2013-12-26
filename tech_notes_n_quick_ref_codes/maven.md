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

<pre>
dependency:sources 	Downloads all project sources separate from IDE project creation. Execute from root of parent project, then have your sources sync'd up in IDE.
javadoc:javadoc 	Generate java doc files
javadoc:jar      	Generate java doc in a jar file
-Dmaven.test.skip=true          	Skip running tests
-Dmaven.test.failure.ignore=true 	Ignore test failure
eclipse:eclipse 	To generate Eclipse project descriptor after configuring the dependencies in pom.xml
help:effective-pom 	Shows the logical contents of a pom.xml, including contents inherited from the parent pom.xml, up to and including the Maven super POM.
help:effective-settings 	Shows the logical contents of a settings.xml, including contents of proxy, profile... 
</pre>
