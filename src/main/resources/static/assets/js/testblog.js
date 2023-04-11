
// 일일 권장 식사량 계산함수
function dailyRecommendedMealAmount(weight) {
  let raof = (parseFloat(weight) * 1000) * 0.02;
  return raof;
};

// 일일 권장 급수량 계산함수
function dailyRecommendedWaterAmount(weight) {
  let raow = weight * 65;
  return raow;
}


// 값이 없는 상태의 차트생성함수
function chartGeneratorZeroValue() {
  var chart = c3.generate({
    bindto: '.water-chart',
    data: {
      columns: [
        ['급수량', 0], ['급식량', 0]
      ],
      type: 'gauge',
    },
    gauge: {
      min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
      max: 150, // 일일 권장 식사량
    },
    color: {
      pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
      threshold: {
        values: [30, 60, 90, 100]
      }
    },
    size: {
      height: 250
    }
  });
  sessionStorage.setItem('water', 0);
  sessionStorage.setItem('food', 0);
  sessionStorage.setItem('weight', 0.0);

}


// 차트생성함수
function chartGenerator(water, food, weight) {

  // 세션에 저장해둔 water food weight 값 비우기
  sessionStorage.removeItem('water');
  sessionStorage.removeItem('food');
  sessionStorage.removeItem('weight');

  let feedMax = dailyRecommendedMealAmount(weight);

  console.log(water, food, weight);
  var chart = c3.generate({
    bindto: '.water-chart',
    data: {
      columns: [
        ['급수량', water], ['급식량', food]
      ],
      type: 'gauge',
    },
    gauge: {
      min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
      max: feedMax, // 일일 권장 식사량
    },
    color: {
      pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
      threshold: {
        values: [30, 60, 90, 100]
      }
    },
    size: {
      height: 250
    }
  });


  // water food weight 값 세션에 저장
  sessionStorage.setItem('water', water);
  sessionStorage.setItem('food', food);
  sessionStorage.setItem('weight', weight);



}

