/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RefTypeServiceDetailComponent } from '../../../../../../main/webapp/app/entities/ref-type-service/ref-type-service-detail.component';
import { RefTypeServiceService } from '../../../../../../main/webapp/app/entities/ref-type-service/ref-type-service.service';
import { RefTypeService } from '../../../../../../main/webapp/app/entities/ref-type-service/ref-type-service.model';

describe('Component Tests', () => {

    describe('RefTypeService Management Detail Component', () => {
        let comp: RefTypeServiceDetailComponent;
        let fixture: ComponentFixture<RefTypeServiceDetailComponent>;
        let service: RefTypeServiceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [RefTypeServiceDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RefTypeServiceService,
                    JhiEventManager
                ]
            }).overrideTemplate(RefTypeServiceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RefTypeServiceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RefTypeServiceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new RefTypeService(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.refTypeService).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
