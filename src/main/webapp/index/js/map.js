var myChart4 = echarts.init(document.getElementById('map'));
/*myChart4.showLoading();*/
$.get('json/jiangxi.json', function (geoJson) {
	/*myChart4.hideLoading();*/
	echarts.registerMap('江西', geoJson);

	myChart4.setOption(option = {
		visualMap: {
			min: 0,
			max: 500,
			show: false,
			splitNumber: 5,
			inRange: {
				color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
			},
			textStyle: {
				color: '#fff'
			}
		},
		geo: {
			map: '江西',
			label: {
				normal: {
					show: true,
					color: '#fff'
				},
				emphasis: {
					show: true,
					color: '#fff'
				}
			},
			roam: false,
			itemStyle: {
				normal: {
					areaColor: '#40458e',
					borderColor: '#6367ad',
					borderWidth: 1.5
				},
				emphasis: { // 选中样式
					borderWidth: 2,
					borderColor: '#fff',
					areaColor: 'rgba(0,0,0,0.1)',
					label: {
						show: true,
						textStyle: {
							color: '#fff'
						}
					}
				}
			},
			"left": 0,
			"right": 0,
			"top": 0,
			"bottom": 0
		},
		series: [{
			type: 'map',
			coordinateSystem: 'geo',
			blurSize: 30
		}]
	});
	window.addEventListener("resize", function () {
		myChart4.resize();
	});
});