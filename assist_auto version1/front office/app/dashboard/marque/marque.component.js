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
var marque_service_1 = require('../../services/marque.service');
var router_1 = require("@angular/router");
var angular2_flash_messages_1 = require('angular2-flash-messages');
var MarqueComponent = (function () {
    function MarqueComponent(_flashMessagesService, marqueService, route) {
        this._flashMessagesService = _flashMessagesService;
        this.marqueService = marqueService;
        this.route = route;
        this.motCle = "";
        this.pageCourante = 0;
        this.size = 4;
    }
    MarqueComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.marqueService.getAllMarque()
            .subscribe(function (posts) {
            _this.marques = posts;
        });
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    MarqueComponent.prototype.afficherPartieListe = function () {
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    MarqueComponent.prototype.afficherPartieAjoutMarque = function () {
        this.partieAffiche = false;
        this.partieAjout = true;
    };
    MarqueComponent.prototype.recupererIdMarque = function (a) {
        this.n = a;
    };
    MarqueComponent.prototype.editMarque = function (a) {
        var _this = this;
        this.recupererIdMarque(a);
        this.marqueService.getMarque(a)
            .subscribe(function (posts) {
            _this.nom_marque = posts.nom_marque;
        });
    };
    MarqueComponent.prototype.validerSupprimerMarque = function (a) {
        this.marqueService.supprimerMarque(a).subscribe(function (posts) { });
        window.location.reload();
    };
    MarqueComponent.prototype.validerEditMarque = function () {
        this.marqueService.modifierMarque(this.n, this.nom_marque)
            .subscribe(function (posts) {
        });
    };
    MarqueComponent.prototype.ajouterMarque = function () {
        this.marqueService.ajouterMarque(this.nom_marqueForAjout)
            .subscribe(function (posts) {
        });
    };
    MarqueComponent.prototype.findMarqueForFiltre = function () {
        var _this = this;
        this.marqueService.aaaa(this.motCle, this.pageCourante, this.size)
            .subscribe(function (response) {
            _this.pageMarques = response.content;
            _this.pages = new Array(response.totalPages);
            console.log(_this.pageMarques.content);
        });
    };
    MarqueComponent.prototype.getMarques = function () {
        this.pageCourante = 0;
        this.findMarqueForFiltre();
    };
    MarqueComponent.prototype.goToPage = function (p) {
        this.pageCourante = p;
        this.findMarqueForFiltre();
    };
    MarqueComponent = __decorate([
        core_1.Component({
            selector: 'table-cmp',
            moduleId: module.id,
            templateUrl: 'marque.component.html',
            providers: [marque_service_1.MarqueService],
        }), 
        __metadata('design:paramtypes', [angular2_flash_messages_1.FlashMessagesService, marque_service_1.MarqueService, router_1.Router])
    ], MarqueComponent);
    return MarqueComponent;
}());
exports.MarqueComponent = MarqueComponent;
//# sourceMappingURL=marque.component.js.map