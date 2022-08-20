FROM openjdk:17-alpine3.14
USER root
RUN localedef -i ru_RU -f UTF-8 ru_RU.UTF-8
RUN echo "LANG=\"ru_RU.UTF-8\"" > /etc/locale.conf
USER jboss
ENV LANG ru_RU.UTF-8
ENV LANGUAGE ru_RU.UTF-8
ENV LC_ALL ru_RU.UTF-8
COPY target/topjava26-project.jar topjava26-project.jar
EXPOSE 8080
ENTRYPOINT java ${JAVA_OPTS} -XX:MaxRAMPercentage=85.0 -Dfile.encoding=UTF-8 -Dconsole.encoding=UTF-8 -jar topjava26-project.jar
