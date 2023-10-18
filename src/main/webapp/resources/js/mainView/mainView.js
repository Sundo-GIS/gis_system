const baseMapBtn = document.getElementById("base-map-btn");
const satelliteMapBtn = document.getElementById("satellite-map-btn");
const hybridMapBtn = document.getElementById("hybrid-map-btn");
const downloadBtn = document.getElementById("download-btn");
const menuShowBtn = document.getElementById("menu-show-btn");
const liveBtn = document.getElementById("live-btn");
const liveBtn2 = document.querySelector(".live_stop");
const donwloadBtn = document.getElementById('download-btn');
const offcanvasStart = document.querySelector(".offcanvas-start");

const giheung = document.querySelector('.giheung');
const cheoin = document.querySelector('.cheoin');
const suji = document.querySelector('.suji');


const apiKey = '01FC9396-78C3-3A58-99A4-EF97461DFFEE';

let map;
let mapType = "Base";
let imgType = "png";


// 용인시 경계
const boundary = new ol.layer.Tile({
	source: new ol.source.TileWMS({
		url: 'http://localhost:8080/geoserver/wms',
		params: {
			'LAYERS': 'sig',
			'TILED': true,

		},
		// projection: 'EPSG:4326',
		serverType: 'geoserver',
	})
});
//  첫 화면 생성
startView();

function startView() {
	map = new ol.Map({
		target: 'map',
		// 뷰, style 등을 관리하기 위해 설정
		layers: [
			new ol.layer.Tile({
				source: new ol.source.XYZ({
					url: `https://api.vworld.kr/req/wmts/1.0.0/${apiKey}/${mapType}/{z}/{y}/{x}.${imgType}`
				})
			})
		],
		view: new ol.View({
			center: ol.proj.transform([127.1775537, 37.2410864], 'EPSG:4326', 'EPSG:3857'),
			zoom: 11,
			minZoom: 0,
			maxZoom: 21
		})
	});

	//레이어 추가
	map.addLayer(boundary);
	boundary.setOpacity(0.3);
}

//  전체화면
var myFullScreenControl = new ol.control.FullScreen();
map.addControl(myFullScreenControl);

// 기본 화면
baseMapBtn.addEventListener('click', () => {
	mapType = "Base";
	imgType = "png";
	baseMapBtn.style.backgroundColor = '#293661';
	baseMapBtn.style.color = 'white';

	satelliteMapBtn.style.background = 'white';
	satelliteMapBtn.style.color = 'black';
	hybridMapBtn.style.background = 'white';
	hybridMapBtn.style.color = 'black';
	updateBackgroundLayer();
})

// 인공위성 화면
satelliteMapBtn.addEventListener('click', () => {
	mapType = "Satellite";
	imgType = "jpeg";
	satelliteMapBtn.style.backgroundColor = '#293661';
	satelliteMapBtn.style.color = 'white';

	baseMapBtn.style.background = 'white';
	baseMapBtn.style.color = 'black';
	hybridMapBtn.style.background = 'white';
	hybridMapBtn.style.color = 'black';
	updateBackgroundLayer();
})

// 하이브리드 화면
hybridMapBtn.addEventListener('click', () => {
	mapType = "Hybrid";
	imgType = "png";
	hybridMapBtn.style.backgroundColor = '#293661';
	hybridMapBtn.style.color = 'white';

	baseMapBtn.style.background = 'white';
	baseMapBtn.style.color = 'black';
	satelliteMapBtn.style.background = 'white';
	satelliteMapBtn.style.color = 'black';
	updateBackgroundLayer();
})

function updateBackgroundLayer() {
	const newBackgroundLayer = new ol.layer.Tile({
		source: new ol.source.XYZ({
			url: `https://api.vworld.kr/req/wmts/1.0.0/${apiKey}/${mapType}/{z}/{y}/{x}.${imgType}`
		})
	});
	map.getLayers().setAt(0, newBackgroundLayer); // 현재 첫 번째 레이어를 교체
}

