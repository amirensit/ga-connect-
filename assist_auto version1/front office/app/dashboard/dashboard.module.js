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
var router_1 = require('@angular/router');
var dashboard_routes_1 = require('./dashboard.routes');
var platform_browser_1 = require("@angular/platform-browser");
var forms_1 = require("@angular/forms");
var common_1 = require('@angular/common');
var ng2_filter_pipe_1 = require('ng2-filter-pipe');
var ng2_validation_1 = require('ng2-validation');
var angular2_flash_messages_1 = require('angular2-flash-messages');
var angular2_datatable_1 = require("angular2-datatable");
var DashboardModule = (function () {
    function DashboardModule() {
    }
    DashboardModule = __decorate([
        core_1.NgModule({
            imports: [
                common_1.CommonModule,
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                angular2_flash_messages_1.FlashMessagesModule,
                ng2_validation_1.CustomFormsModule,
                ng2_filter_pipe_1.Ng2FilterPipeModule,
                angular2_datatable_1.DataTableModule,
                forms_1.ReactiveFormsModule,
                router_1.RouterModule.forChild(dashboard_routes_1.MODULE_ROUTES)
            ],
            declarations: [dashboard_routes_1.MODULE_COMPONENTS]
        }), 
        __metadata('design:paramtypes', [])
    ], DashboardModule);
    return DashboardModule;
}());
exports.DashboardModule = DashboardModule;
//# sourceMappingURL=dashboard.module.js.map