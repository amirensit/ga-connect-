import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { RefRemorqueur } from './ref-remorqueur.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RefRemorqueurService {

    private resourceUrl = 'api/ref-remorqueurs';
    private resourceSearchUrl = 'api/_search/ref-remorqueurs';

    constructor(private http: Http) { }

    create(refRemorqueur: RefRemorqueur): Observable<RefRemorqueur> {
        const copy = this.convert(refRemorqueur);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(refRemorqueur: RefRemorqueur): Observable<RefRemorqueur> {
        const copy = this.convert(refRemorqueur);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<RefRemorqueur> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(refRemorqueur: RefRemorqueur): RefRemorqueur {
        const copy: RefRemorqueur = Object.assign({}, refRemorqueur);
        return copy;
    }
}
