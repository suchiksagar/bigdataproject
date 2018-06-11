#IMP!! Please pass $DOCKER_HOST env variabel for this to work
FROM appropriate/curl
LABEL maintainer="sagarksuchi@gmail.com"
LABEL author="Winter Soldier"
LABEL description="Elastic Search Index Creator"
COPY bootstrap.sh /tmp/scripts/bootstrap.sh
ENV PATH="tmp/scripts:${PATH}"
ENTRYPOINT [ "/bin/sh", "/tmp/scripts/bootstrap.sh" ]