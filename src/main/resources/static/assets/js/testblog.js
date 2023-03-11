




window.onload = () => {
  // URL 파라미터 값 받아오기
  const getParams = new URLSearchParams(location.search);
  for (const param of getParams) {
    console.log(param);
  }

  let water = getParams.get('water');
  let food = getParams.get('food');
  let weight = getParams.get('weight');

  //받아온 값 세션에 저장
  sessionStorage.setItem('water', water);
  sessionStorage.setItem('food', food);
  sessionStorage.setItem('weight', weight);

  // 일일 권장 식사량
  let RAOF = ((parseInt(weight) * 1000) * 0.02);
  console.log(RAOF);

  // 차트생성
  var chart = c3.generate({
    bindto: '.water-chart',
    data: {
      columns: [
        ['급수량', water], ['급식량', food]
      ],
      type: 'gauge',
    },
    gauge: {
      //        label: {
      //            format: function(value, ratio) {
      //                return value;
      //            },
      //            show: false // to turn off the min/max labels.
      //        },
      min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
      max: 400, // 일일 권장 식사량
      //    units: ' %',
      //    width: 39 // for adjusting arc thickness
    },
    color: {
      pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
      threshold: {
        //            unit: 'value', // percentage is default
        //            max: 200, // 100 is default
        values: [30, 60, 90, 100]
      }
    },
    size: {
      height: 250
    }
  });


  // var chart2 = c3.generate({
  //   bindto: '.food-chart',
  //   data: {
  //     columns: [
  //       ['급식량', food]
  //     ],
  //     type: 'gauge',
  //   },
  //   gauge: {
  //     //        label: {
  //     //            format: function(value, ratio) {
  //     //                return value;
  //     //            },
  //     //            show: false // to turn off the min/max labels.
  //     //        },
  //     //    min: 0, // 0 is default, //can handle negative min e.g. vacuum / voltage / current flow / rate of change
  //     //    max: 100, // 100 is default
  //     //    units: ' %',
  //     //    width: 39 // for adjusting arc thickness
  //   },
  //   color: {
  //     pattern: ['#FF0000', '#F97600', '#F6C600', '#60B044'], // the three color levels for the percentage values.
  //     threshold: {
  //       //            unit: 'value', // percentage is default
  //       //            max: 200, // 100 is default
  //       values: [30, 60, 90, 100]
  //     }
  //   },
  //   size: {
  //     height: 150
  //   }
  // });

  // setTimeout(function () {
  //   chart.load({
  //     columns: [['data', 10]]
  //   });
  // }, 1000);

  // setTimeout(function () {
  //   chart.load({
  //     columns: [['data', 50]]
  //   });
  // }, 2000);

  // setTimeout(function () {
  //   chart.load({
  //     columns: [['data', 70]]
  //   });
  // }, 3000);

  // setTimeout(function () {
  //   chart.load({
  //     columns: [['data', 0]]
  //   });
  // }, 4000);

  // setTimeout(function () {
  //   chart.load({
  //     columns: [['data', 100]]
  //   });
  // }, 5000);


  // 타이머 초기화
  var clockTarget = document.getElementById("clock");
  function clock() {
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    clockTarget.innerText = `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
  }

  function init() {
    clock();
    setInterval(clock, 1000);
  }

  init();


} // window.onload end



// 타이머
var time = 0;
var running = 0;  // 0은 멈춤, 1은 실행중
var timerid = 0;
// var intervalid = 0;
let walking;

function test() {
  // 10초마다 locationCalculator 호출
  walking = setInterval(() => {
    locationCalculator()
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

    test();

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
    document.getElementById('stopTime').innerHTML = "일시정지  " + hour + ":" + min + ":" + sec;
    document.getElementById("start").innerHTML = "계속";
    document.getElementById("startPause").style.backgroundColor = "green";
    document.getElementById("startPause").style.borderColor = "green";
  }
}

//산책시간 업데이트 
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
  running = 0;
  time = 0;
  clearTimeout(timerid);
  clearInterval(walking);
  document.getElementById('stopTime').innerHTML = "";
  document.getElementById("start").innerHTML = "시작";
  document.getElementById("output").innerHTML = "<b>00:00:00</b>";
  document.getElementById("startPause").style.backgroundColor = "green";
  document.getElementById("startPause").style.borderColor = "green";

  console.log(`산책거리는 ${totalDistance} 입니다.`);
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
let totalDistance = 0;


let dummylati = 37.5179690;
let dummylongti = 126.857308;








// 이동거리 계산 함수, 이전 위경도값과 현재위경도값을 매개변수로 받아서 두 위경도 사이의 거리를 측정.
function calDistance(previousLatitude, previousLongitude, dummylati, dummylongti) {
  let theta = (previousLongitude - dummylongti);
  dist = Math.sin(degTorad(previousLatitude)) * Math.sin(degTorad(dummylati)) + Math.cos(degTorad(previousLatitude)) * Math.cos(degTorad(dummylati)) * Math.cos(degTorad(theta));
  dist = Math.acos(dist);
  dist = radTodeg(dist);
  dist = dist * 60 * 1.1515;
  dist = dist * 1.609344;  // 키로미터로 변환 1mile == 1.609344km
  console.log('dist : ' + dist);
  totalDistance += dist;

  return Number(totalDistance * 1000).toFixed(2);
};

function degTorad(deg) {
  return (deg * Math.PI / 180);
}
function radTodeg(rad) {
  return (rad * 180 / Math.PI);
}












// 위경도 값을 받아오는 함수
function locationCalculator() {
  // console.log("loactionCalculator 실행됐다~");


  // 만약 startLatitude 또는 startLongtitude에 저장된 값이 없다면 시작 위경도 받아오기
  if (startLatitude == 0 || startLogntitude == 0) {
    navigator.geolocation.getCurrentPosition(
      function (position) {
        startLatitude = position.coords.latitude;
        startLogntitude = position.coords.longitude;
        previousLatitude = position.coords.latitude;
        previousLongitude = position.coords.longitude;
        console.log("-----------------------------------");
        console.log("초기 위경도값 X");
        // console.log(`startLatitude: ${startLatitude}`); // test
        // console.log(`startLogntitude: ${startLogntitude}`); // test
        // console.log(`previousLatitude: ${previousLatitude}`); // test
        // console.log(`previousLongitude: ${previousLongitude}`); // test
        // console.log(`currentLatitude: ${currentLatitude}`); // test
        // console.log(`currentLongtitude: ${currentLongtitude}`); // test
        console.log(`totalDistance: ${totalDistance}`);
        console.log("-----------------------------------");
      }
    )


  }

  // 시작 위경도 값이 있는 경우
  else {
    navigator.geolocation.getCurrentPosition(
      function (position) {

        currentLatitude = position.coords.latitude;
        currentLongtitude = position.coords.longitude;

        console.log("-----------------------------------");
        console.log("초기 위경도 값 O");
        // console.log(`previousLatitude: ${previousLatitude}`); // test
        // console.log(`previousLongitude: ${previousLongitude}`); // test
        // console.log(`currentLatitude: ${currentLatitude}`); // test
        // console.log(`currentLongtitude: ${currentLongtitude}`); // test
        // console.log("-----------------------------------");

        // 계산 **더미좌표 current좌표로 바꿀것
        calDistance(previousLatitude, previousLongitude, currentLatitude, currentLongtitude);

        // 계산 후 previous 값들을 current값으로 초기화
        previousLatitude = currentLatitude;
        previousLongitude = currentLongtitude;

        // 총거리
        console.log(`totalDistance: ${totalDistance}`);

        // console.log(`previousLatitude: ${previousLatitude} \n previousLongitude: ${previousLongitude}`); // test
        // console.log(`currentLatitude: ${currentLatitude} \n currentLongtitude: ${currentLongtitude}`); // test
        console.log("-----------------------------------");


      });

  };



  // 값 잘 받아와지나 test
  // console.log(latitude);
  // console.log(longitude);

  // 10초마다 위경도값을 받아올테니, map 형태로 저장하는 것이 좋을듯.


};



