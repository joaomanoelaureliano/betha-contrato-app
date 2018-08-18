(function () {
    'use strict'

    angular.module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginService', '$state', '$stateParams', '$scope', '$http', '$location'];

    function LoginController(LoginService, $state, $stateParams, $scope, $http, $location) {
        var vm = this;
        vm.error = {};
        
        vm.autenticar = autenticar;

        function autenticar() {
            console.log()
            var usuario = {
                login : $scope.login,
                senha : $scope.senha
            }

            
            console.log(usuario);
            $http.post("/api/auth/login", usuario).then(function(response) {
                if (response.status == 200) {
                    localStorage.setItem("userToken", response.data.token);
                    $location.path("/home");
                } else {
                    vm.error.login = "Usuário ou senha incorretos!";
                    console.log(vm.error.login);
                }
            }, function(error) {
                alert("Senha Incorreta!");
                console.log(error);
            });
        }

    }
})();