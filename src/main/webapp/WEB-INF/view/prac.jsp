<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/ol@v8.1.0/dist/ol.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/ol@v8.1.0/ol.css">
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/resources/js/prac.js"></script>
    <script src="/resources/js/mainView/main.js"></script>
    <style>
        .map {
            height: 850px;
            width: 100%;
        }
    </style>
</head>
<body>
    <label for="car_num">차량번호:</label>
    <select id="car_num" name="car_num" class="selectCarNum">
    	<option>103하2414</option>
    	<option>114하6585</option>
    </select>
    
    <label for="date">일자:</label>
    <input type="date" id="date" name="date" class="selectDate">

    <div id="selectGu">
        <button id="cheoingu">처인구</button>
        <button id="giheunggu">기흥구</button>
        <button id="sujigu">수지구</button>
    </div>
    <div id="download">
        <button type="button" class="download-btn btn mt-1 b" id="download-btn">다운로드</button>
    </div>
    <div id="map" class="map"></div>
</body>
</html>