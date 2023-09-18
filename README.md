# WeatherForecast

## Docker

#### List docker contexts

    docker context ls

#### Create minikube docker context
    
    1. execute command to get minikube context values
    minikube -p minikube docker-env

    2. create docker context
    docker context create minikube --description "Minikube Docker Context" --docker "host=tcp://192.168.49.2:2376,cert=<home path>/.minikube/certs/cert.pem,ca=<home path>/.minikube/certs/ca.pem,key=<home path>/.minikube/certs/key.pem"

#### Swich between docker contexts

    docker context use <context name>

#### Switch docker context do minikube's context without definition of new docker context

    eval $(minikube -p docker-env)

## Minikube

#### Minikube start

    minikube start

#### Run minikube dashboard

    minikube dashboard

## DAPR

#### Apply DAPR CRDs to k8s cluster

    kubectl apply -f K8s/helm/crds/components.yaml
    kubectl apply -f K8s/helm/crds/configuration.yaml
    kubectl apply -f K8s/helm/crds/httpendpoints.yaml
    kubectl apply -f K8s/helm/crds/resiliency.yaml
    kubectl apply -f K8s/helm/crds/subscription.yaml

#### Initialization of DAPR in k8s

    dapr init -k

#### DAPR dashboard

    dapr dashboard -k -p 9080