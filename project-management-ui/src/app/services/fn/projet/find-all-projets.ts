/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseProjetResponse } from '../../models/page-response-projet-response';

export interface FindAllProjets$Params {
  page?: number;
  size?: number;
}

export function findAllProjets(http: HttpClient, rootUrl: string, params?: FindAllProjets$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProjetResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllProjets.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseProjetResponse>;
    })
  );
}

findAllProjets.PATH = '/projets/all';
