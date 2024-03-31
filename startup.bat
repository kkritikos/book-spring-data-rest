docker network create book-net
docker run -d --name mysql --network book-net --network-alias mysql -v mysql:/var/lib/mysql -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_USER=book -e MYSQL_PASSWORD=book1234 -e MYSQL_DATABASE=books mysql:latest
timeout /t 40 >nul
docker run -d --name book --network book-net -p 8080:8080 -e MYSQL_NAME=mysql -e MYSQL_USER=book -e MYSQL_PWD=book1234 book:0.1