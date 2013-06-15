java $JAVA_OPTS -Dshoppinpal.router.logging.enabled="true" \
                -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
