window.onload = function () {
    function onGeoOk(position) {
        const API_KEY = "openweather API"; //openweather API키 입력
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const weather_url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&lang=kr&appid=${API_KEY}&units=metric`;
        const dust_url = `http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lng}&appid=${API_KEY}`;

        const mainval = document.querySelector("#mainval") // 대기질 지수
        const pm10 = document.querySelector("#pm10"); // 미세먼지
        const pm2_5 = document.querySelector("#pm2_5"); // 초미세먼지
        const mainvalemoji = document.querySelector("#mainval"); // 대기질 지수 이모지 치환

        const city = document.querySelector("#city") // 도시 이름(현재 위치)
        const nowtemp = document.querySelector("#nowtemp"); // 현재 온도
        const feellike = document.querySelector("#feellike"); // 체감 온도
        const icon = document.querySelector("#icon"); // 날씨 아이콘
        const humid = document.querySelector("#humid"); // 습도
        const wind = document.querySelector("#wind"); // 풍속
        const desc = document.querySelector("#desc"); // 날씨 설명

        fetch(weather_url)
            .then((response) => response.json())
            .then((data) => {

                city.innerText = "현재 " + `${data.name}` + "의 날씨는 " +`${data.weather[0].description}`;
                // desc.innerText = data.weather[0].description;
                nowtemp.innerText = "현재 기온은 " + Math.round(`${data.main.temp}`) + "°C";
                feellike.innerText = "체감 기온은 " + Math.round(`${data.main.feels_like}`) + "°C";
                humid.innerText = "습도는 " + `${data.main.humidity}` + "%";
                wind.innerText = "풍속은 " + Math.round(`${data.wind.speed}`) + "m/s";
                const iconUrl = `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;
                icon.innerHTML = `<img src="${iconUrl}">`;

            })

            fetch(dust_url)
            .then((response) => response.json())
            .then((data) => {

                mainval.innerHTML = `${data.list[0].main.aqi}`;
                mainvalemoji.innerHTML = `${data.list[0].main.aqi}`;
                pm10.innerText = "미세먼지 농도는 " + Math.round(`${data.list[0].components.pm10}`) + "μg3/m";
                pm2_5.innerText = "초미세먼지 농도는 " + Math.round(`${data.list[0].components.pm2_5}`) + "μg3/m";
                
            })
            .catch((error) => console.log("error:", error));
    }

    function onGeoError() {
        alert("위치 정보를 가져오지 못했어요");
    }
    navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
};