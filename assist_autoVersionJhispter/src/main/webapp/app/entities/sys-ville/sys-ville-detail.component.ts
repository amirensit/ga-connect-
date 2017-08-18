import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { SysVille } from './sys-ville.model';
import { SysVilleService } from './sys-ville.service';

@Component({
    selector: 'jhi-sys-ville-detail',
    templateUrl: './sys-ville-detail.component.html'
})
export class SysVilleDetailComponent implements OnInit, OnDestroy {

    sysVille: SysVille;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sysVilleService: SysVilleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSysVilles();
    }

    load(id) {
        this.sysVilleService.find(id).subscribe((sysVille) => {
            this.sysVille = sysVille;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSysVilles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sysVilleListModification',
            (response) => this.load(this.sysVille.id)
        );
    }
}
