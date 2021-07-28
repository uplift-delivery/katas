#!/usr/bin/env bash

set -ex

export IMAGE_NAME="advanced-gilded-rose"

function build_image() {
  docker build -t "${IMAGE_NAME}" .
}

function start_database() {
  docker run -dp 5432:5432 "${IMAGE_NAME}"
}

function main() {
  build_image
  start_database
}

main