(function () {
    'use strict'

    angular.module('app')
        .controller('NavbarIndexController', NavbarIndexController);

        NavbarIndexController.$inject = ['NavbarIndexService', '$state', '$stateParams', '$scope', '$http', '$location', 'DialogBuilder'];

    function NavbarIndexController(NavbarIndexService, $state, $stateParams, $scope, $http, $location, DialogBuilder) {
        $scope.logout = function() {            
            localStorage.setItem("userToken", "");
            $location.path("/login");
        }
    }
})();