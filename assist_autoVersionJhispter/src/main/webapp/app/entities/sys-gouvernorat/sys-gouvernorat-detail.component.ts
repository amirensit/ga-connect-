import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { SysGouvernorat } from './sys-gouvernorat.model';
import { SysGouvernoratService } from './sys-gouvernorat.service';

@Component({
    selector: 'jhi-sys-gouvernorat-detail',
    templateUrl: './sys-gouvernorat-detail.component.html'
})
export class SysGouvernoratDetailComponent implements OnInit, OnDestroy {

    sysGouvernorat: SysGouvernorat;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private sysGouvernoratService: SysGouvernoratService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSysGouvernorats();
    }

    load(id) {
        this.sysGouvernoratService.find(id).subscribe((sysGouvernorat) => {
            this.sysGouvernorat = sysGouvernorat;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSysGouvernorats() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sysGouvernoratListModification',
            (response) => this.load(this.sysGouvernorat.id)
        );
    }
}
