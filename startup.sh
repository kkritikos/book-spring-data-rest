docker network create book-net
docker run -d --name mysql --network book-net --network-alias mysql -v mysql:/var/lib/mysql -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=books -e MYSQL_USER=book -e MYSQL_PASSWORD=book!23$ mysql:latest
sleep 40
docker run -d --name book --network book-net -p 8080:8080 -e MYSQL_NAME=mysql -e MYSQL_USER=book -e MYSQL_PWD=book!23$ book:0.1