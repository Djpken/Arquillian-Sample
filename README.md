Arquillian-Sample
=
This repository is example of Arquillian test framework,this contains two modules ,one Sql and Jboss eap

1. Arquillian-Sample-Service
    + A sample service about select Employee table
2. Arquillian-Sample-Test
    + To test Arquillian-Sample-Service Module
3. employee.sql
    + A sample Table for Arquillian-Sample-Service
4. jBoss-eap-7,4
    + Take the deployment this repository
    + Only Mariadb is supported
5. doc
    + Study about Arquillian

Setup-managed
-

1. Create a table by employee.sql in your database
2. Replace Screaming Case string your test environment ./jboss-eap-7.4/standalone/configuration/standalone-full.xml
   ```xml
      <datasources>
          <datasource jndi-name="java:/MariaDBDS" pool-name="MariaDBDS" enabled="true" use-java-context="true">
              <connection-url>jdbc:mariadb://YOUR_DATABASE/YOUR_TABLE</connection-url>
              <driver>mariadb</driver>
              <security>
                  <user-name>YOUR_NAME</user-name>
                  <password>YOUR_PASSWORD</password>
              </security>
          </datasource>
          <drivers>
              <driver name="mariadb" module="org.mariadb">
                  <driver-class>org.mariadb.jdbc.Driver</driver-class>
              </driver>
          </drivers>
      </datasources>
     ``` 
3. Replace Screaming Case string ./Arquillian-Sample-Test/src/test/resources/arquillian.xml
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <arquillian xmlns="http://jboss.org/schema/arquillian"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="
           http://jboss.org/schema/arquillian
           http://jboss.org/schema/arquillian/arquillian_1_0.xsd">
   
       <container qualifier="arquillian-wildfly-managed" default="true">
           <configuration>
               <property name="jbossHome">YOUR_JBOSS_ABSOLUTE_PATH</property>
               <property name="serverConfig">standalone-full.xml</property>
           </configuration>
       </container>
       <container qualifier="arquillian-wildfly-remote" >
           <configuration>
               <property name="managementAddress">127.0.0.1</property>
               <property name="managementPort">9990</property>
               <property name="username">root</property>
               <property name="password">P@ssw0rd</property>
           </configuration>
       </container>
   </arquillian>
   ```
4. Open ./Arquillian-Sample-Test/src/test/org/arquillian/test/EmployeeTest
5. Choose your Profiles **arquillian-wildfly-managed**
6. Run your JUnit test
7. Good luck with the test

Remote
-

1. Change container label arquillian-wildfly-remote attribute default="true"
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <arquillian xmlns="http://jboss.org/schema/arquillian"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="
           http://jboss.org/schema/arquillian
           http://jboss.org/schema/arquillian/arquillian_1_0.xsd">
   
       <container qualifier="arquillian-wildfly-managed" default="true">
           <configuration>
               <property name="jbossHome">YOUR_JBOSS_ABSOLUTE_PATH</property>
               <property name="serverConfig">standalone-full.xml</property>
           </configuration>
       </container>
       <container qualifier="arquillian-wildfly-remote" >
           <configuration>
               <property name="managementAddress">127.0.0.1</property>
               <property name="managementPort">9990</property>
               <property name="username">root</property>
               <property name="password">P@ssw0rd</property>
           </configuration>
       </container>
   </arquillian>
   ```
2. Change Maven Profiles **arquillian-wildfly-remote**
3. Run your JUnit test
4. Good luck with the test
