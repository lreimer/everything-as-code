FROM qaware-oss-docker-registry.bintray.io/base/centos7-jre8:8u77
MAINTAINER M.-Leander Reimer <mario-leander.reimer@qaware.de>

RUN mkdir -p /app
ADD build/distributions/everything-as-code-1.2.1.tar /app

WORKDIR /app/everything-as-code-1.2.1
RUN chmod 755 bin/everything-as-code

ENV JAVA_HOME /usr/java/latest
ENV PATH /usr/java/latest/bin:$PATH

EXPOSE 18080

CMD ./bin/everything-as-code
