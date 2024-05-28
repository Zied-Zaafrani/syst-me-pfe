/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FactureRequest } from '../../models/facture-request';
import { FactureResponse } from '../../models/facture-response';

export interface CreateFacture$Params {
      body: FactureRequest
}

export function createFacture(http: HttpClient, rootUrl: string, params: CreateFacture$Params, context?: HttpContext): Observable<StrictHttpResponse<FactureResponse>> {
  const rb = new RequestBuilder(rootUrl, createFacture.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
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

createFacture.PATH = '/factures';
