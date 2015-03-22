var d3jsUIModule = angular.module("d3jsUIModule", []);
d3jsUIModule.directive("barChart", barChart);
d3jsUIModule.directive("treeMap", treeMap);

function barChart($parse) {
	return {
		restrict: 'E',
		replace: false,
		scope: {data: '=chartData'},
		link: function (scope, element, attrs) {
			//in D3, any selection[0] contains the group
			//selection[0][0] is the DOM node
			//but we won't need that this time
			var chart = d3.select(element[0]);
			//to our original directive markup bars-chart
			//we add a div with out chart stling and bind each
			//data entry to the chart
			chart.append("div").attr("class", "chart")
				.selectAll('div')
				.data(scope.data).enter().append("div")
				.transition().ease("elastic")
				.style("width", function(d) { return d + "%"; })
				.text(function(d) { return d + "%"; });
			//a little of magic: setting it's width based
			//on the data value (d)
			//and text all with a smooth transition
		}
	};
}

function treeMap($parse) {
	return {
		restrict: 'E',
		replace: false,
		scope: {data: '=mapData'},
		link: function (scope, element, attrs) {
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
	};
}
