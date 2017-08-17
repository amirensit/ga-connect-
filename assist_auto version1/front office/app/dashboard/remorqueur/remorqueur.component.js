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
var remorqueur_service_1 = require("../../services/remorqueur.service");
var ville_service_1 = require("../../services/ville.service");
var gouvernorat_service_1 = require("../../services/gouvernorat.service");
var RemorqueurComponent = (function () {
    function RemorqueurComponent(remorqueurService, gouvernoratService, villeService, route) {
        this.remorqueurService = remorqueurService;
        this.gouvernoratService = gouvernoratService;
        this.villeService = villeService;
        this.route = route;
    }
    RemorqueurComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.remorqueurService.getAllRemorqueur()
            .subscribe(function (posts) {
            _this.remorqueurs = posts;
        });
        this.gouvernoratService.getListGouvernorat().subscribe(function (posts) {
            _this.gouvernorats = posts;
        });
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    RemorqueurComponent.prototype.afficherPartieListe = function () {
        this.partieAffiche = true;
        this.partieAjout = false;
    };
    RemorqueurComponent.prototype.afficherPartieAjoutRemorqueur = function () {
        this.partieAffiche = false;
        this.partieAjout = true;
    };
    RemorqueurComponent.prototype.recupererIdRemorqueur = function (a) {
        this.n = a;
    };
    RemorqueurComponent.prototype.validerSupprimerRemorqueur = function (b) {
        this.remorqueurService.supprimerRemorqueur(b).subscribe(function (posts) { });
        window.location.reload();
    };
    RemorqueurComponent.prototype.editRemorqueur = function (a) {
        var _this = this;
        this.recupererIdRemorqueur(a);
        this.remorqueurService.getRemorqueur(a)
            .subscribe(function (posts) {
            _this.matricule_fiscal = posts.matricule_fiscal;
            _this.nom_contact = posts.nom_contact;
            _this.etat = posts.etat;
            _this.prenom_contact = posts.prenom_contact;
            _this.raison_social = posts.raison_social;
            _this.tel = posts.tel;
            _this.id_villeForModification = posts.villeDTO.id_ville;
        });
    };
    RemorqueurComponent.prototype.validerEditRemorqueur = function () {
        this.remorqueurService.modifierRemorqueur(this.n, this.matricule_fiscal, this.nom_contact, this.prenom_contact, this.etat, this.villeForEdit.id_ville, this.raison_social, this.tel)
            .subscribe(function (posts) {
        });
        window.location.reload();
    };
    RemorqueurComponent.prototype.getVilleByGouvernorat = function () {
        var _this = this;
        this.villeService.getVilleByGouvernorat(this.gForListVille.id_gouvernorat).subscribe(function (posts) {
            _this.villes = posts;
        });
    };
    RemorqueurComponent.prototype.ajouterRemorqueur = function () {
        this.remorqueurService.ajouterRemorqueur(this.matricule_fiscalForAjout, this.nom_contactForAjout, this.prenom_contactForAjout, this.etatForAjout, this.villeForAjout.id_ville, this.raison_socialForAjout, this.telForAjout)
            .subscribe(function (posts) { });
    };
    RemorqueurComponent.prototype.bloquerRemorqueur = function (isChecked) {
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
        console.log(this.etatForAjout);
    };
    RemorqueurComponent.prototype.debloquerRemorqueur = function (isChecked) {
        this.bloquerOk = false;
        this.debloquerOk = true;
        if (isChecked) {
            this.etat = true;
        }
        else {
            this.etat = false;
        }
        console.log(this.etat);
    };
    RemorqueurComponent = __decorate([
        core_1.Component({
            selector: 'maps-cmp',
            moduleId: module.id,
            templateUrl: 'remorqueur.component.html',
            providers: [remorqueur_service_1.RemorqueurService, ville_service_1.VilleService, gouvernorat_service_1.GouvernoratService],
        }), 
        __metadata('design:paramtypes', [remorqueur_service_1.RemorqueurService, gouvernorat_service_1.GouvernoratService, ville_service_1.VilleService, router_1.Router])
    ], RemorqueurComponent);
    return RemorqueurComponent;
}());
exports.RemorqueurComponent = RemorqueurComponent;
//# sourceMappingURL=remorqueur.component.js.map