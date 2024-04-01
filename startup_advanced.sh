docker network create book-net
docker run -d --name mysql --network book-net --network-alias mysql -v mysql:/var/lib/mysql -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=books -e MYSQL_USER=book -e MYSQL_PASSWORD=book1234 mysql:latest

while ! docker exec -it mysql mysql -ubook -pbook1234 -e "SELECT 1" >/dev/null 2>&1; do
    sleep 1
done

docker run -d --name book --network book-net -p 8080:8080 -e MYSQL_NAME=mysql -e MYSQL_USER=book -e MYSQL_PWD=book1234 book:0.1