// 타이머 초기화
function clock() {
  var clockTarget = document.getElementById("clock");
  var date = new Date();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  var seconds = date.getSeconds();
  clockTarget.innerText = `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
}

// 타이머 인터벌 실행
function init() {
  clock();
  setInterval(clock, 1000);
}












window.onload = () => {

  // let water = sessionStorage.getItem('water');
  // let food = sessionStorage.getItem('food');
  // let weight = sessionStorage.getItem('weight');

  // let waterString = document.getElementById('water').innerText;
  // let foodString = document.getElementById('food').innerText;
  // let weightString = document.getElementById('weight').innerText;

  console.log(document.getElementsByClassName('pet-walkTime'));

  const petArr = document.getElementsByClassName('pet-walkTime')

  let waterString = document.getElementById('water').innerText;
  let foodString = document.getElementById('food').innerText;
  let weightString = document.getElementById('weight').innerText;



  let water = parseInt(waterString);
  let food = parseInt(foodString);
  let weight = parseInt(weightString);


  // 차트 생성함수
  chartGeneratorZeroValue();


  clock();

  init();

  kakaoMapCover();

} // window.onload end






// ======================전역변수


// 타이머
var time = 0;
let walkTime = 0; // 초
var running = 0;  // 0은 멈춤, 1은 실행중
var timerid = 0;
let walking;


// 산책시작 전 맵 커버
function kakaoMapCover() {
  const map = document.getElementById("map");
  map.innerText = "1일 1산책은 필수!";
  map.setAttribute("style", "background-image: url(../assets/img/testdog/bulldog2.jpg);position: inherit; width: 100%; height: 100%;background-size: cover;background-position: center; opacity:0.5;  border-top-left-radius: 0.5rem;  border-bottom-left-radius: 0.5rem;");
}



// kakao maps
function kakaoMapsRender() {
  var container = document.getElementById('map');
  container.innerText = "";
  container.setAttribute("style", "position: inherit; width: 100%; height: 100%;border-top-left-radius: 0.5rem;  border-bottom-left-radius: 0.5rem;")
  var options = {
    center: new kakao.maps.LatLng(currentLatitude, currentLongtitude),
    level: 3
  };

  var map = new kakao.maps.Map(container, options);

  // 마커가 표시될 위치입니다 
  var markerPosition = new kakao.maps.LatLng(currentLatitude, currentLongtitude);

  // 마커를 생성합니다
  var marker = new kakao.maps.Marker({
    position: markerPosition
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.setMap(map);

  // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
  // marker.setMap(null);    
}



//산책거리를 렌더링
function resultMap() {
}







function realtimeCalculator() {
  // 10초마다 locationCalculator 호출
  walking = setInterval(() => {
    locationCalculator();
    kakaoMapsRender();
  }, 3000);
}


function startPause() {



  if (running == 0) {

    //시작버튼
    running = 1;
    increment();
    document.getElementById('stopTime').innerHTML = "";
    document.getElementById("start").innerHTML = "일시중지";
    document.getElementById("startPause").style.backgroundColor = "red";
    document.getElementById("startPause").style.borderColor = "red";
    document.getElementById("startPause").style.color = "white";


    // realtimeCalculator 인터벌 호출
    realtimeCalculator();
  }
  else {
    //일시정시버튼
    running = 0;
    console.log('일시정지!!');
    clearTimeout(timerid);
    clearInterval(walking); // 인터벌 클리어 (반복 중지)
    var date = new Date();
    var hour = date.getHours();
    if (hour < 10) {
      hour = '0' + hour;
    }
    var min = date.getMinutes();
    if (min < 10) {
      min = '0' + min;
    }
    var sec = date.getSeconds();
    if (sec < 10) {
      sec = '0' + sec;
    }
    // document.getElementById('stopTime').innerHTML = "일시정지  " + hour + ":" + min + ":" + sec;
    document.getElementById('stopTime').innerHTML = "산책거리는 " + totalDistance.toFixed(2) + "Km 입니다.";
    document.getElementById("start").innerHTML = "계속하기";
    document.getElementById("startPause").style.backgroundColor = "green";
    document.getElementById("startPause").style.borderColor = "green";
  }
}

//산책시간 업데이트  record로 바꾸기 or 아예 없애기 
function update() {

  // 시 분 초 받아와서 초 단위로 합한 값을 세션에 저장, DB로 전달
  let hours = Math.floor(time / 3600);
  let mins = Math.floor(time % 3600 / 60);
  let secs = time % 3600 % 60;

  sessionStorage.setItem('hours', hours);
  sessionStorage.setItem('mins', mins);
  sessionStorage.setItem('secs', secs);

  console.log(`hours: ${hours}, mins : ${mins}, secs: ${secs}`);
}

function reset() {
  walkTime = time;
  console.log(walkTime);

  postWalkData(); // time 초기화 전에 실행

  running = 0;
  time = 0;
  clearTimeout(timerid);
  clearInterval(walking);
  document.getElementById('stopTime').innerHTML = "";
  document.getElementById("start").innerHTML = "시작";
  document.getElementById("output").innerHTML = "<b>00:00:00</b>";
  document.getElementById("startPause").style.backgroundColor = "green";
  document.getElementById("startPause").style.borderColor = "green";
  document.getElementById("startPause").style.color = "white";

  document.getElementById('stopTime').innerHTML = "오늘의 산책거리는 " + totalDistance.toFixed(2) + "Km 입니다.";
  console.log(`산책거리는 ${totalDistance.toFixed(2)} Km입니다.`);

  // 산책 거리가 표시될 지도
  // resultMap();






}

//타이머 시간측정 
function increment() {
  if (running == 1) {
    timerid = setTimeout(function () {
      time++;
      var hours = Math.floor(time / 3600);
      var mins = Math.floor(time % 3600 / 60);
      var secs = time % 3600 % 60;
      if (hours < 10) {
        hours = "0" + hours;
      }
      if (mins < 10) {
        mins = "0" + mins;
      }
      if (secs < 10) {
        secs = "0" + secs;
      }
      document.getElementById("output").innerHTML = "<b>" + hours + ":" + mins + ":" + secs + "</b>";
      increment();
    }, 1000)
  }
}







// 시작 위경도, 처음 값을 받은 후로 사용되지 않음
let startLatitude = 0;
let startLogntitude = 0;

// 이전 위경도, 거리계산을 위함.
let previousLatitude = 0;
let previousLongitude = 0;

// 현재 위경도, 거리계산을 위함. 거리 계산이 끝나면 previous에 재할당
let currentLatitude = 0;
let currentLongtitude = 0;


// 총거리
var totalDistance = 0;

// 두 점간 거리
let dist = 0;


// 좌표 조작을 위한 테스트 변수
var i = 0;

// 좌표를 담을 배열
const latiArr = [];
const longtiArr = [];




// 두 좌표간 거리 계산 함수
function calDistance(lat1, lon1, lat2, lon2) {
  var theta = lon1 - lon2;
  dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
    * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
  dist = Math.acos(dist);
  dist = rad2deg(dist);
  dist = dist * 60 * 1.1515;
  dist = dist * 1.609344;

  console.log('좌표간 이동거리= ' + dist.toFixed(2));
  // 총거리 합산
  totalDistance += dist;
  console.log('총 이동거리 = ' + totalDistance.toFixed(2));
  return Number(dist * 1000).toFixed(2);

}

function deg2rad(deg) {
  return (deg * Math.PI / 180);
}
function rad2deg(rad) {
  return (rad * 180 / Math.PI);
}










// 위경도 값을 받아오는 함수
function locationCalculator() {
  // 만약 startLatitude 또는 startLongtitude에 저장된 값이 없다면 시작 위경도 받아오기
  if (startLatitude == 0 || startLogntitude == 0) {
    navigator.geolocation.getCurrentPosition(
      function (position) {
        startLatitude = position.coords.latitude;
        startLogntitude = position.coords.longitude;
        // previous 위경도 값도 시작값으로 초기화, else문에 활용하기 위함
        previousLatitude = position.coords.latitude;
        previousLongitude = position.coords.longitude;

        latiArr.push(startLatitude);
        longtiArr.push(startLogntitude);


        console.log("-----------------------------------");
        console.log("초기 위경도값 X");
        console.log(`previousLatitude =  ${previousLatitude}`);
        console.log(`previousLongitude =  ${previousLongitude}`);
        console.log(`currentLatitude =  ${currentLatitude}`);
        console.log(`currentLongtitude =  ${currentLongtitude}`);
        console.log("-----------------------------------");
      }
    )


  }

  // 시작 or 이전 위경도 값이 있는 경우 or 
  else if (startLatitude != 0 || startLogntitude != 0 || previousLatitude != 0 || previousLongitude != 0) {
    navigator.geolocation.getCurrentPosition(
      function (position) {

        currentLatitude = position.coords.latitude;
        currentLongtitude = position.coords.longitude;


        // 위경도 임의 조작
        i += 0.0001;
        currentLatitude = currentLatitude + i;
        latiArr.push(currentLatitude);
        longtiArr.push(currentLongtitude);

        console.log("-----------------------------------");
        console.log("초기 위경도 값 O");
        console.log(`previousLatitude =  ${previousLatitude}`);
        console.log(`previousLongitude =  ${previousLongitude}`);
        console.log(`currentLatitude =  ${currentLatitude}`);
        console.log(`currentLongtitude =  ${currentLongtitude}`);
        console.log("-----------------------------------");


        // 두 좌표간 거리 계산 & 총 거리 계산
        calDistance(previousLatitude, previousLongitude, currentLatitude, currentLongtitude);

        // 계산 후 previous 값들을 current값으로 초기화
        previousLatitude = currentLatitude;
        previousLongitude = currentLongtitude;


        // console.log(`계산 후 previousLatitude =  ${previousLatitude}`);
        // console.log(`계산 후 previousLongitude =  ${previousLongitude}`);
      });



  };




  // 값 잘 받아와지나 test
  // console.log(latitude);
  // console.log(longitude);

  // 10초마다 위경도값을 받아올테니, map 형태로 저장하는 것이 좋을듯.


};



function postWalkData() {

  const path = window.location.pathname;
  let pageOwnerArr = path.split('/');
  let pageOwner = pageOwnerArr[2];
  let td = (totalDistance.toFixed(2)).toString();

  // 체크박스 값 받는 로직
  const petCheckBoxes = document.getElementsByClassName("pet-checkbox");
  const values = [];

  for (const checkbox of petCheckBoxes) {
    if (checkbox.checked) values.push(checkbox.defaultValue);
    console.log(values);
    console.log(petCheckBoxes);
  };



  const data = {
    TTD: td,
    walkTime: walkTime,
    petNameList: values
  };

  console.log(data.TTD);

  fetch(`http://localhost:8080/blog/${pageOwner}/walk`, {
    method: 'POST', // 또는 'PUT'
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })
    .then((response) => response)
    .then((data) => {
      console.log('성공:', data);
    })
    .catch((error) => {
      console.error('실패:', error);
    });

}




