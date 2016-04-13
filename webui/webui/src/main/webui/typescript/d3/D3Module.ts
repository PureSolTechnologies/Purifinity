/* 
 * This JavaScript file contains the D3.js bindings to AngularJS for Purifinity.
 */
var d3Module: angular.IModule = angular.module("d3Module", []);
    
d3Module.directive("treeMap", ["d3Service", "$window",
    function(d3Service, $window) {
        return {
            restrict: "E",
            replace: true,
            scope: {
                data: "=",
                onClick: "&" // parent execution binding
            },
            templateUrl: "charts/tree-map-chart.html",
            link: function(scope, element, attrs) {
                d3Service.d3().then(
                    function(d3) {
                        var chart = d3.select(element[0])
                            .append("div")
                            .attr("class", "chart")
                            .style("width", "100%");
				
                        // watch for data changes and re-render
                        scope.$watch("data", function(newVals, oldVals) {
                            return scope.render(newVals);
                        }, true);
					
                        // Browser onresize event
                        window.onresize = function() {
                            scope.$apply();
                        };

                        // Watch for resize event
                        scope.$watch(function() {
                            return angular.element($window)[0]["innerWidth"];
                        }, function() {
                                scope.render(scope.data);
                            });

                        scope.render = function(data) {
                            // remove all previous items before render
                            chart.selectAll("*").remove();
                            if (!data) {
                                return;
                            }
                            // setup variables
                            var element0 = element[0];
                            var selected = d3.select(element0);
                            var node = selected.node();
                            var width = node.parentElement.offsetWidth;
                            if (width <= 0) {
                                return;
                            }
                            // calculate the height
                            var height = Math.round(width / 1.6);

                            var color = d3.scale.category20c();

                            var treemap = d3.layout.treemap()
                                .size([width, height])
                                .sticky(true)
                                .value(function(d) { return d.size; });

                            var div = chart.append("div")
                                .style("position", "relative")
                                .style("width", width + "px")
                                .style("height", height + "px")
                                .style("left", "0px")
                                .style("top", "0px");

                            scope.position = function() {
                                this.style("left", function(d) { return d.x + "px"; })
                                    .style("top", function(d) { return d.y + "px"; })
                                    .style("width", function(d) { return Math.max(0, d.dx - 1) + "px"; })
                                    .style("height", function(d) { return Math.max(0, d.dy - 1) + "px"; });
                            };

                            var node = div.datum(scope.data).selectAll(".node")
                                .data(treemap.nodes)
                                .enter().append("div")
                                .attr("class", "node")
                                .call(scope.position)
                                .style("background", function(d) { return d.children ? color(d.name) : null; })
                                .text(function(d) { return d.children ? null : d.name; });
                        };
                        scope.printChart = function() {
                            var printer: HtmlElementPrinter = new HtmlElementPrinter(chart.node());
                            printer.print();
                        }
                        scope.exportChart = function() {
                            var exporter: ChartExport = new ChartExport(chart.node());
                            exporter.export();
                        }
                    });
            }
        };
    }]);
 