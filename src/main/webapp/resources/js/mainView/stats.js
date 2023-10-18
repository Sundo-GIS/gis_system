let statsCarList = document.getElementById("stats-car-list");
let firstDateInput = document.getElementById("first-date-input");
let lastDateInput = document.getElementById("last-date-input");

let cleanTime = document.getElementById("clean-time");
let cleanRatio = document.getElementById("clean-ratio");
let totalDistance = document.getElementById("total-distance");
let cleanDistance = document.getElementById("clean-distance");


const checkBtn = document.querySelector(".check-btn");
checkBtn.addEventListener('click', () => {

	let statsCar = statsCarList.value;
	let firstDate = firstDateInput.value;
	let lastDate = lastDateInput.value;

	if (!(firstDate == "") && !(lastDate == "")) {
		if (firstDate < lastDate) {
			if (statsCar !== "차량을 선택하세요.") {
				$.ajax({
					type: "GET",
					url: "/stats/select", // 시작 요청을 보낼 엔드포인트 URL
					data: {
						carNum: statsCar,
						firstDate: firstDate,
						lastDate: lastDate
					},
					dataType: "json",
					success: function(data) {
						cleanTime.innerText = data.cleanTime;
						cleanRatio.innerText = data.cleanRatio.toFixed(2) + "%";
						totalDistance.innerText = data.totalDistance.toFixed(2) + "km";
						cleanDistance.innerText = data.cleanDistance.toFixed(2) + "km";
					}, error: function() {
						alert("해당 차량은 해당 기간의 데이터가 없습니다.");
					}
				});
			} else {
				alert("차량을 선택하세요.")
			}
		} else {
			alert("시작날짜와 마지막날짜를 확인해주세요.");
		}
	} else {
		alert("날짜를 선택해주세요.");
	}
})

// 차량 바꿀때 초기화 
statsCarList.addEventListener('change', () => {
	cleanTime.innerText = "00:00:00";
	cleanRatio.innerText = "0 %";
	totalDistance.innerText = "0 km";
	cleanDistance.innerText = "0 km";
});