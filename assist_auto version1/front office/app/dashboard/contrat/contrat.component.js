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
var contrat_service_1 = require('../../services/contrat.service');
var ville_service_1 = require("../../services/ville.service");
var gouvernorat_service_1 = require("../../services/gouvernorat.service");
var router_1 = require("@angular/router");
var ContratComponent = (function () {
    function ContratComponent(contratService, gouvernoratService, villeService, route) {
        this.contratService = contratService;
        this.gouvernoratService = gouvernoratService;
        this.villeService = villeService;
        this.route = route;
    }
    ContratComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.dateInvalid = false;
        this.contratService.getAllContrat()
            .subscribe(function (posts) {
            _this.contrats = posts;
            console.log(_this.contrats);
        });
        this.contratService.getAllPack().subscribe(function (posts) { _this.packs = posts; });
        this.contratService.getAllMarque().subscribe(function (posts) { _this.marques = posts; });
        this.gouvernoratService.getListGouvernorat().subscribe(function (posts) {
            _this.gouvernorats = posts;
        });
        this.partieAffiche = true;
        this.partieAjout = false;
        this.partieImport = false;
    };
    ContratComponent.prototype.afficherPartieListe = function () {
        this.partieAffiche = true;
        this.partieAjout = false;
        this.partieImport = false;
    };
    ContratComponent.prototype.afficherPartieAjoutContrat = function () {
        this.partieAffiche = false;
        this.partieAjout = true;
        this.partieImport = false;
    };
    ContratComponent.prototype.afficherPartieImport = function () {
        this.partieImport = true;
        this.partieAffiche = false;
        this.partieAjout = false;
    };
    ContratComponent.prototype.getVilleByGouvernorat = function () {
        var _this = this;
        this.villeService.getVilleByGouvernorat(this.gForListVille.id_gouvernorat).subscribe(function (posts) {
            _this.villes = posts;
        });
    };
    ContratComponent.prototype.recupererIdContrat = function (a) {
        this.n = a;
    };
    ContratComponent.prototype.editContrat = function (a) {
        var _this = this;
        this.recupererIdContrat(a);
        this.contratService.getContrat(a)
            .subscribe(function (posts) {
            _this.addresse = posts.addresse;
            _this.date_debut = posts.date_debut;
            _this.date_fin = posts.date_fin;
            _this.nom = posts.nom;
            _this.num_contrat = posts.num_contrat;
            _this.prenom = posts.prenom;
        });
    };
    ContratComponent.prototype.validerEditContrat = function () {
        this.contratService.modifierContrat(this.n, this.addresse, this.date_debut, this.date_fin, this.nom, this.num_contrat, this.prenom, this.villeForEdit.id_ville, this.marqueForEdit.id_marque, this.packForEdit.id_pack)
            .subscribe(function (posts) {
        });
        window.location.reload();
    };
    ContratComponent.prototype.ajouterContrat = function () {
        this.contratService.ajouterContrat(this.addresseForAjout, this.date_debutForAjout, this.date_finForAjout, this.nomForAjout, this.num_contratForAjout, this.prenomForAjout, this.villeForAjout.id_ville, this.marqueForAjout.id_marque, this.packForAjout.id_pack)
            .subscribe(function (posts) { });
    };
    ContratComponent.prototype.verifierDate = function () {
        if (this.date_finForAjout < this.date_debutForAjout)
            this.dateInvalid = true;
        else
            this.dateInvalid = false;
    };
    ContratComponent.prototype.importerContrat = function () {
        this.contratService.importerContrat()
            .subscribe(function (posts) {
        });
    };
    ContratComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'contrat.component.html',
            providers: [contrat_service_1.ContratService, ville_service_1.VilleService, gouvernorat_service_1.GouvernoratService],
        }), 
        __metadata('design:paramtypes', [contrat_service_1.ContratService, gouvernorat_service_1.GouvernoratService, ville_service_1.VilleService, router_1.Router])
    ], ContratComponent);
    return ContratComponent;
}());
exports.ContratComponent = ContratComponent;
//# sourceMappingURL=contrat.component.js.map