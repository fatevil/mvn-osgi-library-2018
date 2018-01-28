#!/bin/bash
cd /home/marek/skola/apache_felix/mvn-osgi-library-2016/
mvn install
cd ..
cd felix-framework-5.6.10
rm -rf /home/marek/skola/apache_felix/felix-framework-5.6.10/felix-cache
cp /home/marek/skola/apache_felix/mvn-osgi-library-2016/utils/target/utils-1.0-SNAPSHOT.jar /home/marek/skola/apache_felix/felix-framework-5.6.10/modules
cp /home/marek/skola/apache_felix/mvn-osgi-library-2016/model/target/model-1.0-SNAPSHOT.jar /home/marek/skola/apache_felix/felix-framework-5.6.10/modules
cp /home/marek/skola/apache_felix/mvn-osgi-library-2016/integration/target/integration-1.0-SNAPSHOT.jar /home/marek/skola/apache_felix/felix-framework-5.6.10/modules
cp /home/marek/skola/apache_felix/mvn-osgi-library-2016/business/target/business-1.0-SNAPSHOT.jar /home/marek/skola/apache_felix/felix-framework-5.6.10/modules
cp /home/marek/skola/apache_felix/mvn-osgi-library-2016/richclient/target/richclient-1.0-SNAPSHOT.jar /home/marek/skola/apache_felix/felix-framework-5.6.10/modules
java -jar /home/marek/skola/apache_felix/felix-framework-5.6.10/bin/felix.jar
