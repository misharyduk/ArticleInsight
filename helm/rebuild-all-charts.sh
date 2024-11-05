#! /bin/bash

COMMON_CHART="articleinsight-common"

MICROSERVICES_CHARTS=("articles", "apigateway", "eurekaserver", "configserver", "review")

ENVIRONMENT_CHARTS=("default-env", "prod-env", "dev-env", "qa-env")

if [ -d "$COMMON_CHART" ]; then
	echo "Dependnencies build for $COMMON_CHART";

	cd "$COMMON_CHART" || { echo "No such directory"; exit 1; }

	helm dependencies build .

	cd ..	
fi

for chart in "${MICROSERVICES_CHARTS[@]}"; do
	if [ -d "articleinsight-services/$chart" ]; then
		echo "Dependencies build for $chart"

		cd "articleinsight-services/$chart" || { echo "No such directory"; exit 1; }

		helm dependencies build .

		cd ..

	fi

done

cd ..
