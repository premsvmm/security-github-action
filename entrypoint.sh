#!/bin/sh -l
java -DDEFECT_DOJO_BASE_URL=${DEFECT_DOJO_BASE_URL} -DUPLOAD_FILE_PATH=${GITHUB_WORKSPACE}/${UPLOAD_FILE_PATH} -DPR_NUM=${PR_NUM} -Dgithub-token=${github-token} -jar /security-github-action-1.0-SNAPSHOT-jar-with-dependencies.jar