window.onload = function() {
    function onGeoOk(position) {
        const API_KEY = "API_KEY"; //openweather API키 입력
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const url = `http://api.openweathermap.org/data/2.5/airpollution?lat=${lat}&lon=${lng}&appid=${API_KEY}`;
        

        const mainval = document.querySelector("#mainval"); // 대기질 지수
        const pm10 = document.querySelector("#pm10"); // 미세먼지 농도
        const pm2_5 = document.querySelector("#pm2_5"); // 초미세먼지 농도

        fetch(url)
            .then((response) => response.json())
            .then((data) => {
                
                mainval.innerText = `${data.list[0].main.aqi}`;
                pm10.innerText = "미세먼지 : " + `${data.list[0].components.pm10}` + "μg3/m";
                pm2_5.innerText = "초미세먼지 : " + `${data.list[0].components.pm2_5}` + "μg3/m";

            })
            .catch((error) => console.log("error:", error));
    }

    function onGeoError() {
        alert("위치 정보를 가져오지 못했어요");
    }
    navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
};