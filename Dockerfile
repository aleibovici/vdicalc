FROM picoded/ubuntu-openjdk-8-jdk
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY vdicalc/target/dependency-jars /run/dependency-jars
ADD vdicalc/target/vdicalc-1.0-SNAPSHOT-ga.jar /run/vdicalc-1.0-SNAPSHOT-ga.jar

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

ENTRYPOINT java -jar /run/vdicalc-1.0-SNAPSHOT-ga.jar -D exec.mainClass="com.myvirtualcloud.vdicalc.vdicalc"