#!/bin/bash

echo ""
echo "$(tput setaf 2)Downloading Kafka $(tput sgr 0)"
echo ""

wget http://public-repo-1.hortonworks.com/HDP-LABS/Projects/kafka/0.8.1/centos6/kafka-0.8.1.2.1.4.0-632.el6.noarch.rpm

echo ""
echo "$(tput setaf 2)Installing Kafka RPM $(tput sgr 0)"
echo ""

rpm -ivh kafka-0.8.1.2.1.4.0-632.el6.noarch.rpm
rm -rf kafka-0.8.1.2.1.4.0-632.el6.noarch.rpm

echo ""
echo "$(tput setaf 2)Finished installing Kafka $(tput sgr 0)"
echo ""