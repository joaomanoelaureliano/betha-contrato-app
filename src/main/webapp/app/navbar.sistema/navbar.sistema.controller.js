(function () {
    'use strict'

    angular.module('app')
        .controller('NavbarSistemaController', NavbarSistemaController);

        NavbarSistemaController.$inject = ['NavbarSistemaService', '$state', '$stateParams', '$scope', '$http', '$location', 'DialogBuilder'];

    function NavbarSistemaController(NavbarSistemaService, $state, $stateParams, $scope, $http, $location, DialogBuilder) {
        $scope.logout = function() {            
            localStorage.setItem("userToken", "");
            $location.path("/login");
        }
    }
})();