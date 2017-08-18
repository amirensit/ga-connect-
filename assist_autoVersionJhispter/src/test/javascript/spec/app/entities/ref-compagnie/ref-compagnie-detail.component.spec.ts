/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RefCompagnieDetailComponent } from '../../../../../../main/webapp/app/entities/ref-compagnie/ref-compagnie-detail.component';
import { RefCompagnieService } from '../../../../../../main/webapp/app/entities/ref-compagnie/ref-compagnie.service';
import { RefCompagnie } from '../../../../../../main/webapp/app/entities/ref-compagnie/ref-compagnie.model';

describe('Component Tests', () => {

    describe('RefCompagnie Management Detail Component', () => {
        let comp: RefCompagnieDetailComponent;
        let fixture: ComponentFixture<RefCompagnieDetailComponent>;
        let service: RefCompagnieService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [RefCompagnieDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RefCompagnieService,
                    JhiEventManager
                ]
            }).overrideTemplate(RefCompagnieDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RefCompagnieDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefCompagnieService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RefCompagnie(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.refCompagnie).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
