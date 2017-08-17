/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SysGouvernoratDetailComponent } from '../../../../../../main/webapp/app/entities/sys-gouvernorat/sys-gouvernorat-detail.component';
import { SysGouvernoratService } from '../../../../../../main/webapp/app/entities/sys-gouvernorat/sys-gouvernorat.service';
import { SysGouvernorat } from '../../../../../../main/webapp/app/entities/sys-gouvernorat/sys-gouvernorat.model';

describe('Component Tests', () => {

    describe('SysGouvernorat Management Detail Component', () => {
        let comp: SysGouvernoratDetailComponent;
        let fixture: ComponentFixture<SysGouvernoratDetailComponent>;
        let service: SysGouvernoratService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [SysGouvernoratDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SysGouvernoratService,
                    JhiEventManager
                ]
            }).overrideTemplate(SysGouvernoratDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SysGouvernoratDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysGouvernoratService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SysGouvernorat(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.sysGouvernorat).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
