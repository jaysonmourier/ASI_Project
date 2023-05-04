import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from "../Modele/article.modele";

@Component({
  selector: 'app-pc-bureau',
  templateUrl: './pc-bureau.component.html',
  styleUrls: ['./pc-bureau.component.css']
})
export class PcBureauComponent implements OnInit {
  articles: Article[] = [];

  constructor(private http: HttpClient) {
    console.log("loaded");
  }

  ngOnInit() {
    this.http.get<Article[]>('http://localhost:8080/ASI_Project_war/api/articles')
      .subscribe((response: Article[]) => {
        this.articles = response;
      });
  }
}
