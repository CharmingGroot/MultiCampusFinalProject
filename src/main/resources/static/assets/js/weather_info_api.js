window.onload = function () {
    function onGeoOk(position) {
        const API_KEY = "API_KEY"; //openweather API키 입력
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&lang=kr&appid=${API_KEY}&units=metric`;
        
        const city = document.querySelector("#city") // 도시 이름(현재 위치)
        const nowtemp = document.querySelector("#nowtemp"); // 현재 온도
        const feellike = document.querySelector("#feellike"); // 체감 온도
        const icon = document.querySelector("#icon"); // 날씨 아이콘
        const humid = document.querySelector("#humid"); // 습도
        const wind = document.querySelector("#wind"); // 풍속
        const desc = document.querySelector("#desc"); // 날씨 설명

        fetch(url)
            .then((response) => response.json())
            .then((data) => {

                city.innerText = data.name;
                desc.innerText = data.weather[0].description;
                nowtemp.innerText = "현재 날씨 : " + Math.round(`${data.main.temp}`) + "°C";
                feellike.innerText = "체감 온도 : " + Math.round(`${data.main.feels_like}`) + "°C";
                humid.innerText = "습도 : " + `${data.main.humidity}` + "%";
                wind.innerText = "풍속 : " + `${data.wind.speed}` + "m/s";
                const iconUrl = `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;
                icon.innerHTML = `<img src="${iconUrl}">`;

            })
            .catch((error) => console.log("error:", error));
    }

    function onGeoError() {
        alert("위치 정보를 가져오지 못했어요");
    }
    navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
};