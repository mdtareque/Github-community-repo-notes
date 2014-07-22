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


guidelines and cmd to install missing dependancy in output of mvn

	$ mvn eclipse:eclipse
	2/4/14 3:21:23 PM IST: Build errors for projA; org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal on project erebus: Missing:
	----------
	1) com.mtk.test:projB:jar:1.3.2

	  Try downloading the file manually from the project website.

	  Then, install it using the command:
		  mvn install:install-file -DgroupId=com.mtk.test -DartifactId=projB -Dversion=1.3.2 -Dpackaging=jar -Dfile=/path/to/file

	  Alternatively, if you host your own repository you can deploy the file there:
		  mvn deploy:deploy-file -DgroupId=com.mtk.test -DartifactId=pyr_common_feed -Dversion=1.3.2. -Dpackaging=jar -Dfile=/path/to/file -Durl=[url] -DrepositoryId=[id]

	  Path to dependency:
		1) com.mtk.test:projA:1.4.2
		2) com.mtk.test:projB:1.3.2

	----------
	1 required artifact is missing.

	for artifact:
		com.mtk.test:projA:pom:1.4.2


Other useful commands

    > mvn eclipse:eclipse -DdownloadSources=true
    > mvn dependency:resolve -Dclassifier=sources     download sources
    > mvn dependency:resolve -Dclassifier=javadoc     download javadocs
    > mvn <goals> -rf :project4                       just build from project4 and onwards

