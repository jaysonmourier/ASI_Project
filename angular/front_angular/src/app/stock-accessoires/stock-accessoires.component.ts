import {Component, OnInit} from '@angular/core';
import {Article} from "../Modele/article.modele";
import {HttpClient} from "@angular/common/http";
@Component({
  selector: 'app-stock-accessoires',
  templateUrl: './stock-accessoires.component.html',
  styleUrls: ['./stock-accessoires.component.css']
})
export class StockAccessoiresComponent implements OnInit {
  articles: Article[] = [];

  constructor(private http: HttpClient) {
    console.log("loaded");
  }

  ngOnInit() {
    this.http.get<Article[]>('http://localhost:8080/ASI_Project_war/api/articles/category/23')
      .subscribe((response: Article[]) => {
        this.articles = response;
      });
  }
}
