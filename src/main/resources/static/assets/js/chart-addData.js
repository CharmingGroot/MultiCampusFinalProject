/**
 * 
 */
function addData() {
  // 입력된 값을 가져옴
  let newWater = parseInt(document.getElementById("water").value);
  let newFood = parseInt(document.getElementById("food").value);
  let newWeight = parseInt(document.getElementById("weight").value);
  let newTreat = parseInt(document.getElementById("treat").value);
  let newWalkDistance = parseInt(document.getElementById("walkDistance").value);
  
  // updateChart 함수 호출
  updateChart(newWater, newFood, newWeight, newTreat, newWalkDistance);
}