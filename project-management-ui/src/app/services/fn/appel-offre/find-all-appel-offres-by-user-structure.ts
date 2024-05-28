/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseAppelOffreResponse } from '../../models/page-response-appel-offre-response';

export interface FindAllAppelOffresByUserStructure$Params {
  page?: number;
  size?: number;
}

export function findAllAppelOffresByUserStructure(http: HttpClient, rootUrl: string, params?: FindAllAppelOffresByUserStructure$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseAppelOffreResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllAppelOffresByUserStructure.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseAppelOffreResponse>;
    })
  );
}

findAllAppelOffresByUserStructure.PATH = '/appels-offres';
