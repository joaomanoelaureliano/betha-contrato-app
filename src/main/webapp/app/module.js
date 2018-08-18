(function () {

    angular.module('app', [
        'ui.router',
        'ngResource'
    ]);

    angular.module('app').config(AppConfig).run(run);

    AppConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$httpProvider'];

    function AppConfig($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise('/login')
        $stateProvider
            .state({
                name: 'login',
                url: '/login',
                templateUrl: '/view/login/login.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            }).state({
                name: 'homeSistema',
                url: '/home',
                templateUrl: '/view/home/home.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .state({
                name: 'sistema',
                url: '/sistema',
                templateUrl: 'sistema.html'
            })
            .state('sistema.relatorioTodos', {
                url: '/contratos/todos',
                templateUrl: '/view/contrato/list-todos.html',
                controller: 'ContratoListController',
                controllerAs: 'vm'
            })
            .state('sistema.cadastroContratos', {
                url: '/contrato/novo',
                templateUrl: '/view/contrato/form.html',
                controller: 'ContratoFormController',
                controllerAs: 'vm'
            })
            .state('sistema.contratosEditar', {
                url: '/contrato/{id}',
                templateUrl: '/view/contrato/form.html',
                controller: 'ContratoFormController',
                controllerAs: 'vm'
            });



        $httpProvider.interceptors.push(function ($q, $location) {
            return {
                'request': function (config) {
                    config.headers.Authorization = 'Bearer ' + localStorage.getItem("userToken");
                    return config;
                },
                'responseError': function (response) {
                    if (response.status == 401) {
                        $location.path("/login");
                    }
                    return response;
                }
            };
        });


    }

    /*
        TODO
        PRIORIZAR CADASTROS DAS TELAS E DE FUNCIONALIDADE DO SISTEMA!!!!!!


        Landing Page 
        Verificar se o usuário ta logado e não deixar exibir a tela de login
        Fazer botão de logout
        Fazer cadastro de usuarios
    */
    run.$inject = ['$rootScope', '$location', '$http', '$transitions'];
    function run($rootScope, $location, $http, $transitions) {
        $transitions.onBefore({}, function (trans) {
            var restrictedPage = $.inArray($location.path(), ['/index', '/register', '']) === -1;
            if (restrictedPage) {
                $http.get("/api/auth/validatoken").then(function (response) {
                    if (response.status == 401) { 
                        $location.path("/login");
                    }
                }, function (error) {
                    if (response.status == 401) {
                        $location.path("/login");
                    }
                    console.log(error);
                });
            }
        });
    }

})();