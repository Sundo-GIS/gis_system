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
