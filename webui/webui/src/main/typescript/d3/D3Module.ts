/* 
 * This JavaScript file contains the D3.js bindings to AngularJS for Purifinity.
 */
var d3Module: angular.IModule = angular.module("d3Module", []);

/*
 * This d3Service is used for lacy loading of d3.js. 
 */
d3Module.factory("d3Service", ["$document", "$q", "$rootScope",
    function($document, $q, $rootScope) {
        var d = $q.defer();
        function onScriptLoad() {
            // Load client into browser
            $rootScope.$apply(function() {
                d.resolve(d3);
            });
        }
        var scriptTag = $document[0].createElement("script");
        scriptTag.type = "text/javascript";
        scriptTag.async = true;
        scriptTag.src = "lib/d3/d3.min.js";
        scriptTag.onreadystatechange = function() {
            if (this.readyState === "complete") {
                onScriptLoad();
            }
        };
        scriptTag.onload = onScriptLoad;
        $document[0].body.appendChild(scriptTag);

        //        var styleTag = $document[0].createElement("link");
        //        styleTag.rel = "stylesheet";
        //        styleTag.type = "text/css";
        //        styleTag.href = "css/d3js.css";
        //        $document[0].head.appendChild(styleTag);

        return {
            d3: function() {
                return d.promise;
            }
        };
    }]);

/**
 * Vertical Pareto Chart.
 */
d3Module.directive("verticalParetoChart", ["d3Service", "$window",
    function(d3Service, $window) {
        return {
            restrict: "E",
            replace: true,
            scope: {
                data: "=",
                onClick: "&" // parent execution binding
            },
            templateUrl: "charts/vertical-pareto-chart.html",
            link: function(scope, element, attrs) {
                d3Service.d3().then(function(d3) {
                    var svg = d3.select(element[0])
                        .append("svg")
                        .attr("class", "chart")
                        .style("width", "100%");

                    var margin = parseInt(attrs.margin, 10) || 20,
                        barHeight = parseInt(attrs.barHeight, 10) || 20,
                        barPadding = parseInt(attrs.barPadding, 10) || 5;
		  
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
                        svg.selectAll("*").remove();
                        if (!data) {
                            return;
                        }
                        // setup variables
                        var width = d3.select(element[0]).node().offsetWidth - margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = scope.data.length * (barHeight + barPadding);

                        var max = d3.max(data, function(d) {
                            return d.value;
                        });
                        var min = d3.min(data, function(d) {
                            return d.value;
                        });
                        var color = d3.scale.linear()
                            .domain([min, (max - min) / 2, max])
                            .range(["red", "yellow", "green"]);
                        // our xScale
                        var xScale = d3.scale.linear()
                            .domain([0, max])
                            .range([0, width]);

                        // set the height based on the calculations above
                        svg.attr("height", height);
                        //create the rectangles for the bar chart
                        svg.selectAll("rect")
                            .data(data)
                            .enter()
                            .append("rect")
                            .on("click", function(d, i) {
                            return scope.onClick({ item: d });
                        })
                            .attr("height", barHeight)
                            .attr("width", 140)
                            .attr("x", Math.round(margin / 2))
                            .attr("y", function(d, i) {
                            return i * (barHeight + barPadding);
                        })
                            .attr("fill", function(d) { return color(d.value); })
                            .transition()
                            .duration(1000)
                            .attr("width", function(d) {
                            return xScale(d.value);
                        });
                        svg.selectAll("text")
                            .data(data)
                            .enter()
                            .append("text")
                            .on("click", function(d, i) {
                            return scope.onClick({ item: d });
                        })
                            .attr("fill", "#000")
                            .attr("y", function(d, i) {
                            return i * (barHeight + barPadding) + 15;
                        })
                            .attr("x", 15)
                            .text(function(d) {
                            return d.name + " (" + String((Math.round(d.value * 100) / 100).toFixed(2)) + ")";
                        });
                    };
                });
            }
        };
    }]);
    
/**
 * Pareto Chart.
 */
