# How to run this project
 1. To run it online via a PaaS provider such as Heroku:
 2. To run it on your local machine:
   - Clone it locally
     - mkdir ~/dev
     - cd ~/dev
     - git clone <url>
   - Make sure that you have the [foreman](https://github.com/ddollar/foreman) gem installed:
     - It requires RubyGems version >= 1.3.6, you can update your version of rubygems via:
         - sudo gem update --system
     - To install foreman, run:
         - sudo gem install foreman
   - With foreman present, simply use:
     - create a file named **.env** inside the **~/dev/cloud-pos-router/** folder and configure the following environment variables:
         - cut & paste

                    CLOUDAMQP_URL=amqp://username:password@rabbitmq.host.com/instanceName
             - You can get your own free RabbitMQ instance with a URL that's ready-to-go at [CloudAMQP](http://www.cloudamqp.com/plans.html)
         - cut & paste into .env file

                    JAVA_OPTS=-Xmx384m -Xss512k -XX:+UseCompressedOops
             - If you want to remote debug this app while its running, you can use:
                 - cut & paste into .env file

                            JAVA_OPTS=-Xmx384m -Xss512k -XX:+UseCompressedOops -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n
                     - You may replace port 8000 with another port # if its already in use.
         - cut & paste into .env file

                    JAVA_TOOL_OPTIONS=-Djava.net.preferIPv4Stack=true
         - cut & paste into .env file

                    MAVEN_OPTS=-Xmx384m -Xss512k -XX:+UseCompressedOops
         - cut & paste into .env file

                    PATH=/app/.jdk/bin:/usr/local/bin:/usr/bin:/bin

     - then to launch the server, simply run:
         - cut & paste onto cmd line terminal

                    foreman start
 3. Added support within the products call for filters.
   - Use the following URL (use a URL encoding tool for the filter text):
     - http://localhost:8080/cloud-router/api/products?filter=code%20CONTAINS%5Bcd%5D%20%22BACKPACK%22%20OR%20description%20CONTAINS%5Bcd%5D%20%22BACKPACK%22%20

 4. New utility method for getting a product via the UPC filter
   - http://localhost:8080/cloud-router/api/products/filterby/upc/0000000001

 5. Get product details by id
   - http://localhost:8080/cloud-router/api/products/2/

# Developing in Eclipse
1. You can enable direct Git access from within Eclipse if you follow these [instructions](http://stackoverflow.com/questions/7194877/how-make-eclipse-egit-recognize-existing-repository-information-after-update).

# How to create a brand new Maven/Eclipse project for the Jersey Framework
 1. Start Eclipse
 2. Right-Click in the Navigator pane
 3. New > Project > Maven Project
 4. Next
 5. Uncheck
    Use default Workspace location
 6. Set location to:
    ~/dev
 7. Next
 8. Catalog:
    All Catalogs
 9. Filter:
    webapp
 10. Select the one with the Artifact Id: maven-archetype-webapp
 11. Next
 12. Group Id: com.companyName
 13. Artifact Id: projectName
 14. Package: umbrella.project.name (optional - you can leave it empty)
 15. Finish
 16. Jersey 1.13 User Guide
   - Section [11.3.5. Servlet](http://jersey.java.net/nonav/documentation/latest/chapter_deps.html#d4e1712)
     - Deploying an application on a servlet container requires a deployment dependency with that container.
     - Using servlet: **com.sun.jersey.spi.container.servlet.ServletContainer** requires a dependency on the jersey-servlet module.
 17. Add dependencies on jersey-server and jersey-servlet
 18. Remove junit dependency as its not being used at this point in time.