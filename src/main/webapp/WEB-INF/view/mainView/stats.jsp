<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!-- char.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<title>Stats</title>

<style>
.b {
	border: 1px solid black;
}
</style>

</head>
<body>
	<div class="conainer">
		<div class="row justify-content-center mt-5">
			<div class="dropdown col-10">
				<select class="car-select col-12 fs-3 h-100" name="car_num_list"
					id="stats-car-list">
					<option disabled selected class="text-center">차량을 선택하세요.</option>
					<c:forEach var="list" items="${carNumList}" varStatus="st">
						<!-- <option value=${st.count} class="text-center">${list.carNum}</option> -->
						<option value=${list.carNum } class="text-center selectedDate">${list.carNum}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row justify-content-end">
			<div class="col-7 mt-5">
				<label for="date">시작 일자</label> <input type="date"
					id="first-date-input" name="date"> <label for="date">마지막
					일자</label> <input type="date" id="last-date-input" name="date">
			</div>
		</div>
		<div class="row justify-content-center mt-5">
			<div class="col-10">
				<table class="table table-bordered ">
					<tbody>
						<tr>
							<th class="w-25">총 운행시간</th>
							<td id="clean-time">00:00:00</td>
						</tr>
						<tr>
							<th class="w-25">청소비율</th>
							<td id="clean-ratio">0 %</td>
						</tr>
						<tr>
							<th class="w-25">총 운행거리</th>
							<td id="total-distance">0 Km</td>
						</tr>
						<tr>
							<th class="w-25"><span>유효 운행거리</span></th>
							<td id="clean-distance">0 Km</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row mt-3 justify-content-end">
			<div class="col-2">
				<button type="submit" class="check-btn btn"
					style="background-color: #bd445b; color: white;">조회</button>
			</div>
		</div>
	</div>

	<script src="/resources/js/mainView/stats.js"></script>
</body>
</html>