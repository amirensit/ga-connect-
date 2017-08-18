import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { SysZoneGeographique } from './sys-zone-geographique.model';
import { SysZoneGeographiqueService } from './sys-zone-geographique.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-sys-zone-geographique',
    templateUrl: './sys-zone-geographique.component.html'
})
export class SysZoneGeographiqueComponent implements OnInit, OnDestroy {
sysZoneGeographiques: SysZoneGeographique[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private sysZoneGeographiqueService: SysZoneGeographiqueService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.sysZoneGeographiqueService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.sysZoneGeographiques = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.sysZoneGeographiqueService.query().subscribe(
            (res: ResponseWrapper) => {
                this.sysZoneGeographiques = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSysZoneGeographiques();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: SysZoneGeographique) {
        return item.id;
    }
    registerChangeInSysZoneGeographiques() {
        this.eventSubscriber = this.eventManager.subscribe('sysZoneGeographiqueListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