function postFeedData(water, food, weight) {
  const path = window.location.pathname;
  let pageOwnerArr = path.split('/');
  let pageOwner = pageOwnerArr[2];

  // 체크박스 값 받는 로직
  const petCheckBoxes = document.getElementsByClassName("pet-Feed-checkbox");
  const values = [];

  // console.log(petCheckBoxes);

  for (const checkbox of petCheckBoxes) {
    if (checkbox.checked) values.push(checkbox.defaultValue);
    // console.log(values);
    // console.log(petCheckBoxes);
  };

  const feedData = {
    water: water,
    food: food,
    weight: weight,
    petNameList: values
  }


  fetch(`http://localhost:8080/blog/${pageOwner}/food`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(feedData),
  })
    .then((response) => response)
    .then((data) => {
      console.log('food POST 성공:', data);
    })
    .catch((error) => {
      console.error('food POST 실패:', error);
    });

}

const feedDataForm = document.getElementById("feedDataForm");
feedDataForm.addEventListener('submit', (e) => {
  e.preventDefault();

  const formData = new FormData(feedDataForm);

  const water = formData.get('water');
  const food = formData.get('food');
  const weight = formData.get('weight');

  dailyRecommendedMealAmount(weight);
  const chartDescription = document.getElementById('chart-description');
  chartDescription.innerText = `현재 체중에 권장되는 급식량은 ${dailyRecommendedMealAmount(weight)}g입니다.`

  chartGenerator(water, food, weight);

  postFeedData(water, food, weight);


});