// 수지구 경계
const sujigu = new ol.layer.Tile({
   source: new ol.source.TileWMS({
      url: 'http://localhost:8080/geoserver/wms',
      params: {
         'LAYERS': 'suji',
         'TILED': true,

      },
      serverType: 'geoserver',
   })
});
// 기흥구 경계
const giheunggu = new ol.layer.Tile({
   source: new ol.source.TileWMS({
      url: 'http://localhost:8080/geoserver/wms',
      params: {
         'LAYERS': 'giheung',
         'TILED': true,

      },
      serverType: 'geoserver',
   })
});
// 처인구 경계
const cheoingu = new ol.layer.Tile({
   source: new ol.source.TileWMS({
      url: 'http://localhost:8080/geoserver/wms',
      params: {
         'LAYERS': 'cheoin',
         'TILED': true,

      },
      serverType: 'geoserver',
   })
});

// 처인구 클릭
cheoin.addEventListener('click', () => {

	map.removeLayer(sujigu);
	map.removeLayer(giheunggu);

	map.getView().animate({
		center: ol.proj.transform([127.252989, 37.2076312], 'EPSG:4326', 'EPSG:3857'),
		zoom: 12,
		duration: 800
	});

	cheoin.style.backgroundColor = '#293661';
	cheoin.style.color = 'white';
	giheung.style.background = 'white';
	giheung.style.color = 'black';
	suji.style.background = 'white';
	suji.style.color = 'black';

	// 처인구 레이어를 추가
	map.addLayer(cheoingu);
	chuinBoundary.setOpacity(0.3);

})

// 기흥구 클릭
giheung.addEventListener('click', () => {

	map.removeLayer(sujigu);
	map.removeLayer(cheoingu);

	map.getView().animate({
		center: ol.proj.transform([127.125525, 37.2702214], 'EPSG:4326', 'EPSG:3857'), // 포인트의 좌표로 설정
		zoom: 12,
		duration: 800
	});
	giheung.style.backgroundColor = '#293661';
	giheung.style.color = 'white';
	cheoin.style.background = 'white';
	cheoin.style.color = 'black';
	suji.style.background = 'white';
	suji.style.color = 'black';

	// 기흥 레이어를 추가
	map.addLayer(giheunggu);
	gihungBoundary.setOpacity(0.8);
})

// 수지구 클릭
suji.addEventListener('click', () => {

	map.removeLayer(cheoingu);
	map.removeLayer(giheunggu);

	map.getView().animate({
		center: ol.proj.transform([127.068532, 37.3323845], 'EPSG:4326', 'EPSG:3857'), // 포인트의 좌표로 설정
		zoom: 12,
		duration: 800
	});
	suji.style.backgroundColor = '#293661';
	suji.style.color = 'white';
	cheoin.style.background = 'white';
	cheoin.style.color = 'black';
	giheung.style.background = 'white';
	giheung.style.color = 'black';

	// 기흥 레이어를 추가
	map.addLayer(sujigu);
	sujiBoundary.setOpacity(0.8);
})

// 지도 유형 클릭 초기화 작업중
function mapTypeClick(layer) {
	// 모든 레이어를 지도에서 제거
	map.getLayers().forEach((mapLayer) => {
		map.removeLayer(mapLayer);
	});
	// layer.setZIndex(-1);
	// 선택한 레이어를 지도에 추가
	map.addLayer(layer);
}


// offcanvas 사용시 화면 비율 조정
document.addEventListener('DOMContentLoaded', function() {

	let viewMap = document.getElementById('map');
	let menuClosebtn = document.querySelector('#menu-close-btn');
	menuShowBtn.onclick = function() {
		viewMap.style.right = '0';
		viewMap.style.width = 'calc(100% - 400px)';
		menuShowBtn.style.display = "none";
		liveBtn.style.display = "none";
	}

	menuClosebtn.onclick = function() {
		viewMap.style.width = '100%';
		menuShowBtn.style.display = "";
		liveBtn.style.display = "";
	}
});
// 라이브 버튼 클릭
liveBtn.addEventListener('click', () => {
	downloadBtn.classList.toggle('hide-btn');
	menuShowBtn.classList.toggle('hide-btn');
	liveBtn.classList.toggle('blinkin-btn');

});
liveBtn2.addEventListener('click', () => {
	downloadBtn.classList.toggle('hide-btn');
	menuShowBtn.classList.toggle('hide-btn');
	liveBtn.classList.toggle('blinkin-btn');
});