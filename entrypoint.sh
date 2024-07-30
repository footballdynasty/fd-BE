#!/bin/sh -e

# first arg is `-f` or `--some-option`
if [ "${1#-}" != "$1" ] ; then
	set -- "$@"
fi

echo "####################"
echo "Jar name: $@"
echo "####################"
echo "Starting Java Application..."
java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar $@