import {Component, OnInit} from '@angular/core';
import {Article} from "../Modele/article.modele";
import {Articlepanier} from "../Modele/articlepanier.modele";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent {

  articles: Article[] = [];
  quantities: { [key: number]: number } = {};
  articlepanier : Articlepanier[] = [];
  total: number = 0;
  constructor(private http: HttpClient) {
    console.log("loaded");
  }


  ngOnInit() {
    this.http.get<{total: number, articles: {[key: number]: number}}>('http://localhost:8080/ASI_Project_war/api/panier')
      .subscribe(response => {
        console.log(response);
        for (let articleId in response.articles) {
          this.http.get<Article>('http://localhost:8080/ASI_Project_war/api/articles/' + articleId)
            .subscribe((data: Article) => {
                console.log(data);
                this.articles.push(data);
                let quantity = response.articles[articleId];
                this.quantities[data.id] = quantity;
                this.total += data.price * quantity;
              });
        }
      });
  }

}
