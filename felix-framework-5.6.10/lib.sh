#!/bin/bash
rm -rf felix-cache
cp ../mvn-osgi-library-2016/utils/target/utils-1.0-SNAPSHOT.jar modules
cp ../mvn-osgi-library-2016/model/target/model-1.0-SNAPSHOT.jar modules
cp ../mvn-osgi-library-2016/integration/target/integration-1.0-SNAPSHOT.jar modules
cp ../mvn-osgi-library-2016/business/target/business-1.0-SNAPSHOT.jar modules
cp ../mvn-osgi-library-2016/richclient/target/richclient-1.0-SNAPSHOT.jar modules
java -jar bin/felix.jar
