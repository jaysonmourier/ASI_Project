import {Component, OnInit} from '@angular/core';
import {Article} from "../Modele/article.modele";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-pc-accessoires',
  templateUrl: './pc-accessoires.component.html',
  styleUrls: ['./pc-accessoires.component.css']
})
export class PcAccessoiresComponent implements OnInit {
  articles: Article[] = [];

  constructor(private http: HttpClient) {
    console.log("loaded");
  }

  ngOnInit() {
    this.http.get<Article[]>('http://localhost:8080/ASI_Project_war/api/articles/category/12')
      .subscribe((response: Article[]) => {
        this.articles = response;
      });
  }
}
