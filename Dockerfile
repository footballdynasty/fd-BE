FROM ubuntu:latest AS builder
ARG JAR_FILE


RUN mkdir -p /srv/fd/
COPY entrypoint.sh /srv/fd
RUN chmod +x /srv/fd/entrypoint.sh

COPY ${JAR_FILE} /srv/fd/footballdynasty.jar

FROM eclipse-temurin:21-jdk AS runner

COPY --from=builder /srv/fd /srv/fd

RUN useradd fd
RUN chown -R fd /srv/fd
USER fd

WORKDIR /srv/fd
EXPOSE 8080
CMD ["-jar","footballdynasty.jar"]
ENTRYPOINT ["./entrypoint.sh"]