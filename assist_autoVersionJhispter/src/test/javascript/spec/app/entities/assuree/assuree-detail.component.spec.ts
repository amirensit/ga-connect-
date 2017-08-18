/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AssureeDetailComponent } from '../../../../../../main/webapp/app/entities/assuree/assuree-detail.component';
import { AssureeService } from '../../../../../../main/webapp/app/entities/assuree/assuree.service';
import { Assuree } from '../../../../../../main/webapp/app/entities/assuree/assuree.model';

describe('Component Tests', () => {

    describe('Assuree Management Detail Component', () => {
        let comp: AssureeDetailComponent;
        let fixture: ComponentFixture<AssureeDetailComponent>;
        let service: AssureeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [AssureeDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AssureeService,
                    JhiEventManager
                ]
            }).overrideTemplate(AssureeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AssureeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AssureeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Assuree(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.assuree).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
