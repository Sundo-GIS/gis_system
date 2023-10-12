window.addEventListener("load", function() {
	// 배경지도
	let map = new ol.Map({
		target: 'map',
		layers: [
			new ol.layer.Tile({
				source: new ol.source.XYZ({
					url: 'https://api.vworld.kr/req/wmts/1.0.0/01FC9396-78C3-3A58-99A4-EF97461DFFEE/Hybrid/{z}/{y}/{x}.png'
				})
			})
		],
		view: new ol.View({
			center: ol.proj.transform([127.235537, 37.2310864], 'EPSG:4326', 'EPSG:3857'),
			zoom: 11.5,
			minZoom: 0,
			maxZoom: 21
		})
	});

	// 용인시 레이어 추가
	var boundary = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'sig',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});

	// 데이터 가져오기
	var dateInput = document.querySelector(".selectDate")
	var carNumInput = document.querySelector(".selectCarNum")
	dateInput.addEventListener('change', function(event) {
		console.log(dateInput.value);
		updateMap(); // 날짜가 변경되면 지도 업데이트
	});

	carNumInput.addEventListener('change', function(event) {
		updateMap(); // 차량 번호가 변경되면 지도 업데이트
	});

	// 일자, 차량번호 선택하여 데이터 출력하기
	function updateMap() {
		// 사용자가 입력한 값을 가져오기
		var date = dateInput.value;
		var car_num = carNumInput.value;
		console.log(date + " " + car_num);
		var viewparams = 'date:' + date + ';carNum:' + car_num;

		line.getSource().updateParams({ 'viewparams': viewparams });
		point.getSource().updateParams({ 'viewparams': viewparams });
		start_point.getSource().updateParams({ 'viewparams': viewparams });
		end_point.getSource().updateParams({ 'viewparams': viewparams });

		// 중심 좌표 이동
		$.ajax({
			type: "GET",
			url: "/gis/coord", // 시작 요청을 보낼 엔드포인트 URL
			data: {
				date: date,
				carNum: car_num
			},
			dataType: "json",
			success: function(data) {
				var lon = data.lon;
				var lat = data.lat;
				console.log(lon + " +  " + lat);
				map.getView().animate({
					center: ol.proj.transform([lon, lat], 'EPSG:4326', 'EPSG:3857'),
					zoom: 15,
					duration: 600
				});
			}
		});
	};

	// 구 선택 이벤트

	var cheoingu = document.getElementById('cheoingu');
	var giheunggu = document.getElementById('giheunggu');
	var sujigu = document.getElementById('sujigu');

	cheoingu.addEventListener("click", function() {
		map.getView().animate({
			center: ol.proj.transform([127.252989, 37.2076312], 'EPSG:4326', 'EPSG:3857'), // 포인트의 좌표로 설정
			zoom: 12,
			duration: 800
		});
	})

	giheunggu.addEventListener("click", function() {
		map.getView().animate({
			center: ol.proj.transform([127.125525, 37.2702214], 'EPSG:4326', 'EPSG:3857'), // 포인트의 좌표로 설정
			zoom: 12,
			duration: 800
		});
	})

	sujigu.addEventListener("click", function() {
		map.getView().animate({
			center: ol.proj.transform([127.068532, 37.3323845], 'EPSG:4326', 'EPSG:3857'), // 포인트의 좌표로 설정
			zoom: 12,
			duration: 800
		});
	})



	var point = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'clean_data',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	var line = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'clean_line',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	var start_point = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'start_point',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	var end_point = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'end_point',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	var live_start_point = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'live_start_point',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	var live_end_point = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'live_end_point',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	var live_coord = new ol.layer.Tile({
		source: new ol.source.TileWMS({
			url: 'http://localhost:8080/geoserver/wms',
			params: {
				'LAYERS': 'live_coord',
				'TILED': true,
			},
			serverType: 'geoserver',
		})
	});
	map.addLayer(boundary);
	map.addLayer(line);
	map.addLayer(point);
	map.addLayer(start_point);
	map.addLayer(end_point);
	boundary.setOpacity(0.5)

	var live_start = document.getElementById('live_start');
	var live_stop = document.getElementById('live_stop');

	live_start.addEventListener("click", function() {
		console.log("라이브 시작");
		map.getView().animate({
			center: ol.proj.transform([127.2075537, 37.2310864], 'EPSG:4326', 'EPSG:3857'),
			zoom: 11.5,
			duration: 600
		});
		liveStart();
	})
	function liveStart() {
		console.log("새로고침!!");
		map.addLayer(live_coord);
		map.addLayer(live_start_point);
		map.addLayer(live_end_point);
		map.removeLayer(line);
		map.removeLayer(point);
		map.removeLayer(start_point);
		map.removeLayer(end_point);
		localStorage.setItem("previousState", "someValue");

		intervalId = setInterval(function() {
			location.reload(); // 현재 페이지 새로고침
		}, 10000);
	};
	live_stop.addEventListener("click", function() {
		console.log("라이브 종료");
		map.removeLayer(live_coord);
		map.removeLayer(live_start_point);
		map.removeLayer(live_end_point);
		map.addLayer(line);
		map.addLayer(point);
		map.addLayer(start_point);
		map.addLayer(end_point);
		boundary.setOpacity(0.5)
	})
	var previousState = localStorage.getItem("previousState");
	if (previousState === "someValue") {
		// 이전 상태 복원
		console.log("이전 상태 복원");
		liveStart(); // start 함수 호출
	}
});