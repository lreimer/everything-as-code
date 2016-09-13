# -*- mode: ruby -*-
# vi: set ft=ruby :
require 'yaml'

VAGRANTFILE_API_VERSION = "2"

$setup = <<SCRIPT
    sudo apt-get -y update
    sudo locale-gen en_US.UTF-8
    sudo update-locale LANG=en_US.UTF-8

    sudo apt-get -y install ansible
    sudo apt-get -y install sshpass
    sudo apt-get -y install vim

    sudo apt-get -y install git
    sudo apt-get -y install python-pip
    sudo pip install paramiko PyYAML jinja2 httplib2

    git clone git://github.com/ansible/ansible.git
    cd ansible
    git checkout tags/v2.1.1.0-1
    git submodule update --init lib/ansible/modules/core
    git submodule update --init lib/ansible/modules/extras
    cd ..

    chown -R vagrant:vagrant ansible/

    echo 'export ANSIBLE_HOST_KEY_CHECKING=False' >> /home/vagrant/.bashrc
    echo 'source ansible/hacking/env-setup' >> /home/vagrant/.bashrc
SCRIPT

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

    config.vm.box = "ubuntu/trusty32"
    config.vm.box_check_update = false

    config.vm.network "forwarded_port", guest: 18080, host: 18080
    config.vm.network "public_network"

    config.ssh.port = 2222
    config.ssh.forward_agent = true

    config.vm.synced_folder "src/ansible", "/home/vagrant/provisioning", :mount_options => ["dmode=755", "fmode=644"]

    settings = YAML.load_file 'src/vagrant/vagrant.yml'

    config.vm.provider "virtualbox" do |vb|
        vb.name = settings['vm']['name']

        # No VirtualBox GUI when booting the machine
        vb.gui = false

        # Customize the amount of memory on the VM:
        vb.memory = "512"

        # vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
        # vb.customize ["modifyvm", :id, "--natdnsproxy1", "on"]
    end

    config.vm.provision "file" do |f|
        f.source = "src/vagrant/.ansible.cfg"
        f.destination = ".ansible.cfg"
    end

    config.vm.provision "shell", inline: $setup

end
