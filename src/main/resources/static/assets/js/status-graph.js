// 변수 선언
let water;
let food;
let weight;
let treat;
let walkDistance;

  // 일일 권장 식사량
  let RAOF = ((parseInt(weight) * 1000) * 0.02);
  console.log(RAOF);

window.onload = () => {
  // URL 파라미터 값 받아오기
  const getParams = new URLSearchParams(location.search);
  for (const param of getParams) {
    console.log(param);
  }

 // 변수에 값 넣기 
  water = getParams.get('water');
  food = getParams.get('food');
  weight = getParams.get('weight');
  treat = getParams.get('treat');
  walkDistance= getParams.get(walkDistance);

  //받아온 값 세션에 저장
  sessionStorage.setItem('water', water);
  sessionStorage.setItem('food', food);
  sessionStorage.setItem('weight', weight);
  sessionStorage.setItem('treat',treat);
  sessionStorage.setItem('walkDistance',walkDistance);
  
  // 차트생성
var chart = c3.generate({
  bindto: '#linechart',
    data: {
        columns: [  
        ['water', water], ['food', food],
        ['treat',treat],['walkDistance',walkDistance],
        ['weight',weight]
        ]
    }
});

setTimeout(function () {
    chart.load({
        columns: [
            ['water', water]
        ]
    });
}, 1000);

setTimeout(function () {
    chart.load({
        columns: [
            ['data3', 130, 150, 200, 300, 200, 100]
        ]
    });
}, 1500);





