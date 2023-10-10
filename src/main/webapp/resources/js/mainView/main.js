// $(document).ready(function () {
//     $("#csv-register-btn").click(function () {
//         var formData = new FormData($("#uploadForm")[0]);

//         $.ajax({
//             url: "your-java-upload-endpoint", // Java 서버 업로드 엔드포인트 URL
//             type: "POST",
//             data: formData,
//             processData: false,
//             contentType: false,
//             success: function (response) {
//                 // 업로드 성공 처리
//                 alert("데이터가 성공적으로 업로드되었습니다.");
//                 $("#uploadModal").modal("hide");
//             },
//             error: function (xhr, status, error) {
//                 // 업로드 실패 처리
//                 alert("데이터 업로드 중 오류가 발생했습니다.");
//             }// $(document).ready(function () {
//     $("#csv-register-btn").click(function () {
//         var formData = new FormData($("#uploadForm")[0]);

//         $.ajax({
//             url: "your-java-upload-endpoint", // Java 서버 업로드 엔드포인트 URL
//             type: "POST",
//             data: formData,
//             processData: false,
//             contentType: false,
//             success: function (response) {
//                 // 업로드 성공 처리
//                 alert("데이터가 성공적으로 업로드되었습니다.");
//                 $("#uploadModal").modal("hide");
//             },
//             error: function (xhr, status, error) {
//                 // 업로드 실패 처리
//                 alert("데이터 업로드 중 오류가 발생했습니다.");
//             }
//         });
//     });
// });


//  datePicker 설정
$(function () {
    $('#datepicker').datepicker({
        calendarWeeks: false,
        todayHighlight: true,
        autoclose: true,
        format: "yyyy-mm-dd",
        language: "kr"
    });
});

// datePicker 날짜 선택 이벤트
$('.calendar-date').datepicker().on('changeDate', function (e) {
    // 선택한 날짜에서 연도와 월을 가져옵니다.
    const selectedDate = e.date;
    const year = selectedDate.getFullYear();
    const month = selectedDate.getMonth() + 1; // 월은 0부터 시작하므로 +1 해줍니다.
    const day = selectedDate.getDate();
});


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
//         });
//     });
// });