var d3Module = angular.module("d3Module", []);
d3Module.factory('d3Service', ['$document', '$q', '$rootScope', d3Service]);
d3Module.directive("barChart", ['d3Service', '$window', barChart]);
d3Module.directive("treeMap", ['d3Service', treeMap]);

function d3Service($document, $q, $rootScope) {
	var d = $q.defer();
	function onScriptLoad() {
		// Load client into browser
		$rootScope.$apply(function() {
			d.resolve(window.d3);
		});
	}
	var scriptTag = $document[0].createElement('script');
	scriptTag.type = 'text/javascript';
	scriptTag.async = true;
	scriptTag.src = "lib/d3/d3.min.js";
	scriptTag.onreadystatechange = function() {
		if (this.readyState == 'complete') onScriptLoad();
	};
	scriptTag.onload = onScriptLoad;
	$document[0].body.appendChild(scriptTag);

	var styleTag = $document[0].createElement('link');
	styleTag.rel = 'stylesheet';
	styleTag.type = 'text/css';
	styleTag.href = "css/d3js.css";
	
	$document[0].head.appendChild(styleTag);

	return {
		d3: function() { 
			return d.promise;
		}
	};
}

function barChart(d3Service, $window) {
	return {
		restrict: 'E',
		replace: false,
		scope: {
			data: '=',
			onClick: '&' // parent execution binding
		},
		link: function (scope, element, attrs) {
			d3Service.d3().then(
				function (d3) {
					var svg = d3.select(element[0])
						.append("svg")
						.attr("class", "chart")
						.style('width', '100%');

					var margin = parseInt(attrs.margin) || 20,
						barHeight = parseInt(attrs.barHeight) || 20,
						barPadding = parseInt(attrs.barPadding) || 5;
		  
					// watch for data changes and re-render
					scope.$watch('data', function(newVals, oldVals) {
						return scope.render(newVals);
					}, true);
					
					// Browser onresize event
					window.onresize = function() {
						scope.$apply();
					};

					 // Watch for resize event
					scope.$watch(function() {
						return angular.element($window)[0].innerWidth;
					}, function() {
						scope.render(scope.data);
					});

					scope.render = function(data) {
						// remove all previous items before render
						svg.selectAll('*').remove();
						if (!data) {
							return;
						}
						// setup variables
						var width = d3.select(element[0]).node().offsetWidth - margin,
						// calculate the height
						height = scope.data.length * (barHeight + barPadding),
						// Use the category20() scale function for multicolor support
						color = d3.scale.category20(),
						// our xScale
						xScale = d3.scale.linear()
							.domain([0, d3.max(data, function(d) {
								return d.value;
							})])
							.range([0, width]);

						// set the height based on the calculations above
						svg.attr('height', height);

						data.sort(function(l,r) { 
							return -1 * (l.value - r.value); 
						});
						//create the rectangles for the bar chart
						svg.selectAll('rect')
						.data(data)
						.enter()
						.append('rect')
						.on('click', function(d,i) {
							return scope.onClick({item: d});
						})
						.attr('height', barHeight)
						.attr('width', 140)
						.attr('x', Math.round(margin/2))
						.attr('y', function(d,i) {
							return i * (barHeight + barPadding);
						})
						.attr('fill', function(d) { return color(d.value); })
						.transition()
						.duration(1000)
						.attr('width', function(d) {
							return xScale(d.value);
						});
						svg.selectAll('text')
						.data(data)
						.enter()
						.append('text')
						.on('click', function(d,i) {
							return scope.onClick({item: d});
						})
						.attr('fill', '#fff')
						.attr('y', function(d,i) {
							return i * (barHeight + barPadding) + 15;
						})
						.attr('x', 15)
						.text(function(d) {
							return d.name + " (value: " + d.value + ")";
						});
					}
				}
			);
		}
	};
}

function treeMap(d3Service) {
	return {
		restrict: 'E',
		replace: false,
		scope: {data: '=mapData'},
		link: function (scope, element, attrs) {
			d3Service.d3().then(
				function (d3) {
					var chart = d3.select(element[0]);
	
					var w = window.innerWidth
						|| document.documentElement.clientWidth
						|| document.body.clientWidth;
	
					var h = window.innerHeight
						|| document.documentElement.clientHeight
						|| document.body.clientHeight;
	
					var width = w * 0.66;
					var height = width * 0.6;
					var color = d3.scale.category20c();
	
					var treemap = d3.layout.treemap()
						.size([width, height])
						.sticky(true)
						.value(function(d) { return d.size; });
			
					var treeMapDiv = document.getElementById("treeMap");
					var div = chart.append("div")
						.style("position", "relative")
						.style("width", width + "px")
						.style("height", height + "px")
						.style("left", "0px")
						.style("top", "0px");
			
					scope.position = function () {
						this.style("left", function(d) { return d.x + "px"; })
							.style("top", function(d) { return d.y + "px"; })
							.style("width", function(d) { return Math.max(0, d.dx - 1) + "px"; })
							.style("height", function(d) { return Math.max(0, d.dy - 1) + "px"; });
					}
			
					var node = div.datum(scope.data).selectAll(".node")
						.data(treemap.nodes)
						.enter().append("div")
						.attr("class", "node")
						.call(scope.position)
						.style("background", function(d) { return d.children ? color(d.name) : null; })
						.text(function(d) { return d.children ? null : d.name; });
				}
			);
		}
	};
}
