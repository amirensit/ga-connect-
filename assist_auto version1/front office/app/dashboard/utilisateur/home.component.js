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
var utilisateur_service_1 = require('../../services/utilisateur.service');
var role_service_1 = require('../../services/role.service');
var role_1 = require("../../model/role");
var router_1 = require("@angular/router");
var utilisateur_1 = require("../../model/utilisateur");
var UtilisateurComponent = (function () {
    function UtilisateurComponent(utilisateurService, rolesService, route) {
        this.utilisateurService = utilisateurService;
        this.rolesService = rolesService;
        this.route = route;
        this.roleFilter = new role_1.Role();
        this.utilisateurFilter = new utilisateur_1.Utilisateur();
        this.u = {};
        this.teste = true;
        this.utilisateuur = {};
        this.Utilisateur = {};
        this.sv = false;
        this.selectedrole = {};
        this.roleDTO = {};
        this.motCle = "";
        this.pageCourante = 0;
        this.size = 4;
        this.r = {};
        this.rolee = {};
        this.spinning = false;
        this.roleExistError = '';
        this.role = {};
        this.utilisateur = {};
        this.utilisateurExistError = '';
        this.Role = {};
        this.pListPackForAjoutClient = [];
        this.loading = false;
    }
    UtilisateurComponent.prototype.ngOnInit = function () {
        this.getUtilisateurs();
        this.getRoles();
    };
    UtilisateurComponent.prototype.chercherUtilisateurs = function () {
        var _this = this;
        this.utilisateurService.serviceFindUtilisateur(this.motCle, this.pageCourante, this.size)
            .subscribe(function (response) {
            _this.pageUtilisateurs = response.content;
            _this.pages = new Array(response.totalPages);
        });
    };
    UtilisateurComponent.prototype.getUtilisateurs = function () {
        this.pageCourante = 0;
        this.chercherUtilisateurs();
    };
    UtilisateurComponent.prototype.goToPage = function (p) {
        this.pageCourante = p;
        this.chercherUtilisateurs();
    };
    UtilisateurComponent.prototype.ajoutPackToClient = function (p, isChecked) {
        if (isChecked) {
            this.pListPackForAjoutClient.push(p);
        }
        else {
            var index = this.pListPackForAjoutClient.findIndex(function (x) { return x.id == p.id; });
            this.pListPackForAjoutClient.splice(index, 1);
        }
        console.log(this.pListPackForAjoutClient);
    };
    UtilisateurComponent.prototype.ajouterUtilisateur = function () {
        var _this = this;
        this.spinning = true;
        this.utilisateurService.testIfUtilisateurExist(this.Utilisateur.email)
            .subscribe(function (result) {
            _this.utilisateur = result;
            console.log(_this.utilisateur);
            if (_this.utilisateur == false) {
                _this.test = true;
                if ((_this.nouveaupass == _this.nouveaupass1)) {
                    _this.Utilisateur.motDePasse = _this.nouveaupass1;
                    _this.utilisateurService.createUtilisateur(_this.Utilisateur.nom, _this.Utilisateur.prenom, _this.Utilisateur.email, _this.Utilisateur.motDePasse, _this.Utilisateur.numFixe, _this.Utilisateur.numMobile, _this.Utilisateur.status, _this.Utilisateur.blocage, _this.Utilisateur.connect, _this.pListPackForAjoutClient).subscribe(function (posts) {
                        _this.ok = posts;
                        window.location.reload();
                        _this.spinning = false;
                        _this.utilisateurs, console.log(_this.ok);
                    });
                }
            }
            else {
                _this.test = false;
                _this.t = false,
                    _this.spinning = false;
                _this.utilisateurExistError = "Cet utilisateur existe déjà! ";
                console.log(_this.utilisateurExistError);
                _this.loading = false;
                return true;
            }
        });
        console.log("cv");
    };
    UtilisateurComponent.prototype.getUtilisateur = function (id) {
        var _this = this;
        this.utilisateuur.nom = "";
        this.utilisateuur.prenom = "";
        this.utilisateuur.email = "";
        this.utilisateuur.motDePasse = "";
        this.utilisateuur.numFixe = "";
        this.utilisateuur.numMobile = "";
        this.utilisateuur.status = "";
        this.utilisateuur.blocage = "";
        this.utilisateuur.connect = "";
        this.utilisateuur = this.utilisateurService.getUtilisateur(id).subscribe(function (res) { _this.utilisateuur = res, console.log(_this.utilisateuur); }, function (error) { return console.error(error); });
    };
    UtilisateurComponent.prototype.updateUtilisateur = function () {
        var _this = this;
        this.utilisateurService.modifierUtilisateur(this.utilisateuur.id, this.utilisateuur.nom, this.utilisateuur.prenom, this.utilisateuur.email, this.utilisateuur.motDePasse, this.utilisateuur.numFixe, this.utilisateuur.numMobile, this.utilisateuur.status, this.utilisateuur.blocage, this.utilisateuur.connect, this.pListPackForAjoutClient)
            .subscribe(function (res) { _this.utilisateuur, console.log(_this.utilisateuur); }, function (error) { return console.error(error); });
    };
    UtilisateurComponent.prototype.bloquerUtilisateur = function (id) {
        var _this = this;
        this.u = this.utilisateurService.getUtilisateur(id).subscribe(function (res) { _this.u = res; });
        if (this.u.blocage == false) {
            document.getElementById('checkme-' + id).checked == true;
        }
        else {
            document.getElementById('checkme-' + id).checked == false;
        }
        if (document.getElementById('checkme-' + id).checked == false) {
            (this.utilisateurService.bloquerUtilisateur(id).subscribe(function (re) { console.log("lll"); }));
        }
        else {
            (this.utilisateurService.debloquerUtilisateur(id).subscribe(function (re) { console.log("lllmm"); }));
            //(<HTMLInputElement>document.getElementById('text-'+id)).disabled == true ;
            this.teste = true;
            console.log("hhhhhkkkkh");
        }
    };
    UtilisateurComponent.prototype.check = function (id) {
        var _this = this;
        this.u = this.utilisateurService.getUtilisateur(id).subscribe(function (res) { _this.u = res; }, function (err) { return console.log("ok"); });
        if (this.u.blocage == false) {
            return true;
        }
        else {
            return false;
        }
    };
    UtilisateurComponent.prototype.getRoles = function () {
        //let b:any;
        //this.execut.endTime="";
        //if(this.User.typeRole!== 1){
        //this.route.navigate(['/gererusers']);
        var _this = this;
        this.rolesService.getRoles().subscribe(function (response) {
            //console.log(this.User.typeRole);
            _this.roles = response;
            console.log(_this.roles);
        }, function (error) { });
        // }else{
        // alert("hhhhhhhhhhhhhhhhhhh");
        //}
    };
    UtilisateurComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'home.component.html',
            providers: [utilisateur_service_1.utilisateursService],
        }), 
        __metadata('design:paramtypes', [utilisateur_service_1.utilisateursService, role_service_1.rolesService, router_1.Router])
    ], UtilisateurComponent);
    return UtilisateurComponent;
}());
exports.UtilisateurComponent = UtilisateurComponent;
//# sourceMappingURL=home.component.js.map