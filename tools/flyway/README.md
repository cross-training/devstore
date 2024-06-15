# Flyway

## Install

```bash
sudo ansible-playbook -i ../ansible/inventory.ini install_flyway.yml --extra-vars "hosts=local flyway_version=10.13.0"
```

## Local Run

```bash
sudo flyway migrate --configFiles=./local.flyway.conf
```
