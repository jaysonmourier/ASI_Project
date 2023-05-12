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

  retirerDuPanier(articleId: number) {
    // Envoyer une requête HTTP DELETE à l'API REST
    this.http.delete('http://localhost:8080/ASI_Project_war/api/panier/retirer/' + articleId).subscribe(() => {
      location.reload();
    }, error => {
      console.log(articleId);
      console.error(error);
    });
  }

  viderPanier() {
    // Boucler sur tous les articles du panier et les supprimer individuellement
    for (let article of this.articles) {
      this.http.delete('http://localhost:8080/ASI_Project_war/api/panier/retirer/' + article.id).subscribe(() => {
        this.actualiserPanier();
      }, error => {
        console.error(error);
      });
    }
  }

  private actualiserPanier() {
    this.articles = [];
    this.quantities = {};
    this.total = 0;
    this.http.get<{ total: number, articles: { [key: number]: number } }>('http://localhost:8080/ASI_Project_war/api/panier')
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
