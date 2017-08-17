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
var pack_service_1 = require('../../services/pack.service');
var router_1 = require("@angular/router");
var pack_1 = require('../../model/pack');
var PackComponent = (function () {
    function PackComponent(packService, route) {
        this.packService = packService;
        this.route = route;
        this.filtre = new pack_1.PackModel();
    }
    PackComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.packService.getAllPack().subscribe(function (posts) { _this.packs = posts; });
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    PackComponent.prototype.afficherPartieListe = function () {
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    PackComponent.prototype.afficherPartieAjoutPack = function () {
        this.partieAffiche = false;
        this.partieAjout = true;
    };
    PackComponent.prototype.recupererIdPack = function (a) {
        this.n = a;
    };
    PackComponent.prototype.editPack = function (a) {
        var _this = this;
        this.recupererIdPack(a);
        this.packService.getPack(a)
            .subscribe(function (posts) {
            _this.nom_pack = posts.nom_pack;
            _this.description = posts.description;
            _this.nbre_max_service = posts.nbre_max_service;
            _this.kilometrage_max = posts.kilometrage_max;
            _this.etat = posts.etat;
        });
    };
    PackComponent.prototype.validerSupprimerPack = function (a) {
        this.packService.supprimerPack(a).subscribe(function (posts) { });
        window.location.reload();
    };
    PackComponent.prototype.validerEditPack = function () {
        this.packService.modifierPack(this.n, this.nom_pack, this.description, this.nbre_max_service, this.kilometrage_max, this.etat)
            .subscribe(function (posts) {
        });
        window.location.reload();
    };
    PackComponent.prototype.ajouterPack = function () {
        this.etatForAjout = true;
        this.packService.ajouterPack(this.nom_packForAjout, this.descriptionForAjout, this.nbre_max_serviceForAjout, this.kilometrage_maxForAjout, this.etatForAjout)
            .subscribe(function (posts) {
        });
    };
    PackComponent.prototype.bloquerPack = function (isChecked) {
        this.bloquerOk = true;
        this.debloquerOk = false;
        if (isChecked) {
            this.etat = false;
            this.etatForAjout = false;
        }
        else {
            this.etat = true;
            this.etatForAjout = true;
        }
        console.log(this.etat);
    };
    PackComponent.prototype.debloquerPack = function (isChecked) {
        this.bloquerOk = false;
        this.debloquerOk = true;
        if (isChecked) {
            this.etat = true;
            this.etatForAjout = true;
        }
        else {
            this.etat = false;
            this.etatForAjout = false;
        }
        console.log(this.etat);
    };
    PackComponent = __decorate([
        core_1.Component({
            selector: 'home-cmp',
            moduleId: module.id,
            templateUrl: 'pack.component.html',
            providers: [pack_service_1.PackService],
        }), 
        __metadata('design:paramtypes', [pack_service_1.PackService, router_1.Router])
    ], PackComponent);
    return PackComponent;
}());
exports.PackComponent = PackComponent;
//# sourceMappingURL=pack.component.js.map