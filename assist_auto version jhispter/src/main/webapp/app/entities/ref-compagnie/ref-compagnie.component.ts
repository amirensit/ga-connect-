import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { RefCompagnie } from './ref-compagnie.model';
import { RefCompagnieService } from './ref-compagnie.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-ref-compagnie',
    templateUrl: './ref-compagnie.component.html'
})
export class RefCompagnieComponent implements OnInit, OnDestroy {
refCompagnies: RefCompagnie[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private refCompagnieService: RefCompagnieService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.refCompagnieService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.refCompagnies = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.refCompagnieService.query().subscribe(
            (res: ResponseWrapper) => {
                this.refCompagnies = res.json;
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
        this.registerChangeInRefCompagnies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RefCompagnie) {
        return item.id;
    }
    registerChangeInRefCompagnies() {
        this.eventSubscriber = this.eventManager.subscribe('refCompagnieListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
