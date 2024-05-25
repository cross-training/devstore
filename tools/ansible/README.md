# Ansible

## Install

```bash
sudo apt update
sudo apt install ansible
ansible --version
```

## Inventory Ping

```bash
ansible -i inventory.ini all -m ping
```
