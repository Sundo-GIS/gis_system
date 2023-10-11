
// //  datePicker 설정
// $(function () {
	
// 	$('#datepicker').datepicker({
// 		calendarWeeks: false,
// 		todayHighlight: true,
// 		autoclose: true,
// 		format: "yyyy-mm-dd",
// 		language: "kr"
// 	});
// 	todayCheck();
// });

// //  오늘 날짜 표시하기
// function todayCheck() {
// 	//  현재의 년도와 월 받아오기
// 	const currentYear = new Date().getFullYear();
// 	const currentMonth = new Date().getMonth() + 1;
// 	const today = new Date();
// 	if (currentMonth === today.getMonth() + 1 && currentYear === today.getFullYear()) {
// 		for (let date of document.querySelectorAll('.day')) {
// 			if (+date.innerHTML === today.getDate()) {
// 				date.setAttribute('id', 'today');
// 				break
// 			}
// 		}
// 	}
// }



// // datePicker 날짜, 차량 선택시 map 변경
// // 데이터베이스에서 JSP로 갖고오고 차값을 사용하면 됨  
// $('.calendar-date').datepicker().on('changeDate', function (e) {
// 	// 선택한 날짜에서 연도와 월을 가져옵니다.
// 	const selectedDate = e.date;
// 	const year = String(selectedDate.getFullYear());
// 	const month = String(selectedDate.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 해주고 2자리로 포맷팅
// 	const day = String(selectedDate.getDate()).padStart(2, '0'); // 날짜를 2자리로 포맷팅
// 	const date = year + '-' + month + '-' + day;


// 	const carNumInput = document.getElementById('car_num');
// 	const carNum = carNumInput.value;

// 	// td.day 요소의 data-date 속성을 'yyyy-mm-dd' 형태로 설정합니다.
// 	// let tdDays = document.querySelectorAll('td.day');
// 	// tdDays.forEach(function (tdDay) {
// 	// 	tdDay.setAttribute('data-date', date);
// 	// });
// 	// $('.form-control').text(`${year}-${month}-${day}`);

// 	date.addEventListener('change', function (event) {
// 		updateMap(); // 날짜가 변경되면 지도 업데이트
// 	});

// 	carNum.addEventListener('change', function (event) {
// 		updateMap(); // 차량 번호가 변경되면 지도 업데이트
// 	});

// 	// 일자, 차량번호 선택하여 데이터 출력하기
// 	function updateMap() {
// 		// 사용자가 입력한 값을 가져오기

// 		var viewparams = 'date:' + date + ';carNum:' + carNum;
// 		line.getSource().updateParams({ 'viewparams': viewparams });
// 		point.getSource().updateParams({ 'viewparams': viewparams });
// 		start_point.getSource().updateParams({ 'viewparams': viewparams });
// 		end_point.getSource().updateParams({ 'viewparams': viewparams });

// 		// 중심 좌표 이동
// 		$.ajax({
// 			type: "GET",
// 			url: "/view", // 시작 요청을 보낼 엔드포인트 URL
// 			data: {
// 				date: date,
// 				carNum: carNum
// 			},
// 			dataType: "json",
// 			success: function (data) {
// 				var lon = data.x;
// 				var lat = data.y;

// 				map.getView().animate({
// 					center: ol.proj.transform([lon, lat], 'EPSG:4326', 'EPSG:3857'),
// 					zoom: 15,
// 					duration: 800
// 				});
// 			}

// 		});

// 	};

// })


// < DATA 모달창 !!!!!!!!!!!!!!!!!!!!!!!>
// 모달창 show
$('#add-data-btn').click(function (e) {
	e.preventDefault();
	$('#add-data-modal').modal("show");
});

// 모달창 hide
$('.csv-cancel-btn').click(function (e) {
	e.preventDefault();
	$('#add-data-modal').modal("hide");

	var gpsFile = $('#gpsfile');
	var noiseFile = $('#noisefile');
	var rpmFile = $('#rpmfile');

	gpsFile.val('');
	noiseFile.val('');
	rpmFile.val('');

});

// gps-csv
$(document).ready(function () {
	$("#gps-csv").on('change', function () {  // 값이 변경되면
		if (window.FileReader) {  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		}

		// 추출한 파일명 삽입
		$("#gpsfile").val(filename);
	});
});

// noise-csv
$(document).ready(function () {
	$("#noise-csv").on('change', function () {  // 값이 변경되면
		if (window.FileReader) {  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		}

		// 추출한 파일명 삽입
		$("#noisefile").val(filename);
	});
});

// rpm-csv
$(document).ready(function () {
	$("#rpm-csv").on('change', function () {  // 값이 변경되면
		if (window.FileReader) {  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		}

		// 추출한 파일명 삽입
		$("#rpmfile").val(filename);
	});
});

// csv 파일만 업로드가능
const csvFileInputs = document.querySelectorAll('.csv-file-input');

csvFileInputs.forEach((input) => {
	input.addEventListener('change', (event) => {
		const selectedFile = event.target.files[0];
		if (selectedFile) {
			const fileName = selectedFile.name;
			if (fileName.endsWith('.csv')) {

			} else {
				alert('.csv 파일을 선택해주세요.');
				input.value = '';
			}
		}
	});
});


// < DATA 모달창 끝 !!!!!!!!!!!!!!!!!!!!!!!>

// < CAR 모달창 >
// 모달창 show
$('#add-car-btn').click(function (e) {
	e.preventDefault();
	$('#add-car-modal').modal("show");
});

// 모달창 hide
$('.car-cancel-btn').click(function (e) {
	e.preventDefault();
	$('#add-car-modal').modal("hide");
});
