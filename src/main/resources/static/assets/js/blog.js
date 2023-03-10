




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




}