d3Module.directive("paretoChart", ["d3Service", "$window",
    function(d3Service, $window) {
        return {
            restrict: "E",
            replace: true,
            scope: {
                data: "=",
                onClick: "&" // parent execution binding
            },
            templateUrl: "charts/pareto-chart.html",
            link: function(scope, element, attrs) {
                d3Service.d3().then(function(d3) {
                    var svg = d3.select(element[0])
                        .append("svg")
                        .attr("class", "chart")
                        .style("width", "100%");

                    var margin = parseInt(attrs.margin, 10) || 30;
				
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
                        svg.selectAll("*").remove();
                        if (!data) {
                            return;
                        }
                        // setup variables
                        var width = d3.select(element[0]).node().offsetWidth - 2 * margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = Math.round(width / 2);
                        var barWidth = width / data.length;

                        var max = d3.max(data, function(d) {
                            return d.value;
                        });
                        var min = d3.min(data, function(d) {
                            return d.value;
                        });

                        var color = d3.scale.linear()
                            .domain([min, (max - min) / 2, max])
                            .range(["red", "yellow", "green"]);
                        // our scales
                        var xScale = d3.scale.ordinal()
                            .domain(data.map(function(d) { return d.name; }))
                            .rangeBands([0, width]);
                        var yScale = d3.scale.linear()
                            .domain([0, max])
                            .range([height, 0]);

                        // set the height based on the calculations above
                        var chart = svg
                            .attr("height", height + 2 * margin)
                            .append("g")
                            .attr("transform", "translate(" + margin + "," + margin + ")");

                        var xAxis = d3.svg.axis()
                            .scale(xScale)
                            .orient("bottom");
                        var yAxis = d3.svg.axis()
                            .scale(yScale)
                            .orient("left");
                        chart
                            .append("g")
                            .attr("class", "x axis")
                            .attr("transform", "translate(0," + height + ")")
                            .call(xAxis);
                        chart
                            .append("g")
                            .attr("class", "y axis")
                            .call(yAxis);
						
                        //create the rectangles for the bar chart
                        chart.selectAll("rect")
                            .data(data)
                            .enter()
                            .append("rect")
                            .on("click", function(d, i) {
                            return scope.onClick({ item: d });
                        })
                            .attr("height", 0)
                            .attr("width", barWidth)
                            .attr("x", function(d, i) {
                            return i * barWidth;
                        })
                            .attr("y", height)
                            .attr("fill", function(d) { return color(d.value); })
                            .transition()
                            .duration(1000)
                            .attr("y", function(d) {
                            return yScale(d.value);
                        })
                            .attr("height", function(d) {
                            return height - yScale(d.value);
                        });
                    };
                });
            }
        };
    }]);

/**
 * Histogram Chart.
 */
