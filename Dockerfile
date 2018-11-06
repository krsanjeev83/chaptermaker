# A basic apache server. To use either add or bind mount content under /var/www
FROM ubuntu:12.04

MAINTAINER Sanjeev Kumar

RUN apt-get update && apt-get install -y apache2 && apt-get clean && rm -rf /var/lib/apt/lists/*1
