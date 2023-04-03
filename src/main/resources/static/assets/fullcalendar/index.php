<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>schedule</title>
  <script defer src="../assets/fullcalendar/dist/index.global.js"></script>
<script defer src="../assets/fullcalendar/lib/main.min.css"></script>
<script defer src="../assets/fullcalendar/lib/main.min.js"></script>
   <script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
</head>
<body>

<script>

// jquey 확인용
// $("div").css("background-color", "red");


// caledar를 전역변수로 선언해서 어디서든 쓸 수 있게 하기 
   var calendar= null;

   $(document).ready(function() {
    var calendarEl = document.getElementById('calendar');

    // 캘린더에 값주기 
    calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: '2023-03-17',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      // 일정추가하기 기능 
      select: function(arg) {
    	  // 프롬프트로 추가가능 
        var title = prompt('Add schedule:');
        if (title) {
        	//이벤트 추가
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      // 삭제기능
      eventClick: function(arg) {
        if (confirm('Do you want to delete this event?')) {
          arg.event.remove()
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
     
      ]
    });

    calendar.render();
  });

  // 전체 이벤트 데이터를 추출해야한다. 
  function allSave(){
  var allEvent = calendar.getEvents();
  // allEvent 객체가 잘 넘어오는지 콘솔찍어서 확인
  // getEvent는 배열 형태로 넘어오는데 잘 넘어옴, 안에 content에 title내용이 일정 제목이 들어감
  console.log(allEvent);
  // 이벤트를 배열에 한 번 더 담아준다. 
  var events = new Array();
  
  // for문을 돌린다. allEvent의 길이만큼 i를 돌린다. 
  for(var i =0; i <allEvent.length; i++ ){
      // obj 객체에 담는다. 
	  var obj = new Object();
	  
	  // 디비에 저장할 내용
	 obj.title= allEvent[i]._def.title // 이벤트 명칭 출력
	 obj.allday= allEvent[i]._def.allDay // 하루 종일의 이벤트인지 알려주는 boolean 값 (true,false)
	 obj.start= allEvent[i]._instance.range.start; // 이벤트 시작 시간 및 날짜
	 obj.end= allEvent[i]._instance.range.end; // 이벤트 종료 시간 및 날짜
  
	 // 전체 events들이 배열형태로 json 객체 형태로 events 변수에 담긴다. 
     events.push(obj);
  }
  
  // events를 서버 전송시, 문자열 형태로 넘길것이라 stringfy를 사용
  var jsondata= JSON.stringify(events);
  console.dir(jsondata);
  
  savedata(jsondata);
  
}

// ajax를 사용해서 db 연결 저장하기 
// jsondata 에 담긴 데이터들을 전송하기 
function savedata(jsondata){
  // ajax 자체가 서버에 전송하는 것
  $.ajax({
     type : 'POST',
     url: "savedata.php",
     data:{"alldata" : jsondata}, 
     dataType: 'text',
     async: false
  })
  .done(function(result){
     alert(result);
  }) // 성공시 진행, 성공한 내용에 대한 문자 정보가 result에 담길 것 
  .fail(function(request,status,error){
    alert('에러 발생: '+ error );
  }) // 실패시 진행
}
  













</script>
</body>
</html>