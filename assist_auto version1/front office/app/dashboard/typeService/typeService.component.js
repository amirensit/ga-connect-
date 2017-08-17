"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require("@angular/router");
var typeService_service_1 = require('../../services/typeService.service');
var model_1 = require('../../model/model');
var angular2_flash_messages_1 = require('angular2-flash-messages');
var TypeServiceComponent = (function () {
    function TypeServiceComponent(_flashMessagesService, typeServiceService, route) {
        this._flashMessagesService = _flashMessagesService;
        this.typeServiceService = typeServiceService;
        this.route = route;
        this.filtre = new model_1.Model();
        this.motCle = "";
        this.pageCourante = 0;
        this.size = 4;
    }
    TypeServiceComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.typeServiceService.getAllTypeService()
            .subscribe(function (posts) {
            _this.typeServices = posts;
        });
        this.partieAffiche = true;
        this.partieAjout = false;
        //this._flashMessagesService.show('suppression validé !!!!', {cssClass: 'alert-success', timeout: 7000 });
    };
    TypeServiceComponent.prototype.afficherPartieListe = function () {
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    TypeServiceComponent.prototype.afficherPartieAjoutMarque = function () {
        this.partieAffiche = false;
        this.partieAjout = true;
    };
    TypeServiceComponent.prototype.recupererIdTypeService = function (a) {
        this.n = a;
    };
    TypeServiceComponent.prototype.editTypeService = function (a) {
        var _this = this;
        this.recupererIdTypeService(a);
        this.typeServiceService.getTypeService(a)
            .subscribe(function (posts) {
            _this.nom_typeService = posts.nom_typeService;
        });
    };
    TypeServiceComponent.prototype.validerSupprimerTypeService = function (a) {
        this.typeServiceService.supprimerTypeService(a).subscribe(function (posts) { });
        //this._flashMessagesService.show('suppression validé !!!!', {cssClass: 'alert-success', timeout: 7000 });
        window.location.reload();
    };
    TypeServiceComponent.prototype.validerEditTypeService = function () {
        this.typeServiceService.modifierTypeService(this.n, this.nom_typeService)
            .subscribe(function (posts) {
        });
        window.location.reload();
    };
    TypeServiceComponent.prototype.ajouterTypeService = function () {
        this.typeServiceService.ajouterTypeService(this.nom_typeServiceForAjout)
            .subscribe(function (posts) {
        });
    };
    TypeServiceComponent = __decorate([
        core_1.Component({
            selector: 'table-cmp',
            moduleId: module.id,
            templateUrl: 'typeService.component.html',
            providers: [typeService_service_1.TypeServiceService],
        }), 
        __metadata('design:paramtypes', [angular2_flash_messages_1.FlashMessagesService, typeService_service_1.TypeServiceService, router_1.Router])
    ], TypeServiceComponent);
    return TypeServiceComponent;
}());
exports.TypeServiceComponent = TypeServiceComponent;
//# sourceMappingURL=typeService.component.js.map