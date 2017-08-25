#!/bin/sh

export KUBERNETES_PROVIDER=vagrant
export KUBE_ENABLE_CLUSTER_MONITORING=none

curl -sS https://get.k8s.io | bash
