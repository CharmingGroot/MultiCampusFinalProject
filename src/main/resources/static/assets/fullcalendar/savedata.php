<?php
// MySQL 연결 정보
$servername = "localhost";
$username = "ts";
$password = "qwe123QWE!@#";
$encoded_password = urlencode($password);
$dbname = "pet_event";

// MySQL 연결
$conn = new mysqli($servername, $username, $password, $dbname);

// 연결 오류 확인
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// POST로 넘어온 데이터 처리
$data = json_decode(file_get_contents("php://input"));

// 이벤트 정보를 MySQL에 저장
foreach ($data as $event) {
    $title = $event->title;
    $start = $event->start;
    $end = $event->end;

    $sql = "INSERT INTO events (title, start, end)
            VALUES ('$title','$start', '$end')";

    if ($conn->query($sql) === FALSE) {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

// MySQL 연결 종료
$conn->close();




 //include "conndb.php";

//  $db_link = db_conn(); //데이터베이스 연결함수
//  mysqli_select_db($db_link,"ts");

//  $dataArr= json_decode($_POST["alldata"]); // alldata 변수에는 전체 이벤트들이 담겨있는데 json으로 담겨있다. 
//                                            // 이를 json_decode라는 함수로 디코딩한 후 이 값을 dataArr변수에 담는다. 
//  // 함수에 담긴 각각의 값 확인 
//   for($i=0; $i < count($dataArr); $i++){
//     // 이들을 변수에 담아 db로 넣는다. 
//       $title= $dataArr[$i]->title;
//       $start= $dataArr[$i]->start;
//       $end= $dataArr[$i]->end;
//       $allday= $dataArr[$i]->allday;
      
//       //sql 구문
//       $SQL = "insert into pet_schedule ( schedule_name, start_date, end_date, allday) 
//       values ('".$title."','".$start."','".$end."','". $allday."')";

//       mysqli_query($db_link,$SQL);
//      }                                
//     echo "OK"; // 정상적으로 실행된다면, ok 문구를 출력함 
?>