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

<!-- openlayers -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/ol@v8.1.0/ol.css">
<!-- openlayers -->
<script src="https://cdn.jsdelivr.net/npm/ol@v8.1.0/dist/ol.js"></script>

<!-- fontawesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	rel="stylesheet">
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/25046d3d52.js"
	crossorigin="anonymous"></script>

<!-- datepicker -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
	integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- datepicker -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"
	integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.ko.min.js"
	integrity="sha512-L4qpL1ZotXZLLe8Oo0ZyHrj/SweV7CieswUODAAPN/tnqN3PA1P+4qPu5vIryNor6HQ5o22NujIcAZIfyVXwbQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>



<!-- local에서 갖고옴 -->
<link rel="stylesheet" href="/resources/css/mainView/main.css">
<link rel="stylesheet" href="/resources/css/mainView/calendar.css">

<title>Main</title>
<style>
.b {
	border: 1px solid black;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<div id="big-view" class="row">
			<div id="map" class="col-12 p-0"></div>

			<!-- live 버튼 -->
			<div class="live">
				<button type="button" class="live-btn btn b mt-1" id="live-btn">LIVE</button>
			</div>

			<!-- 다운로드 버튼 -->
			<div id="download">
				<button type="button" class="download-btn btn mt-1 b"
					id="download-btn">다운로드</button>
			</div>

			<!-- 메뉴창 버튼 -->
			<div id="menu-show">
				<button type="button" class="menu-show-btn btn btn-success mt-1 b"
					id="menu-show-btn" data-bs-toggle="offcanvas"
					data-bs-target="#offcanvasScrolling"
					aria-controls="offcanvasScrolling">&gt;</button>
			</div>

			<div class="frame text-center offcanvas offcanvas-start p-0"
				data-bs-keyboard="false" tabindex="-1" data-bs-backdrop="false"
				id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel"
				style="background-color: #F2EFEC;">

				<div type="button" class="btn-close mt-1" id="menu-close-btn"
					data-bs-dismiss="offcanvas" aria-label="Close"
					style="font-size: 20px; position: absolute; right: 0;"></div>

				<!-- 정보창 offcanvas -->
				<div class=" offcanvas-header me-3">
					<img src="/resources/img/yongin-logo.png" class="img-fluid col-3">
					<h5 class="col-9 my-auto">용인시 청소차 관제 시스템</h5>
				</div>
				<!-- 왼쪽 화면 데이터  -->
				<div class="info-view col-12 offcanvas-body p-1" id="info-view">
					<div class="stats-data">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th class="w-25"
										style="background-color: #bd445b; color: white;">지도유형</th>
									<td class="w-25" id="base-map-btn"
										style="background-color: #293661; color: white;">기본</td>
									<td class="w-25" id="satellite-map-btn"
										style="background-color: white; color: black;">위성</td>
									<td class="w-25" id="hybrid-map-btn"
										style="background-color: white; color: black;">하이브리드</td>
								</tr>
								<tr>
									<th class="w-25"
										style="background-color: #bd445b; color: white;">권역(구)</th>
									<td class="w-25 cheoin"
										style="background-color: white; color: black;">처인구</td>
									<td class="w-25 giheung"
										style="background-color: white; color: black;">기흥구</td>
									<td class="w-25 suji"
										style="background-color: white; color: black;">수지구</td>
								</tr>
								<tr>
									<th class="w-25"
										style="background-color: #bd445b; color: white;">차량</th>
									<td class="dropdown" colspan="3"><select class="col-12"
										name="car_num_list" id="car_num">
										
											<option disabled selected class="text-center">차량을
												선택하세요.</option>
											<c:forEach var="list" items="${carNumList}" varStatus="st">
												<!-- <option value=${st.count} class="text-center">${list.carNum}</option> -->
												<option value=${list.carNum } class="text-center selectedDate">${list.carNum}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th class="w-25" style="line-height: 300px; height: 300px;">날짜</th>
									<td colspan="3">
										<table class="sec-cal col-12 mt-3" id="calendar">
											<tr class="fs-4">
												<td><label onclick="prevCalendar()">&lt;</label></td>
												<td id="tbCalendarYM" colspan="5">yyyy년 m월</td>
												<td><label onclick="nextCalendar()">&gt;</label></td>
											</tr>
											<tr style="height: 50px;">
												<td class="text-danger">일</td>
												<td>월</td>
												<td>화</td>
												<td>수</td>
												<td>목</td>
												<td>금</td>
												<td class="text-primary">토</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="w-25">운행시간</th>
									<td colspan="3" id="clean-time">00:00:00</td>
								</tr>
								<tr>
									<th class="w-25">청소비율</th>
									<td colspan="3" id="clean-ratio">0%</td>
								</tr>
								<tr>
									<th class="w-25">총 운행거리</th>
									<td colspan="3" id="total-distance">0 Km</td>
								</tr>
								<tr>
									<th class="w-25 p-3"><span>유효</span><br> <span>
											운행거리</span></th>
									<td colspan="3" id="clean-distance" style="line-height: 56px;">0 Km</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="container-fluid">
						<div class="row">
							<button id="add-data-btn" type="button" class="btn mt-2"
								style="background-color: #293661; color: white;">데이터 추가</button>
							<button id="add-car-btn" type="button" class="btn mt-2"
								style="background-color: #bd445b; color: white;">차량 추가</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 데이터 추가 modal -->
            <div class="modal fade" id="add-data-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title offset-1">데이터 추가</h5>
                        </div>
                        <div class="modal-body container">
                            <form class="form-group row" id="upload-form" enctype="multipart/form-data">
                                <!-- gps -->
                                <div class="offset-2 col-9">
                                    <input id="gps-csv" name="gpsfile" filestyle="" type="file" data-class-button="btn btn-default"
                                        data-class-input="form-control" data-button-text="" accept=".csv"
                                        data-icon-name="fa fa-upload" class="form-control csv-file-input" tabindex="-1"
                                        style="position: absolute; clip: rect(0px 0px 0px 0px);">
                                    <div class="bootstrap-filestyle input-group">
                                        <input type="text" id="gpsfile" class="form-control" name="gpsfile" disabled="">
                                        <span class="group-span-filestyle input-group-btn" tabindex="0">
                                            <label for="gps-csv" class="btn btn-default">
                                                <i class="fa-solid fa-upload"></i>
                                            </label>
                                        </span>
                                    </div>
                                    <div class="text-start">※ 위치 gps.csv</div>
                                </div>
                                <!-- noise -->
                                <div class="offset-2 col-9 mt-4">
                                    <input id="noise-csv" name="noisefile" filestyle="" type="file" data-class-button="btn btn-default"
                                        data-class-input="form-control" data-button-text="" accept=".csv"
                                        data-icon-name="fa fa-upload" class="form-control csv-file-input" tabindex="-1"
                                        style="position: absolute; clip: rect(0px 0px 0px 0px);">
                                    <div class="bootstrap-filestyle input-group">
                                        <input type="text" id="noisefile" class="form-control" name="noisefile" disabled="">
                                        <span class="group-span-filestyle input-group-btn" tabindex="0">
                                            <label for="noise-csv" class="btn btn-default">
                                                <i class="fa-solid fa-upload"></i>
                                            </label>
                                        </span>
                                    </div>
                                    <div class="text-start">※ 소음 noise.csv</div>
                                </div>
                                <!-- rpm -->
                                <div class="offset-2 col-9 mt-4">
                                    <input id="rpm-csv" name="rpmfile" filestyle="" type="file" data-class-button="btn btn-default"
                                        data-class-input="form-control" data-button-text="" accept=".csv"
                                        data-icon-name="fa fa-upload" class="form-control csv-file-input" tabindex="-1"
                                        style="position: absolute; clip: rect(0px 0px 0px 0px);">
                                    <div class="bootstrap-filestyle input-group">
                                        <input type="text" id="rpmfile" class="form-control" name="rpmfile" disabled="">
                                        <span class="group-span-filestyle input-group-btn" tabindex="0">
                                            <label for="rpm-csv" class="btn btn-default">
                                                <i class="fa-solid fa-upload"></i>
                                            </label>
                                        </span>
                                    </div>
                                    <div class="text-start">※ 진동 rpm.csv</div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="csv-register-btn btn" id="csv-register-btn"
                                style="background-color: #bd445b; color:white;">등록</button>
                            <button type="button" class="csv-cancel-btn btn"
                                style="background-color: #bd445b; color:white;">취소</button>
                        </div>
                    </div>
                </div>
            </div>
			<!-- 차량 추가 modal -->
			<div class="modal fade" id="add-car-modal" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<form class="form-group row" action="/view" method="post">
							<div class="modal-header">
								<h5 class="modal-title offset-1">차량 추가</h5>
							</div>
							<div class="modal-body contianer">
								<div class="col-10">
									<!-- 차량번호 입력 -->
									<input type="text" class="col-11 offset-2" name="carNum">
									<div class="text-start offset-2">※차량번호를 입력해주세요.</div>
								</div>
								<div class="col-10 mt-4">
									<select name="carType" id="car-type" class="col-11 offset-2">
										<option value="진공노면 차량">진공노면 차량</option>
										<option value="분진흡입 차량">분진흡입 차량</option>
									</select>
									<div class="text-start offset-2">※차량유형을 선택해주세요.</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="car-register-btn btn"
									style="background-color: #bd445b; color: white;">등록</button>
								<button type="button" class="car-cancel-btn btn"
									style="background-color: #bd445b; color: white;">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
 	
	<!-- local에서 갖고옴 -->
	<script src="/resources/js/mainView/mainView.js"></script>
	<script src="/resources/js/mainView/main.js"></script>
	<script src="/resources/js/mainView/calendar.js"></script>
    
    <!-- <script src="/resources/js/prac.js"></script> -->
</body>

</html>