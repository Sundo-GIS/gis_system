<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="kor">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
    <label for="hour">시:</label>
    <input type="number" id="hour" name="hour" min="0" max="23" step="1" required>
    
    <label for="minute">분:</label>
    <input type="number" id="minute" name="minute" min="0" max="59" step="1" required>
    
    <label for="second">초:</label>
    <input type="number" id="second" name="second" min="0" max="59" step="1" required>
    
	<button type="button" class="start">스케줄러 시작</button>
	<button type="button" class="stop">스케줄러 종료</button>
	
	<button type="button" class="cleanTime">날짜계산</button>
	
	<button type="button" class="coord">좌표 계산</button>
	
	<script>
	$(".start").click(function() {
		var hour = document.getElementById("hour").value*60*60*1000;
	    var minute = document.getElementById("minute").value*60*1000;
	    var second = document.getElementById("second").value*1000;
		console.log(hour+" " + minute + " " + second);
		const time = hour+minute+second;
		if(time==0) {
			alert("시간을 입력하세요");
		}
		$.ajax({
			type : "GET",
			url : "/gis/start", // 시작 요청을 보낼 엔드포인트 URL
			data: { time: time },
			success : function() {
				alert("스케줄러가 시작되었습니다.");
			},
			error : function() {
				alert("스케줄러 시작에 실패했습니다.");
			}
		});
	});

	// "스케줄러 종료" 버튼 클릭 이벤트
	$(".stop").click(function() {
		$.ajax({
			type : "GET",
			url : "/gis/stop", // 종료 요청을 보낼 엔드포인트 URL
			success : function() {
				alert("스케줄러가 종료되었습니다.");
			},
			error : function() {
				alert("스케줄러 종료에 실패했습니다.");
			}
		});
	});
	
	$(".cleanTime").click(function() {
		$.ajax({
			type : "GET",
			url : "/gis/statistics", // 시작 요청을 보낼 엔드포인트 URL
			success : function() {
				alert("스케줄러가 시작되었습니다.");
			},
			error : function() {
				alert("스케줄러 시작에 실패했습니다.");
			}
		});
	});

</script>
</body>
</html>
