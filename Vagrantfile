# -*- mode: ruby -*-
# vi: set ft=ruby :
require 'yaml'

VAGRANTFILE_API_VERSION = "2"

$setup = <<SCRIPT
    sudo apt-add-repository ppa:ansible/ansible
    sudo apt-get update

    sudo apt-get install -y software-properties-common
    sudo apt-get install -y ansible sshpass

    echo 'export ANSIBLE_HOST_KEY_CHECKING=False' >> /home/vagrant/.bashrc
SCRIPT

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

    config.vm.box = "ubuntu/trusty32"
    config.vm.box_check_update = false

    config.vbguest.auto_update = false
    config.vbguest.no_remote = true

    # config.vm.network "forwarded_port", guest: 18080, host: 18080

    config.ssh.port = 2222
    config.ssh.forward_agent = true

    config.vm.synced_folder "src/vagrant/cache/apt-archives", "/var/cache/apt/archives"
    config.vm.synced_folder "src/ansible", "/home/vagrant/provisioning", :mount_options => ["dmode=755", "fmode=644"]

    settings = YAML.load_file 'src/vagrant/vagrant.yml'

    config.vm.provider "virtualbox" do |vb|
        vb.name = settings['vm']['name']
        vb.gui = false
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
