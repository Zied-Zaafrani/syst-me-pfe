/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { StructureResponse } from '../../models/structure-response';

export interface FindStructureById$Params {
  structureId: number;
}

export function findStructureById(http: HttpClient, rootUrl: string, params: FindStructureById$Params, context?: HttpContext): Observable<StrictHttpResponse<StructureResponse>> {
  const rb = new RequestBuilder(rootUrl, findStructureById.PATH, 'get');
  if (params) {
    rb.path('structureId', params.structureId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<StructureResponse>;
    })
  );
}

findStructureById.PATH = '/structures/{structureId}';
