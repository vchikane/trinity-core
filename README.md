# trinity-core
REST based modular setup using Spring MVC, Hibernate &amp; Angular JS.
The setup has following 2 divisions:
1) trinity-core
2) trinity-ui

The core part consist of rest based backend written in spring mvc using hibernate as ORM over MySql database.
The core part also incorporates security using in spring security.

The ui is web application powered by angularjs.
It uses ajax based calls to exchange json data to and from core module.

Both core and ui run on seperate contexts in a web server and ui is completely independent of core while 
rendering partial views.
