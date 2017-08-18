import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { RefCompagnie } from './ref-compagnie.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RefCompagnieService {

    private resourceUrl = 'api/ref-compagnies';
    private resourceSearchUrl = 'api/_search/ref-compagnies';

    constructor(private http: Http) { }

    create(refCompagnie: RefCompagnie): Observable<RefCompagnie> {
        const copy = this.convert(refCompagnie);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(refCompagnie: RefCompagnie): Observable<RefCompagnie> {
        const copy = this.convert(refCompagnie);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<RefCompagnie> {
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

    private convert(refCompagnie: RefCompagnie): RefCompagnie {
        const copy: RefCompagnie = Object.assign({}, refCompagnie);
        return copy;
    }
}
