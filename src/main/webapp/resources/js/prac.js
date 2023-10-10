window.addEventListener("load", function(){


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
        center: ol.proj.transform([127.2, 37.06], 'EPSG:4326', 'EPSG:3857'),
        zoom: 11,
        minZoom : 0,
        maxZoom : 21
    })
});
    //레이어 추가
    var boundary = new ol.layer.Tile({
        source: new ol.source.TileWMS({
                url: 'http://localhost:8080/geoserver/wms',
                params: {
                    'LAYERS': 'outside',
                    'TILED': true
                },
                serverType: 'geoserver',
            })
         });
        
            var line = new ol.layer.Tile({
                source: new ol.source.TileWMS({
                    url: 'http://localhost:8080/geoserver/wms',
                    params: {
                        'LAYERS': 'sql_line',
                        'TILED': true,
                        'viewparams':'date:2023-08-31;carNum:103하2414'
                        },
                        serverType: 'geoserver',
                    })
                });
            // var point = new ol.layer.Tile({
            //     source: new ol.source.TileWMS({
            //         url: 'http://localhost:8080/geoserver/wms',
            //         params: {
            //             'LAYERS': 'sql_test',
            //             'TILED': true,
            //             'viewparams':'date:2023-08-29;'
            //         },
            //         serverType: 'geoserver',
            //     })
            // });
    map.addLayer(boundary);
    map.addLayer(line);
    // map.addLayer(point);
    boundary.setOpacity(0.5)
});