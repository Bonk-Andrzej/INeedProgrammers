import {Component, OnInit} from '@angular/core';
import {JobOfferDto} from "./model/JobOfferDto";

@Component({
  selector: 'app-job-offer',
  templateUrl: './job-offer.component.html',
  styleUrls: ['./job-offer.component.scss']
})
export class JobOfferComponent implements OnInit {

  jobOffers: JobOfferDto[];

  constructor() {
  }

  ngOnInit() {
  }

}
