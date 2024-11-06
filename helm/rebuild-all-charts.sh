#! /bin/bash

MICROSERVICES_CHARTS=("articles", "apigateway", "eurekaserver", "configserver", "review")

ENVIRONMENT_CHARTS=("default-env", "prod-env", "dev-env", "qa-env")

echo "Dependnencies build for articleinsight-common";
cd articleinsight-common
helm dependencies build .
cd ..	

echo ""

cd articleinsight-services;
echo "Dependnencies build for APIGATEWAY";
cd apigateway
helm dependencies build . 
cd ..   

echo ""

echo "Dependnencies build for ARTICLES";
cd articles
helm dependencies build . 
cd ..

echo ""

echo "Dependnencies build for EUREKASERVER";
cd eurekaserver
helm dependencies build . 
cd ..

echo ""

echo "Dependnencies build for CONFIGSERVER";
cd configserver
helm dependencies build . 
cd ..

echo ""

echo "Dependnencies build for REVIEW";
cd review
helm dependencies build . 
cd ..

echo ""

cd ../environments
echo "Dependnencies build for DEFAULT environment";
cd default-env
helm dependencies build . 
cd ..

echo ""

echo "Dependnencies build for DEV environment";
cd dev-env
helm dependencies build . 
cd ..

echo ""

echo "Dependnencies build for PROD environment";
cd prod-env
helm dependencies build . 
cd ..

echo ""

echo "Dependnencies build for QA environment";
cd qa-env
helm dependencies build . 
cd ..

cd ..
