#!/usr/bin/env bash

set -ex

export IMAGE_NAME="advanced-gilded-rose"

function stop_database() {
  docker stop $(docker ps -q --filter ancestor="${IMAGE_NAME}")
}

function remove_image() {
  docker rm $(docker ps -qa --filter ancestor="${IMAGE_NAME}")
  docker rmi ${IMAGE_NAME}
}

function main() {
  stop_database
  remove_image
}

main