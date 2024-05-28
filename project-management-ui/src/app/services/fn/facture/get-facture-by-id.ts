/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FactureResponse } from '../../models/facture-response';

export interface GetFactureById$Params {
  id: number;
}

export function getFactureById(http: HttpClient, rootUrl: string, params: GetFactureById$Params, context?: HttpContext): Observable<StrictHttpResponse<FactureResponse>> {
  const rb = new RequestBuilder(rootUrl, getFactureById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<FactureResponse>;
    })
  );
}

getFactureById.PATH = '/factures/{id}';
