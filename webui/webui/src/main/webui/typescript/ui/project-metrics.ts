var projectMetricsModule: angular.IModule = angular.module("projectMetricsModule", [
    "projectManagerModule", "pluginManagerModule", "purifinityServerModule", "purifinityUI"
]);


projectMetricsModule.controller("treeMapCtrl", ["$scope",
    function treeMapCtrl($scope: any) {
        // Treemap test!
        $scope.mapData =
        {
            "name": "flare",
            "children": [
                {
                    "name": "analytics",
                    "children": [
                        {
                            "name": "cluster",
                            "children": [
                                { "name": "AgglomerativeCluster", "size": 3938 },
                                { "name": "CommunityStructure", "size": 3812 },
                                { "name": "MergeEdge", "size": 743 }
                            ]
                        },
                        {
                            "name": "graph",
                            "children": [
                                { "name": "BetweennessCentrality", "size": 3534 },
                                { "name": "LinkDistance", "size": 5731 }
                            ]
                        }
                    ]
                }
            ]
        };
    }]);
