## 아티클작성 

curl -i -X POST http://localhost:8080/board \
-H "Content-Type: application/json" \
-d '{ 
	"category":"Spring",
	"boardType":"ARTICLE",
	"title":"Hello",
	"content":"Hello content",
	"createdAt":"2019-01-03'T'14:39:01",
	"createdBy":"kido"
}'

## 코멘트 작성 

curl -i -X POST http://localhost:8080/comment \
-H "Content-Type: application/json" \
-d '{ 
	"boardId":1,
	"comment":"하이 안녕하세요. ",
	"createdAt":"2019-01-03'T'14:39:01",
	"createdBy":"kido"
}'

## 코멘트의 코멘트 작성 

curl -i -X POST http://localhost:8080/comment \
-H "Content-Type: application/json" \
-d '{ 
	"boardId":1,
	"parentCommentId":1,
	"comment":"하이 안녕하세요. 답글입니다.",
	"createdAt":"2019-01-03'T'14:39:01",
	"createdBy":"kido"
}'

## 보드 아이디로 조회하기 

curl -i -X GET http://localhost:8080/board/1

## 보드 전체 목록 가져오기 

curl -i -X GET http://localhost:8080/board/all?page=0&size=10?sort=id,desc