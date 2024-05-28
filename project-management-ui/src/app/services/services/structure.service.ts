/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findAllStructures } from '../fn/structure/find-all-structures';
import { FindAllStructures$Params } from '../fn/structure/find-all-structures';
import { findStructureById } from '../fn/structure/find-structure-by-id';
import { FindStructureById$Params } from '../fn/structure/find-structure-by-id';
import { saveStructure } from '../fn/structure/save-structure';
import { SaveStructure$Params } from '../fn/structure/save-structure';
import { StructureResponse } from '../models/structure-response';

@Injectable({ providedIn: 'root' })
export class StructureService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `saveStructure()` */
  static readonly SaveStructurePath = '/structures';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveStructure()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveStructure$Response(params: SaveStructure$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveStructure(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveStructure$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveStructure(params: SaveStructure$Params, context?: HttpContext): Observable<number> {
    return this.saveStructure$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findStructureById()` */
  static readonly FindStructureByIdPath = '/structures/{structureId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findStructureById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findStructureById$Response(params: FindStructureById$Params, context?: HttpContext): Observable<StrictHttpResponse<StructureResponse>> {
    return findStructureById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findStructureById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findStructureById(params: FindStructureById$Params, context?: HttpContext): Observable<StructureResponse> {
    return this.findStructureById$Response(params, context).pipe(
      map((r: StrictHttpResponse<StructureResponse>): StructureResponse => r.body)
    );
  }

  /** Path part for operation `findAllStructures()` */
  static readonly FindAllStructuresPath = '/structures/all';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllStructures()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllStructures$Response(params?: FindAllStructures$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<StructureResponse>>> {
    return findAllStructures(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllStructures$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllStructures(params?: FindAllStructures$Params, context?: HttpContext): Observable<Array<StructureResponse>> {
    return this.findAllStructures$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<StructureResponse>>): Array<StructureResponse> => r.body)
    );
  }

}
