(function() {
     'use strict'

    angular.module('app')
      .controller('ContratoFormController', ContratoFormController);

    ContratoFormController.$inject = ['ContratoService', '$state', '$stateParams', 'DialogBuilder'];

    function ContratoFormController(ContratoService, $state, $stateParams, DialogBuilder) {

        var vm = this;
        vm.registro = {};
        vm.error = {};
    
        vm.salvar = salvar;


        if ($stateParams.id) {
            ContratoService.findById($stateParams.id)
                .then(function (data) {
                    vm.registro = data;

                });
        }
        
        function salvar() {
            if (!vm.registro.id) {
                ContratoService.insert(vm.registro)
                    .then(function (dado) {
                        DialogBuilder.message('Contrato inserido com sucesso!');
                        $state.go("sistema.relatorioTodos");
                    })
                    .catch(function (error) {
                        vm.error = error.data;
                    });
            } else {
                ContratoService.update(vm.registro)
                    .then(function (dado) {
                        DialogBuilder.message('Contrato alterado com sucesso!');
                        $state.go("sistema.relatorioTodos");
                    })
                    .catch(function (error) {
                        vm.error = error.data;
                    });
            }
        }
    }
})();