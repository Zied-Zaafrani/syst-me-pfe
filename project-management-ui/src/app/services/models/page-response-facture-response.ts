/* tslint:disable */
/* eslint-disable */
import { FactureResponse } from '../models/facture-response';
export interface PageResponseFactureResponse {
  content?: Array<FactureResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
