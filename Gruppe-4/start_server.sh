#!/bin/bash

UNAME=$(uname)

if [ "$UNAME" == "Linux" ] ; then
	echo "Linux"
	echo "python -m SimpleHTTPServer 8000"
	python -m SimpleHTTPServer 8000
elif [ "$UNAME" == "Darwin" ] ; then
	echo "Mac"
	echo "python -m SimpleHTTPServer 8000"
	python -m SimpleHTTPServer 8000
elif [[ "$UNAME" == CYGWIN* || "$UNAME" == MINGW* ]] ; then
	echo "Windows"
	echo "python -m http.server 8000"
	python -m http.server 8000
else
	echo "Unknown OS"
fi
