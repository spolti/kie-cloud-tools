Overview
--------

This is a vagrant/ansible based project to prepare the environment to build RHPAM / RHDM images.

Pre-requisites
--------------

Vagrant - https://www.vagrantup.com

https://www.vagrantup.com/docs/installation

VirtualBox - https://www.virtualbox.org

https://www.virtualbox.org/manual/UserManual.html


Usage
--------------

Connect your workstation to Red Hat VPN. After that clone the repository:

```bash
https://github.com/kiegroup/kie-cloud-tools.git
cd kie-cloud-tools/rhba-dev-env
```

Update the config.yml file to suit your needs. The IP of the virtual machine must be in the network range of your workstation.

```yaml
---
- name: workstation
  ip: 192.168.0.40
  memory: 8192
  cpu: 4
  disk: workstation.vdi
  disk_size: 204800
  alias: "workstation"
```
 
 Customization of repository, variables etc., must be done in the following file:

```bash
kie-cloud-tools/rhba-dev-env/vagrant/provisioning/group_vars/all/vars_file.yml
 ```

Please, download the vars_file.yml which is attached in the following document: 

"1 - Starting the virtual machine" - https://mojo.redhat.com/docs/DOC-1203582

After following document guidelines start the virtual machine:

```bash
vagrant up 
```

Choose the network interface where the VPN is being used:

```bash
==> workstation: Available bridged network interfaces:
1) wlp4s0
2) enp0s31f6
3) docker0
4) virbr0
```

 After that ansible playbook will start and all configuration will be performed automatically.
 
 If all tasks are executed successfully you will see something like:
 
```bash
 PLAY RECAP *********************************************************************
workstation                : ok=29   changed=3    unreachable=0    failed=0    skipped=1    rescued=0    ignored=0   

Friday 26 July 2019  01:59:18 +0000 (0:00:08.785)       0:12:35.106 *********** 
=============================================================================== 
setup_env : upgrade all packages -------------------------------------- 427.18s
setup_env : install the latest version of docker ----------------------- 97.67s
setup_env : install the latest version of cekit packages --------------- 75.63s
setup_env : clone repo https://github.com/jboss-container-images/rhpam-7-openshift-image.git -- 53.78s
setup_env : install useful packages ------------------------------------ 27.99s
setup_env : clone repo https://github.com/jboss-container-images/rhdm-7-openshift-image.git -- 20.85s
setup_env : install the latest version of ansible ---------------------- 11.04s
setup_env : install useful packages for brew --------------------------- 10.41s
setup_env : clone repo https://gitlab.cee.redhat.com/mmagnani/rhba-dev.git --- 8.79s
setup_env : install redhat-internal-cert -------------------------------- 5.39s
setup_env : start docker service ---------------------------------------- 4.67s
Gathering Facts --------------------------------------------------------- 1.57s
setup_env : docker storage setup ---------------------------------------- 1.34s
setup_env : enable @cekit/cekit ----------------------------------------- 1.26s
setup_env : enable docker service --------------------------------------- 0.84s
setup_env : adding user vagrant to group docker ------------------------- 0.77s
setup_env : docker storage configurations ------------------------------- 0.71s
setup_env : ensure group "docker" exists -------------------------------- 0.66s
setup_env : create kerberos configuration ------------------------------- 0.61s
setup_env : copy rpm redhat-internal-cert ------------------------------- 0.55s
```

Inside the Virtual Machine
--------------

After everything is set up the first step is to restart the virtual machine to ensure that all configurations are consistent.

```bash
vagrant ssh
sudo reboot
```
Log in again and go to the cloned repository at the virtual machine startup:

```bash
vagrant ssh
cd /home/vagrant/workspace/kie-cloud-tools/rhba-dev-env/ansible
```
Run ssh-copy-id for localhost (use the password "vagrant")

```bash
 ssh-copy-id localhost
/usr/bin/ssh-copy-id: INFO: Source of key(s) to be installed: "/home/vagrant/.ssh/id_rsa.pub"
The authenticity of host 'localhost (::1)' can't be established.
ECDSA key fingerprint is SHA256:NGzRYlpK+S1jsn5CENT6V+obbav7vzAOLcOYrWcw3hs.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
/usr/bin/ssh-copy-id: INFO: attempting to log in with the new key(s), to filter out any that are already installed
/usr/bin/ssh-copy-id: INFO: 1 key(s) remain to be installed -- if you are prompted now it is to install the new keys
vagrant@localhost's password: 

Number of key(s) added: 1

Now try logging into the machine, with:   "ssh 'localhost'"
and check to make sure that only the key(s) you wanted were added.
```
New customizations can be made in:

```bash
/home/vagrant/workspace/kie-cloud-tools/rhba-dev-env/ansible/group_vars/all/vars_file.yml
 ```
Or you can change the same variables on the command line.


Check the document again: "2 - Building the images" - https://mojo.redhat.com/docs/DOC-1203582

Go to /home/vagrant/workspace/kie-cloud-tools/rhba-dev-env/ansible and run: 

Example to build RHPAM images

```bash
ansible-playbook -i hosts play_build_images.yml -e 'build_overrides_product=rhpam' -e 'kerberos_password=pass' -e 'build_overrides_nightly=20190724' -e 'build_product=rhpam-businesscentral' -e 'git_branch_target=remotes/origin/7.4.x'
```
Example to build RHDM images

```bash
ansible-playbook -i hosts play_build_images.yml -e 'build_overrides_product=rhdm' -e 'kerberos_password=pass' -e 'build_overrides_nightly=20190724' -e 'build_product=rhdm-kieserver' -e 'git_branch_target=remotes/origin/7.4.x'
```

(do not forget to update the variable kerberos_password).

Open another session on the terminal and run ssh to see the new images available:

```bash
vagrant ssh
watch docker images
```

Build Parameters
--------------

"build_overrides_version" - Version being built.

"build_overrides_nightly" -  The date of the nightly build to access.

"build_overrides_product" - Valid choices are: rhpam or rhdm

"build_product" - Parameter for which an image is being built. Valid choices are: rhpam-all rhpam-businesscentral, rhpam-businesscentral-monitoring, rhpam-businesscentral-indexing, rhpam-controller, rhpam-kieserver, rhdm-all, rhpam-smartrouter rhdm-decisioncentral, rhdm-decisioncentral-indexing, rhdm-controller, rhdm-kieserver, rhdm-optaweb-employee-rostering

"git_branch_target" - Branch to be used for image build. (Default is remotes/origin/7.4.x)

"kerberos_username" - Your kerberos username

"kerberos_password" - Your kerberos password