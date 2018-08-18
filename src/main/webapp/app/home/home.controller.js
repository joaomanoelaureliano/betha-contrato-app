(function () {
    'use strict'

    angular.module('app')
        .controller('HomeController', HomeController);

        HomeController.$inject = ['HomeService', '$state', '$stateParams', '$scope', '$http', '$location', 'DialogBuilder'];

    function HomeController(HomeService, $state, $stateParams, $scope, $http, $location, DialogBuilder) {
        $scope.logout = function() {            
            localStorage.setItem("userToken", "");
            $location.path("/login");
        }
    }
})();