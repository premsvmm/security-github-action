#!/bin/sh -l
echo "Print DoJo Base URL"
echo ${DEFECT_DOJO_BASE_URL}
echo "ENV"
env
echo "LS"
ls
echo "PWD"
pwd
echo "Running app"
java -DDEFECT_DOJO_BASE_URL=${DEFECT_DOJO_BASE_URL} -DUPLOAD_FILE_PATH=${UPLOAD_FILE_PATH} -jar /security-github-action-1.0-SNAPSHOT.jar