// 변수 선언(단순값)
let water=0;
let food=0;
let weight=0;
let treat=0;
let walkDistance=0;

// 종합 변수 선언(누적값), 초기값 0으로 설정, 누적값의 경우 함수내에서 사용함으로 var로 선언함
// var waterTotal = 0;
// var foodTotal =0;
// var weightTotal=0;
// var treatTotal=0;
// var walkDistanceTotal=0;

// 누적 합계 계산하기
// waterTotal += parseInt(water);
// foodTotal += parseInt(food);
// weightTotal += parseInt(weight);
// treatTotal += parseInt(treat);
// walkDistanceTotal += parseInt(walkDistance);


window.onload = () => {
  // location.search로 URL 파라미터 가져오기
  const getParams = new URLSearchParams(location.search);
  for (const param of getParams) {
    console.log(param);
     }
    
    function onClick() {
        document.querySelector('.modal_wrap').style.display ='block';
        document.querySelector('.black_bg').style.display ='block';
    }   
    function offClick() {
        document.querySelector('.modal_wrap').style.display ='none';
        document.querySelector('.black_bg').style.display ='none';
    }
 
    document.getElementById('modal_btn').addEventListener('click', onClick);
    document.querySelector('.modal_close').addEventListener('click', offClick);
 
 
 

 // 변수에 값 넣기 
  water = getParams.get('water');
  food = getParams.get('food');
  weight = getParams.get('weight');
  treat = getParams.get('treat');
  walkDistance= getParams.get('walkDistance');

  
  // 차트생성
var chart = c3.generate({
 bindto: '#total-chart',
    data: {
        columns: [  
        ['water', water], ['food', food],
        ['treat',treat],['walkDistance',walkDistance],
        ['weight',weight]
        ]
    }
});

 };
