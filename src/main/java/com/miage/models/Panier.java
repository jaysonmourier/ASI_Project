package com.miage.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Panier {
    private int id;
    @JsonIgnore
    private Map<Article, Integer> articles;
    private double total;

    public Panier(){
        this.articles = new HashMap<>();
        this.total = 0;
    }

    public Panier(int id) {
        this.id = id;
        this.articles = new HashMap<>();
        this.total = 0;
    }

    public Map<Article, Integer> getArticles() {
        return articles;
    }

    public void setArticles(Map<Article, Integer> articles) {
        this.articles = articles;
    }

    // Ajouter un article au panier
    public void ajouterArticle(Article article, int quantite) {
        if (articles.containsKey(article)) {
            int oldQuantite = articles.get(article);
            articles.put(article, oldQuantite + quantite);
        } else {
            articles.put(article, quantite);
        }
        this.total = getTotal();
    }

    // Retirer un article du panier
    public void retirerArticle(Article article) {
        articles.remove(article);
    }

    // Modifier la quantité d'un article dans le panier
    public void modifierQuantiteArticle(Article article, int nouvelleQuantite) {
        if (nouvelleQuantite > 0) {
            articles.put(article, nouvelleQuantite);
        } else {
            retirerArticle(article);
        }
    }

    // Calculer le montant total du panier
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    @JsonGetter("articles")
    public Map<Integer, Integer> getArticleIdQuantities() {
        Map<Integer, Integer> articleIdQuantities = new HashMap<>();
        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            Article article = entry.getKey();
            int articleId = article.getId();
            int quantity = entry.getValue();
            articleIdQuantities.put(articleId, quantity);
        }
        return articleIdQuantities;
    }

    public void setArticleIdQuantities(Map<Integer, Integer> articleIdQuantities) {
        for (Map.Entry<Integer, Integer> entry : articleIdQuantities.entrySet()) {
            Article article = new Article(entry.getKey());
            int quantity = entry.getValue();
            this.articles.put(article, quantity);
        }
    }
}
