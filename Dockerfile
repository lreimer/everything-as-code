FROM qaware-oss-docker-registry.bintray.io/base/debian8-jre8
MAINTAINER QAware GmbH <qaware-oss@qaware.de>

RUN mkdir -p /app
ADD build/distributions/everything-as-code-1.0.0.tar /app

RUN cd /app/everything-as-code; chmod 755 bin/everything-as-code

ENV PORT=18080

EXPOSE 18080
CMD ./bin/everything-as-code