d3Module.directive("histogramChart", ["d3Service", "$window",
    function(d3Service, $window) {
        return {
            restrict: "E",
            replace: true,
            scope: {
                data: "=",
                onClick: "&" // parent execution binding
            },
            templateUrl: "charts/histogram-chart.html",
            link: function(scope, element, attrs) {
                d3Service.d3().then(function(d3) {
                    var svg = d3.select(element[0])
                        .append("svg")
                        .attr("class", "chart")
                        .style("width", "100%");

                    var margin = parseInt(attrs.margin, 10) || 30;
				
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
                        svg.selectAll("*").remove();
                        if (!data) {
                            return;
                        }
                        // setup variables
                        var width = d3.select(element[0]).node().offsetWidth - 2 * margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = Math.round(width / 2) - 2 * margin;
                        // bins and bars
                        var binCount = Math.floor(Math.sqrt(data.length));
                        var barWidth = width / binCount;

                        var dataMax = d3.max(data, function(d) {
                            return d.value;
                        });
                        var dataMin = d3.min(data, function(d) {
                            return d.value;
                        });

                        var binData = new Array(binCount);
                        var bins = new Array(binCount);
                        for (var i = 0; i < binData.length; i++) {
                            binData[i] = 0;
                            bins[i] = String((Math.round((dataMin + (dataMax - dataMin) / binCount * (i + 0.5)) * 100) / 100).toFixed(2));
                        }
                        for (var key in data) {
                            var value = data[key].value;
                            var binNum = Math.round((value - dataMin) / (dataMax - dataMin) * binCount);
                            binData[binNum < binCount ? binNum : binCount - 1]++;
                        }

                        var max = d3.max(binData, function(d) {
                            return d;
                        });
                        var min = d3.min(binData, function(d) {
                            return d;
                        });

                        var color = d3.scale.linear()
                            .domain([min, (max - min) / 2, max])
                            .range(["red", "yellow", "green"]);
                        // our yScale
                        var xScale = d3.scale.ordinal()
                            .domain(bins)
                            .rangeBands([0, width]);
                        var yScale = d3.scale.linear()
                            .domain([0, max])
                            .range([0, height]);

                        // set the height based on the calculations above
                        var chart = svg
                            .attr("height", height + 2 * margin)
                            .append("g")
                            .attr("transform", "translate(" + margin + "," + margin + ")");

                        var xAxis = d3.svg.axis()
                            .scale(xScale)
                            .orient("bottom");
                        var yAxis = d3.svg.axis()
                            .scale(yScale)
                            .orient("left");
                        chart
                            .append("g")
                            .attr("class", "x axis")
                            .attr("transform", "translate(0," + height + ")")
                            .call(xAxis);
                        chart
                            .append("g")
                            .attr("class", "y axis")
                            .call(yAxis);

                        //create the rectangles for the bar chart
                        chart.selectAll("rect")
                            .data(binData)
                            .enter()
                            .append("rect")
                            .on("click", function(d, i) {
                            return scope.onClick({ item: d });
                        })
                            .attr("height", 0)
                            .attr("width", barWidth)
                            .attr("x", function(d, i) {
                            return i * barWidth;
                        })
                            .attr("y", height)
                            .attr("fill", function(d) { return color(d); })
                            .transition()
                            .duration(1000)
                            .attr("y", function(d) {
                            return height - yScale(d);
                        })
                            .attr("height", function(d) {
                            return yScale(d);
                        });
                    };
                });
            }
        };
    }]);

/**
 * Correlation Chart.
 */
d3Module.directive("correlationChart", ["d3Service", "$window",
    function(d3Service, $window) {
        return {
            restrict: "E",
            replace: true,
            scope: {
                data: "=",
                onClick: "&" // parent execution binding
            },
            templateUrl: "charts/correlation-chart.html",
            link: function(scope, element, attrs) {
                d3Service.d3().then(function(d3) {
                    var svg = d3.select(element[0])
                        .append("svg")
                        .attr("class", "chart")
                        .style("width", "100%");

                    var margin = parseInt(attrs.margin, 10) || 30;
				
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
                        svg.selectAll("*").remove();
                        if (!data) {
                            return;
                        }
                        // setup variables
                        var width = d3.select(element[0]).node().offsetWidth - 2 * margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = Math.round(width / 2) - 2 * margin;
                    };
                });
            }
        };
    }]);

/**
 * Cummulative Distribution Chart.
 */
d3Module.directive("cummulativeDistributionChart", ["d3Service", "$window",
    function(d3Service, $window) {
        return {
            restrict: "E",
            replace: true,
            scope: {
                data: "=",
                onClick: "&" // parent execution binding
            },
            templateUrl: "charts/cummulative-distribution-chart.html",
            link: function(scope, element, attrs) {
                d3Service.d3().then(function(d3) {
                    var svg = d3.select(element[0])
                        .append("svg")
                        .attr("class", "chart")
                        .style("width", "100%");

                    var margin = parseInt(attrs.margin, 10) || 30;
				
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
                        svg.selectAll("*").remove();
                        if (!data) {
                            return;
                        }
                        // setup variables
                        var width = d3.select(element[0]).node().offsetWidth - 2 * margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = Math.round(width / 2) - 2 * margin;
                    };
                });
            }
        };
    }]);

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
                    }
                    );
            }
        };
    }]);
