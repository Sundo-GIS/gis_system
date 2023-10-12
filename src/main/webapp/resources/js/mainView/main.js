/* 파일 업로드 */
document.addEventListener('DOMContentLoaded', function() {
	const uploadForm = document.getElementById('upload-form');
	const registerButton = document.getElementById('csv-register-btn');


	registerButton.addEventListener('click', function() {
		const formData = new FormData(uploadForm);



	registerButton.addEventListener('click', function() {
		const formData = new FormData(uploadForm);


		// 서버 엔드포인트 설정
		const serverEndpoint = '/uploadCsv';

		fetch(serverEndpoint, {
			method: 'POST',
			body: formData
		})
			.then(response => {
				if (response.ok) {
					$('#add-data-modal').modal('hide'); // 모달 닫기
					alert('데이터가 성공적으로 추가되었습니다!');
					// 파일 입력 필드 초기화
					uploadForm.reset();
				} else {
					response.text().then(errorMessage => { // 서버 응답의 텍스트 본문을 errorMessage 변수에 저장
						alert(errorMessage);
					});
				}
			})
			.catch(error => {
				alert('데이터 추가를 실패하였습니다.');
				console.error('업로드 중 오류 발생: ', error);
			});
	});
});



// 파일 유효성 검사
async function validateFile(fileElement, fileType) {
	const file = fileElement.files[0];
	
	if (file) {
		const content = await file.text();
		switch (fileType) {
			case 'gps':
				if (!content.includes('lon') || !content.includes('lat')) {
					alert('gps 파일을 확인해 주세요');
					$("#gps-csv").val('');
					$("#gpsfile").val('');
				} else {

				}
				break;

			case 'noise':
				if (!content.includes('noise')) {
					alert('noise 파일을 확인해 주세요');
					$("#noise-csv").val('');
					$("#noisefile").val('');
				}
				break;
			case 'rpm':
				if (!content.includes('rpm')) {
					alert('rpm 파일을 확인해 주세요');
					$("#rpm-csv").val('');
					$("#rpmfile").val('');
				}
				break;
		}
	}
}


// < DATA 모달창 !!!!!!!!!!!!!!!!!!!!!!!>
// 모달창 show
$('#add-data-btn').click(function(e) {
	e.preventDefault();
	$('#add-data-modal').modal("show");
});

// 모달창 hide
$('.csv-cancel-btn').click(function(e) {
	e.preventDefault();
	$('#add-data-modal').modal("hide");
		$("#gps-csv").val('');
		$("#gpsfile").val('');
		$("#noise-csv").val('');
		$("#noisefile").val('');
		$("#rpm-csv").val('');
		$("#rpmfile").val('');
});

// gps-csv
$(document).ready(function() {
	$("#gps-csv").on('change', function() {  // 값이 변경되면
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
$(document).ready(function() {
	$("#noise-csv").on('change', function() {  // 값이 변경되면
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
$(document).ready(function() {
	$("#rpm-csv").on('change', function() {  // 값이 변경되면
		if (window.FileReader) {  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		}

		// 추출한 파일명 삽입
		$("#rpmfile").val(filename);
	});
});

// < DATA 모달창 끝 !!!!!!!!!!!!!!!!!!!!!!!>

// < CAR 모달창 >
// 모달창 show
$('#add-car-btn').click(function(e) {
	e.preventDefault();
	$('#add-car-modal').modal("show");
});

// 모달창 hide
$('.car-cancel-btn').click(function(e) {
	e.preventDefault();
	$('#add-car-modal').modal("hide");
});