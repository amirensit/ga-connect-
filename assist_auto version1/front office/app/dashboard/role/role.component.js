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
var role_service_1 = require('../../services/role.service');
var role_1 = require("../../model/role");
var router_1 = require("@angular/router");
var RoleComponent = (function () {
    function RoleComponent(roleService, route) {
        this.roleService = roleService;
        this.route = route;
        //moduleFilter: Module ;
        this.roleFilter = new role_1.Role();
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
        this.pListPackForAjoutClient = [];
        this.Role = {};
        this.loading = false;
    }
    RoleComponent.prototype.ngOnInit = function () {
        this.getRoles();
        this.getModules();
    };
    RoleComponent.prototype.chercherRoles = function () {
        var _this = this;
        this.roleService.serviceFindRole(this.motCle, this.pageCourante, this.size)
            .subscribe(function (response) {
            _this.pageRoles = response.content;
            _this.pages = new Array(response.totalPages);
        });
    };
    RoleComponent.prototype.getRoles = function () {
        this.pageCourante = 0;
        this.chercherRoles();
    };
    RoleComponent.prototype.goToPage = function (p) {
        this.pageCourante = p;
        this.chercherRoles();
    };
    RoleComponent.prototype.getRole = function (id) {
        var _this = this;
        this.rolee.libelle = "";
        this.rolee = this.roleService.getRole(id).subscribe(function (res) { _this.rolee = res, console.log(_this.rolee); }, function (error) { return console.error(error); });
    };
    RoleComponent.prototype.deleteRole = function (id) {
        var _this = this;
        console.log("jit");
        this.loading = false;
        this.r = this.roleService.getRole(id).subscribe(function (r) {
            _this.r = r;
            _this.roleService.deleteRole(r.id).subscribe(function (res) { _this.d = res, _this.getRoles(), _this.loading = true; console.log(res); }, function (error) {
                alert("Could not delete project.");
                console.error('error:', error);
            });
        }, function (error) { return console.error(error); });
    };
    //deleteRole(id) {
    //console.log("jit");
    //this.roleService.deleteRole(id).subscribe(
    //  res =>{ this.re=res,this.getRoles(),
    //    console.log(id);
    //  console.log(res);},
    // error => {alert("Could not delete product."); console.error('error:', error)}
    //);
    // }
    RoleComponent.prototype.selectedRole = function (Role) {
        this.sv = true;
        this.selectedrole = Role;
        this.x = this.selectedrole.id;
        this.roleDTO = { id: this.x };
        this.y = this.selectedrole.libelle;
    };
    RoleComponent.prototype.testIfRoleExist = function () {
        var _this = this;
        this.roleService.testIfRoleExist(this.Role.libelle)
            .subscribe(function (result) {
            if (result != null) {
                _this.roleExistError = "Ce role existe déja ! ";
                console.log(_this.roleExistError);
                // this.flashMessage.show('aaaaaaaaa', {cssClass:'alert-danger', timeout:3000});
                _this.loading = false;
                return true;
            }
            else {
                return false;
            }
        });
        //return true ;
    };
    RoleComponent.prototype.createRole = function () {
        var _this = this;
        this.spinning = true;
        this.roleService.testIfRoleExist(this.Role.libelle)
            .subscribe(function (result) {
            _this.role = result;
            console.log(_this.role);
            if (_this.role == false) {
                _this.test = true;
                _this.roleService.createRole(_this.Role.libelle, _this.Role.modules)
                    .subscribe(function (res) {
                    _this.test = true;
                    _this.Role.libelle = null;
                    _this.spinning = false;
                    _this.roles, console.log(_this.roles);
                }, function (err) { return console.error(err); });
            }
            else {
                _this.test = false;
                _this.t = false,
                    _this.spinning = false;
                _this.roleExistError = "Ce role existe déjà! ";
                console.log(_this.roleExistError);
                _this.loading = false;
                return true;
            }
        });
        console.log("cv");
    };
    RoleComponent.prototype.ajoutPackToClient = function (p, isChecked) {
        if (isChecked) {
            this.pListPackForAjoutClient.push(p);
        }
        else {
            var index = this.pListPackForAjoutClient.findIndex(function (x) { return x.id == p.id; });
            this.pListPackForAjoutClient.splice(index, 1);
        }
        console.log(this.pListPackForAjoutClient);
    };
    RoleComponent.prototype.ajouterRole = function () {
        var _this = this;
        this.spinning = true;
        this.roleService.testIfRoleExist(this.Role.libelle)
            .subscribe(function (result) {
            _this.role = result;
            console.log(_this.role);
            if (_this.role == false) {
                _this.test = true;
                _this.roleService.createRole(_this.Role.libelle, _this.pListPackForAjoutClient).subscribe(function (posts) {
                    _this.ok = posts;
                    _this.Role.libelle = null;
                    _this.spinning = false;
                    _this.roles, console.log(_this.ok);
                });
            }
            else {
                _this.test = false;
                _this.t = false,
                    _this.spinning = false;
                _this.roleExistError = "Ce role existe déjà! ";
                console.log(_this.roleExistError);
                _this.loading = false;
                return true;
            }
        });
        console.log("cv");
    };
    RoleComponent.prototype.recupererIdClient = function (a) {
        this.n = a;
    };
    RoleComponent.prototype.updateRole = function () {
        var _this = this;
        this.roleService.modifierRole(this.rolee.id, this.rolee.libelle, this.pListPackForAjoutClient)
            .subscribe(function (res) { _this.rolee, console.log(_this.rolee); }, function (error) { return console.error(error); });
    };
    RoleComponent.prototype.validerEditRole = function (n) {
        this.roleService.modifierRole(n, this.libelle, this.m)
            .subscribe(function (posts) {
        });
    };
    RoleComponent.prototype.getModules = function () {
        //let b:any;
        //this.execut.endTime="";
        //if(this.User.typeRole!== 1){
        //this.route.navigate(['/gererusers']);
        var _this = this;
        this.roleService.getModules().subscribe(function (response) {
            //console.log(this.User.typeRole);
            _this.modules = response;
            console.log(_this.modules);
        }, function (error) { });
        // }else{
        // alert("hhhhhhhhhhhhhhhhhhh");
        //}
    };
    RoleComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: 'role.component.html',
            providers: [role_service_1.rolesService],
        }), 
        __metadata('design:paramtypes', [role_service_1.rolesService, router_1.Router])
    ], RoleComponent);
    return RoleComponent;
}());
exports.RoleComponent = RoleComponent;
//# sourceMappingURL=role.component.js.map