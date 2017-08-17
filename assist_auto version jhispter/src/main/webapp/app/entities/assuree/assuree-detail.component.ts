import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Assuree } from './assuree.model';
import { AssureeService } from './assuree.service';

@Component({
    selector: 'jhi-assuree-detail',
    templateUrl: './assuree-detail.component.html'
})
export class AssureeDetailComponent implements OnInit, OnDestroy {

    assuree: Assuree;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private assureeService: AssureeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAssurees();
    }

    load(id) {
        this.assureeService.find(id).subscribe((assuree) => {
            this.assuree = assuree;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAssurees() {
        this.eventSubscriber = this.eventManager.subscribe(
            'assureeListModification',
            (response) => this.load(this.assuree.id)
        );
    }
}
