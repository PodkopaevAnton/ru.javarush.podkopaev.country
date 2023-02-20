# ru.javarush.podkopaev.country
1) Run MySQL server as docker container:
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8 
2) Expand dump. In Workbench, create a new database connection, port (3306), username (root by default) and password (root).
3) In the Workbench, do Data Import / Restore and select Import from Self Contained File. As a file, indicate where you downloaded the dump.
you will have a world schema with three tables
4) Run Redis server as a docker container:
docker run -d --name redis -p 6379:6379 redis:latest
5) Set your username and password in hibernate.properties
6) Run Application
7) The console will show the difference in getting data by id using radis and directly through MySQL
