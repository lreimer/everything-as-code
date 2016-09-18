#!/bin/sh

curl -SL https://github.com/dcos/dcos-vagrant/archive/v0.8.0.tar.gz | tar xz

cd dcos-vagrant-0.8.0
mv VagrantConfig.yaml.example VagrantConfig.yaml
cp etc/config-1.7.yaml etc/config.yaml

curl -O https://downloads.dcos.io/dcos/stable/dcos_generate_config.sh

vagrant up m1 a1 boot